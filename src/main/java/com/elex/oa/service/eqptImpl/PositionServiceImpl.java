package com.elex.oa.service.eqptImpl;

import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.dao.eqptDao.MaterialMtMapper;
import com.elex.oa.dao.eqptDao.PositionMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.PositionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private PositionMapper positionMapper;

    // 显示所有库位
    @Override
    public PageInfo<Repository> showPosition(Page page,HttpServletRequest request){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        Repository repository = new Repository();
        repository.setReptAdmin(request.getParameter("username"));
        List<Repository> listP = positionMapper.PositionList(repository);
        return new PageInfo<>(listP);
    }

    // 查寻库位
    @Override
    public PageInfo<Repository> searchPosition(Page page, HttpServletRequest request){
        String REPTID = request.getParameter("reptId");
        String REPTIDC = request.getParameter("reptIdC");
        String REPTNAME = request.getParameter("reptName");
        String REPTNAMEC = request.getParameter("reptNameC");
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
        String REMARK = request.getParameter("remark");
        String REPTADMIN = request.getParameter("reptAdmin");
        if (  REPTID == null && REPTNAME == null && POSTID == null && POSTNAME == null && REMARK == null && POSTCATE == null && FIXPOSTMAT == null && POSTCAP == null  ){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setReptAdmin(request.getParameter("username"));
            List<Repository> listP = positionMapper.PositionList(repository);
            return new PageInfo<>(listP);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setReptId(REPTID);
            repository.setReptIdC(REPTIDC);
            repository.setReptName(REPTNAME);
            repository.setReptNameC(REPTNAMEC);
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
            repository.setRemark(REMARK);
            repository.setReptAdmin(REPTADMIN);
            List<Repository> listP = positionMapper.searchPosition(repository);
            return new PageInfo<>(listP);
        }
    }

    // 插入新的库位
    @Override
    public String insertPosition(Repository repository, HttpServletRequest request) {
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptName(request.getParameter("reptName"));
        repository.setPostId(request.getParameter("postId"));
        repository.setPostName(request.getParameter("postName"));
        repository.setPostCate(request.getParameter("postCate"));
        repository.setFixPostMat(request.getParameter("fixPostMat"));
        repository.setPostCap(request.getParameter("postCap"));
        repository.setRemark("无");
        repository.setReptState("是");
        String postId = positionMapper.checkPostId(repository);
        if (postId == null){
            positionMapper.insertPosition(repository);
            return "1";
        }else{
            return "0";
        }
    }

    // 更新库位信息
    @Override
    public void changePosition(Repository repository, HttpServletRequest request) {
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptName(request.getParameter("reptName"));
        repository.setPostId(request.getParameter("postId"));
        repository.setPostName(request.getParameter("postName"));
        repository.setPostCate(request.getParameter("postCate"));
        repository.setFixPostMat(request.getParameter("fixPostMat"));
        repository.setPostCap(request.getParameter("postCap"));
        repository.setRemark(request.getParameter("remark"));
        String reptState;
        String result = positionMapper.showPosition(repository);
        if (result.equals("是")){
            reptState = "是";
        } else {
            reptState = "否";
        }
        repository.setReptState(reptState);
        /*String position;
        String result = repositoryMapper.managePost(repository);
        if (result.equals("否")) {
            position = "无";
        }else {
            position = request.getParameter("position");
        }
        repository.setPosition(position);
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setNum(request.getParameter("num"));*/
        repository.setOnlyIdP( Integer.parseInt(request.getParameter("onlyIdP")) );
        positionMapper.changePosition(repository);
        Repository repository1 = positionMapper.locationPosition(repository);
        /*Material material = new Material();
        material.setReptId(repository1.getReptId());
        material.setPostId(repository1.getPostId());*/
        String reptId = repository1.getReptId();
        String postId = repository1.getPostId();
        String position = request.getParameter("postId");
        materialMapper.updateDetail(reptId,postId,position);
    }


    // 是否有记录
    @Override
    public String record(HttpServletRequest request){
        Repository repository = new Repository();
        repository.setPostId(request.getParameter("postId"));
        String resultin = positionMapper.recordin(repository);
        String resultout = positionMapper.recordout(repository);
        String resultshift = positionMapper.recordshift(repository);
        // 有记录返回1
        if ( resultin == null && resultout == null && resultshift == null){
            return "0";
        } else{
            return "1";
        }
    }

    // 删除库位
    @Override
    public void deleteRepository(Repository repository, HttpServletRequest request) {
        repository.setOnlyIdP( Integer.parseInt(request.getParameter("onlyIdP")) );
        positionMapper.deletePosition(repository);
    }

    @Override
    public List<Repository> ReptList(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setReptAdmin(request.getParameter("username"));
        List<Repository> reptlist = positionMapper.reptlist(repository);
        return reptlist;
    }

    @Override
    public List<Repository> ReptName(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setReptId(request.getParameter("reptId"));
        List<Repository> result = positionMapper.reptname(repository);
        return result;
    }
}
