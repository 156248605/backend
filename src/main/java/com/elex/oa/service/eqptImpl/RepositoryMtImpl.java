package com.elex.oa.service.eqptImpl;

import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.dao.eqptDao.RepositoryMtMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.RepositoryMtService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class RepositoryMtImpl implements RepositoryMtService {
    @Resource
    private RepositoryMtMapper repositoryMtMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private IUserDao iUserDao;

    @Override
    public PageInfo<Repository> showRepositoryMt(Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> listM = repositoryMtMapper.RepositoryMtList();
        return new PageInfo<>(listM);
    }

    @Override
    public PageInfo<Repository> searchRepositoryMt(Page page, HttpServletRequest request) {
        String REPTID = request.getParameter("reptId");
        String REPTIDC = request.getParameter("reptIdC");
        String REPTNAME = request.getParameter("reptName");
        String REPTNAMEC = request.getParameter("reptNameC");
        String REPTADMIN = request.getParameter("reptAdmin");
        String REPTADMINC = request.getParameter("reptAdminC");
        String REPTSTATE = request.getParameter("reptState");
        String REPTSTATEC = request.getParameter("reptStateC");
        String POSTMANAGE = request.getParameter("postManage");
        String POSTMANAGEC = request.getParameter("postManageC");
        String POSTID = request.getParameter("postId");
        String POSTIDC = request.getParameter("postIdC");
        String POSTNAME = request.getParameter("postName");
        String POSTNAMEC = request.getParameter("postNameC");
        String POSTCATE = request.getParameter("postCate");
        String POSTCATEC = request.getParameter("postCateC");
        String FIXPOSTMAT = request.getParameter("fixPostMat");
        String FIXPOSTMATC = request.getParameter("fixPostMatC");
        String POSTCAP = request.getParameter("postCap");
        String POSTCAPC = request.getParameter("postCapC");
        String REPTADDR = request.getParameter("reptAddr");
        String REPTADDRC = request.getParameter("reptAddrC");
        String REMARK = request.getParameter("remark");
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        if (REPTID.equals("") && REPTNAME.equals("") && REPTADMIN.equals("") && REPTSTATE.equals("") && REMARK.equals("") && POSTMANAGE.equals("") && POSTID.equals("") && POSTNAME.equals("") && POSTCATE.equals("") && FIXPOSTMAT.equals("") && POSTCAP.equals("") && REPTADDR.equals("") ){
            List<Repository> listR = repositoryMtMapper.RepositoryMtList();
            return new PageInfo<>(listR);
        }else {
            Repository repository = new Repository();
            repository.setReptId(REPTID);
            repository.setReptIdC(REPTIDC);
            repository.setReptName(REPTNAME);
            repository.setReptNameC(REPTNAMEC);
            repository.setReptAdmin(REPTADMIN);
            repository.setReptAdminC(REPTADMINC);
            repository.setReptState(REPTSTATE);
            repository.setReptStateC(REPTSTATEC);
            repository.setPostManage(POSTMANAGE);
            repository.setPostManageC(POSTMANAGEC);
            repository.setPostId(POSTID);
            repository.setPostIdC(POSTIDC);
            repository.setPostName(POSTNAME);
            repository.setPostNameC(POSTNAMEC);
            repository.setPostCate(POSTCATE);
            repository.setPostCateC(POSTCATEC);
            repository.setFixPostMat(FIXPOSTMAT);
            repository.setFixPostMatC(FIXPOSTMATC);
            repository.setPostCap(POSTCAP);
            repository.setPostCapC(POSTCAPC);
            repository.setReptAddr(REPTADDR);
            repository.setReptAddrC(REPTADDRC);
            repository.setRemark(REMARK);
            List<Repository> listR = repositoryMtMapper.SearchRepositoryMt(repository);
            return new PageInfo<>(listR);
        }
    }

    @Override
    public void changeRepositoryMt(HttpServletRequest request) {
        int onlyIdR = parseInt(request.getParameter("onlyId"));
        Repository repository = new Repository();
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptName(request.getParameter("reptName"));
        repository.setReptAdmin(request.getParameter("reptAdmin"));
        repository.setReptState(request.getParameter("reptState"));
        repository.setPostManage(request.getParameter("postManage"));
        String postId = "";
        String postName = "";
        String postCate = "";
        if (request.getParameter("postManage").equals("否")) {
            postId = "无";
            postName = "无";
            postCate = "无";
        }else {
            postId = request.getParameter("postId");
            postName = request.getParameter("postName");
            postCate = request.getParameter("postCate");
        }
        repository.setPostId(postId);
        repository.setPostName(postName);
        repository.setPostCate(postCate);
        repository.setFixPostMat(request.getParameter("fixPostMat"));
        repository.setPostCap(request.getParameter("postCap"));
        repository.setReptAddr(request.getParameter("reptAddr"));
        repository.setRemark(request.getParameter("remark"));
        repository.setOnlyIdR(onlyIdR);
        repositoryMtMapper.saveRepositoryMt(repository);
    }

    @Override
    public void insertRepositoryMt(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptName(request.getParameter("reptName"));
        repository.setReptAdmin(request.getParameter("reptAdmin"));
        repository.setReptState(request.getParameter("reptState"));
        repository.setPostManage(request.getParameter("postManage"));
        repository.setPostId(request.getParameter("postId"));
        repository.setPosition(request.getParameter("postId"));
        repository.setPostName(request.getParameter("postName"));
        repository.setPostCate(request.getParameter("postCate"));
        repository.setFixPostMat(request.getParameter("fixPostMat"));
        repository.setPostCap(request.getParameter("postCap"));
        repository.setReptAddr(request.getParameter("reptAddr"));
        repository.setRemark(request.getParameter("remark"));
        String A;
        if (repositoryMapper.theCategory(repository).isEmpty()){
            A = request.getParameter("reptCategory");
        }else {
            A = repositoryMapper.theCategory(repository).get(0).toString();
        }
        if (repositoryMapper.Position(repository) == null) {
            if (request.getParameter("reptCategory").equals(A)) {
                repositoryMtMapper.newRepositoryMt(repository);
            }
        }
    }

    @Override
    public void deleteRepositoryMt(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setOnlyIdR(parseInt(request.getParameter("onlyIdR")));
        repositoryMtMapper.deleteRepositoryMt(repository);
    }

    @Override
    public List<Material> MatList() {
        List<Material> matList = repositoryMtMapper.matlist();
        return matList;
    }

    @Override
    public void updFix(HttpServletRequest request) {
        String id = request.getParameter("id");
        Material material = new Material();
        material.setId(id);
        repositoryMtMapper.updMatFix(material);
    }

    @Override
    public void updOtherFix(HttpServletRequest request) {
        String id = request.getParameter("id");
        Material material = new Material();
        material.setId(id);
        repositoryMtMapper.updOtherFix(material);
    }

    @Override
    public List<User> username(){
        List<User> selectUser = iUserDao.selectAll();
        return selectUser;
    }
}
