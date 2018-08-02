package com.elex.oa.service.eqptImpl;


import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.dao_shiyun.IUserDao;
import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.RepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Service
public class RepositoryImpl implements RepositoryService {

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private IUserDao iUserDao;
    // 显示所有仓库
    @Override
    public PageInfo<Repository> showRepository(Page page){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> listR = repositoryMapper.RepositoryList();
        return new PageInfo<>(listR);
    }

    // 查询仓库
    @Override
    public PageInfo<Repository> searchRepository(Page page,HttpServletRequest request){
        /*String reptId = request.getParameter("reptId");
        String reptIdC = request.getParameter("reptIdC");
        String position = request.getParameter("position");
        String positionC = request.getParameter("positionC");
        String materialId = request.getParameter("materialId");
        String materialIdC = request.getParameter("materialIdC");
        String num = request.getParameter("num");
        String numC = request.getParameter("numC");*/
        String REPTCATEGORY = request.getParameter("reptCategory");
        String REPTCATEGORYC = request.getParameter("reptCategoryC");
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
        /*String POSTID = request.getParameter("postId");
        String POSTIDC = request.getParameter("postIdC");
        String POSTNAME = request.getParameter("postName");
        String POSTNAMEC = request.getParameter("postNameC");
        String POSTCATE = request.getParameter("postCate");
        String POSTCATEC = request.getParameter("postCateC");
        String FIXPOSTMAT = request.getParameter("fixPostMat");
        String FIXPOSTMATC = request.getParameter("fixPostMatC");
        String POSTCAP = request.getParameter("postCap");
        String POSTCAPC = request.getParameter("postCapC");*/
        String REPTADDR = request.getParameter("reptAddr");
        String REPTADDRC = request.getParameter("reptAddrC");
        String REMARK = request.getParameter("remark");
        if (  REPTID.equals("") && REPTNAME.equals("") && REPTADMIN.equals("") && REPTSTATE.equals("") && REMARK.equals("") && POSTMANAGE.equals("") &&/* POSTID.equals("") && POSTNAME.equals("") && POSTCATE.equals("") && FIXPOSTMAT.equals("") && POSTCAP.equals("") &&*/ REPTADDR.equals("") && REPTCATEGORY.equals("")  ){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = repositoryMapper.RepositoryList();
            return new PageInfo<>(listR);
        } else {
            Repository repository = new Repository();
            /*repository.setNum(num);
            repository.setNumC(numC);
            repository.setReptId(reptId);
            repository.setReptIdC(reptIdC);
            repository.setPosition(position);
            repository.setPositionC(positionC);
            repository.setMaterialId(materialId);
            repository.setMatIdC(materialIdC);*/
            repository.setReptCategory(REPTCATEGORY);
            repository.setReptCateC(REPTCATEGORYC);
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
            /*repository.setPostId(POSTID);
            repository.setPostIdC(POSTIDC);
            repository.setPostName(POSTNAME);
            repository.setPostNameC(POSTNAMEC);
            repository.setPostCate(POSTCATE);
            repository.setPostCateC(POSTCATEC);
            repository.setFixPostMat(FIXPOSTMAT);
            repository.setFixPostMatC(FIXPOSTMATC);
            repository.setPostCap(POSTCAP);
            repository.setPostCapC(POSTCAPC);*/
            repository.setReptAddr(REPTADDR);
            repository.setReptAddrC(REPTADDRC);
            repository.setRemark(REMARK);
            List<Repository> listR = repositoryMapper.searchRepository(repository);
            return new PageInfo<>(listR);
        }
    }

    // 插入新的仓库信息
    @Override
    public String insertRepository(Repository repository,HttpServletRequest request) {
        repository.setReptCategory(request.getParameter("reptCategory"));
        /*repository.setReptId(request.getParameter("reptId"));
        repository.setPosition(request.getParameter("position"));
        repository.setNum(request.getParameter("num"));
        repository.setMaterialId(request.getParameter("materialId"));*/
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptName(request.getParameter("reptName"));
        repository.setReptAdmin(request.getParameter("reptAdmin"));
        repository.setReptState(request.getParameter("reptState"));
        repository.setPostManage(request.getParameter("postManage"));
        /*repository.setPostId(request.getParameter("postId"));
        repository.setPostName(request.getParameter("postName"));
        repository.setPostCate(request.getParameter("postCate"));
        repository.setFixPostMat(request.getParameter("fixPostMat"));
        repository.setPostCap(request.getParameter("postCap"));*/
        repository.setReptAddr(request.getParameter("reptAddr"));
        String A;
        if (repositoryMapper.theCategory(repository).isEmpty()){
            A = request.getParameter("reptCategory");
        }else {
            A = repositoryMapper.theCategory(repository).get(0).toString();
        }
        repository.setPrice("0");
        repository.setNum("0");
        repository.setMaterialName("无");
        repository.setSpec("无");
        repository.setCategory("无");
        repository.setRemark("无");
        repository.setMaterialId("无");
        if (request.getParameter("reptCategory").equals(A)) {
            repositoryMapper.insertRepository(repository);
            return "1";
        }else{
            return "2";
        }
    }

    // 更新仓库信息
    @Override
    public void changeRepository(Repository repository,HttpServletRequest request) {
        repository.setReptCategory(request.getParameter("reptCategory"));
        repository.setReptId(request.getParameter("reptId"));
        repository.setReptName(request.getParameter("reptName"));
        repository.setReptAdmin(request.getParameter("reptAdmin"));
        repository.setReptState(request.getParameter("reptState"));
        repository.setPostManage(request.getParameter("postManage"));
        /*repository.setPostId(request.getParameter("postId"));
        repository.setPostName(request.getParameter("postName"));
        repository.setPostCate(request.getParameter("postCate"));
        repository.setFixPostMat(request.getParameter("fixPostMat"));
        repository.setPostCap(request.getParameter("postCap"));*/
        repository.setReptAddr(request.getParameter("reptAddr"));
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
        repository.setOnlyIdR( Integer.parseInt(request.getParameter("onlyIdR")) );
        repositoryMapper.changeRepository(repository);
        Repository repository1 = new Repository();
        repository1.setReptState(request.getParameter("reptState"));
        repository1.setReptId(request.getParameter("reptId"));
        repositoryMapper.updPostState(repository);
        if (request.getParameter("postManage").equals("")) {
            Repository repository2 = new Repository();
            String postId = "";
            repository2.setPostId(postId);
            repository2.setReptId(request.getParameter("reptId"));
            repositoryMapper.changeManagePost(repository2);
            repositoryMapper.changeDetail(repository2);
        }
    }


    @Override
    public void deleteRepository(Repository repository,HttpServletRequest request) {
        repository.setOnlyIdR( Integer.parseInt(request.getParameter("onlyIdR")) );
        repositoryMapper.deleteRepository(repository);
    }

    // 获取仓库ID
    @Override
    public List<Repository> ReptList() {
        List<Repository> reptlist = repositoryMapper.reptlist();
        return reptlist;
    }

    // 获取库位
    @Override
    public List<Repository> getPost(HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setReptId(request.getParameter("reptId"));
        List<Repository> postlist = repositoryMapper.getPost(repository);
        return postlist;
    }

    @Override
    public List<Repository> matInRept(HttpServletRequest request) {
        List<Repository> reptList = null;
        String LIST = "["+request.getParameter("inList")+"]";
        List<HashMap> list = JSON.parseArray(LIST, HashMap.class);
        Material material = new Material();
        material.setId(list.get(0).get("theMatId").toString());
        reptList = repositoryMapper.matInRept(material);
        return reptList;
    }

    @Override
    public List<Repository> matInPost(HttpServletRequest request) {
        List<Repository> reptList = null;
        String LIST = "["+request.getParameter("inList")+"]";
        List<HashMap> list = JSON.parseArray(LIST, HashMap.class);
        Material material = new Material();
        material.setId(list.get(0).get("theMatId").toString());
        String reptId = "";
        if (list.get(0).containsKey("reptId")){
            reptId = list.get(0).get("reptId").toString();
        }
        if (list.get(0).containsKey("inRept")){
            reptId = list.get(0).get("inRept").toString();
        }
        material.setReptId(reptId);
        reptList = repositoryMapper.matInPost(material);
        if (reptList.size() == 0){
            reptList = repositoryMapper.matInPostReplace();
        }
        return reptList;
    }

    @Override
    public List<Repository> matOutRept(HttpServletRequest request) {
        List<Repository> reptList = null;
        String LIST = "["+request.getParameter("outList")+"]";
        List<HashMap> list = JSON.parseArray(LIST, HashMap.class);
        Material material = new Material();
        material.setId(list.get(0).get("theMatId").toString());
        reptList = repositoryMapper.matOutRept(material);
        return reptList;
    }

    @Override
    public List<Repository> matOutPost(HttpServletRequest request) {
        List<Repository> reptList = null;
        String LIST = "["+request.getParameter("outList")+"]";
        List<HashMap> list = JSON.parseArray(LIST, HashMap.class);
        Material material = new Material();
        material.setId(list.get(0).get("theMatId").toString());
        String reptId = "";
        if (list.get(0).containsKey("reptId")){
            reptId = list.get(0).get("reptId").toString();
        }
        if (list.get(0).containsKey("outRept")){
            reptId = list.get(0).get("outRept").toString();
        }
        material.setReptId(reptId);
        reptList = repositoryMapper.matOutPost(material);
        return reptList;
    }

    // 获取库位
    @Override
    public List<Repository> PostList() {
        List<Repository> postlist = repositoryMapper.postlist();
        return postlist;
    }

    public List<Material> MatList() {
        List<Material> matList = repositoryMapper.matlist();
        return matList;
    }

    public List<User> username() {
        List<User> selectUser = iUserDao.selectAll();
        return selectUser;
    }





   /* @Override
    public List<Repository> matInPost(HttpServletRequest request) {
        List<Repository> postList = null;
        String LIST = request.getParameter("inList");
        List<HashMap> list =JSON.parseArray(LIST, HashMap.class);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).get("theMatId").toString());
            Material material = new Material();
            material.setId(list.get(i).get("theMatId").toString());
            material.setBn(list.get(i).get("theMatBnSn").toString());
            material.setSn(list.get(i).get("theMatBnSn").toString());
            Material material1 = materialMapper.lockBn(material);
            Material material2 = materialMapper.lockSn(material);
            String bn = null;
            String sn = null;
            if (material1 != null){
                bn = list.get(i).get("theMatBnSn").toString();
                sn = "无";
            }else if (material2 != null){
                sn = list.get(i).get("theMatBnSn").toString();
                bn = "无";
            }else {
                sn = "无";
                bn = "无";
            }
            repository.setSn(sn);
            repository.setBn(bn);
            postList = repositoryMapper.matInPost(repository);
        }
        return postList;
    }

    @Override
    public List<Repository> matOutPost(HttpServletRequest request) {
        List<Repository> postList = null;
        String LIST = request.getParameter("outList");
        List<HashMap> list =JSON.parseArray(LIST, HashMap.class);
        for (int i = 0; i < list.size(); i++) {
            Repository repository = new Repository();
            repository.setMaterialId(list.get(i).get("theMatId").toString());
            Material material = new Material();
            material.setId(list.get(i).get("theMatId").toString());
            material.setBn(list.get(i).get("theMatBnSn").toString());
            material.setSn(list.get(i).get("theMatBnSn").toString());
            Material material1 = materialMapper.lockBn(material);
            Material material2 = materialMapper.lockSn(material);
            String bn = null;
            String sn = null;
            if (material1 != null){
                bn = list.get(i).get("theMatBnSn").toString();
                sn = "无";
            }else if (material2 != null){
                sn = list.get(i).get("theMatBnSn").toString();
                bn = "无";
            }else {
                sn = "无";
                bn = "无";
            }
            repository.setSn(sn);
            repository.setBn(bn);
            postList = repositoryMapper.matOutPost(repository);
        }
        return postList;
    }*/
}


