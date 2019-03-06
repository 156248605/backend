package com.elex.oa.service.eqptImpl;


import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.dao.eqptDao.PositionMapper;
import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.hr_entity.personalinformation.User;
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
    private PositionMapper positionMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private IUserDao iUserDao;
    // 显示所有仓库
    @Override
    public PageInfo<Repository> showRepository(Page page,HttpServletRequest request){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        Repository repository = new Repository();
        repository.setReptAdmin(request.getParameter("username"));
        List<Repository> listR = repositoryMapper.RepositoryList(repository);
        return new PageInfo<>(listR);
    }

    // 查询仓库
    @Override
    public PageInfo<Repository> searchRepository(Page page,HttpServletRequest request){
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
        String REPTADDR = request.getParameter("reptAddr");
        String REPTADDRC = request.getParameter("reptAddrC");
        String REMARK = request.getParameter("remark");
        if (  REPTID == null && REPTNAME == null && REPTADMIN == null && REPTSTATE == null && REMARK == null && POSTMANAGE == null && REPTADDR == null && REPTCATEGORY == null  ){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setReptAdmin(request.getParameter("username"));
            List<Repository> listR = repositoryMapper.RepositoryList(repository);
            return new PageInfo<>(listR);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
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
        repository.setReptAddr(request.getParameter("reptAddr"));
        repository.setOnlyIdR( Integer.parseInt(request.getParameter("onlyIdR")) );
        repositoryMapper.changeRepository(repository);
        Repository repository1 = new Repository();
        repository1.setReptState(request.getParameter("reptState"));
        repository1.setReptId(request.getParameter("reptId"));
        repositoryMapper.updPostState(repository);
        if (request.getParameter("postManage").equals("否")) {
            Repository repository2 = new Repository();
            repository2.setReptId(request.getParameter("reptId"));
            positionMapper.deletePostManage(repository2);
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
        material.setReptName(request.getParameter("reptAdmin"));
        String result = repositoryMapper.fixPostMatInfo(material);
        if (result != null) {
            reptList = repositoryMapper.fixRept(material);
        }else {
            reptList = repositoryMapper.matInRept(material);
        }
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
        material.setReptName(request.getParameter("reptAdmin"));
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


    // 是否有记录
    @Override
    public String record(HttpServletRequest request){
        Repository repository = new Repository();
        repository.setReptId(request.getParameter("reptId"));
        String resultin = repositoryMapper.recordin(repository);
        String resultout = repositoryMapper.recordout(repository);
        String resultshift = repositoryMapper.recordshift(repository);
        // 有记录返回1
        if ( resultin == null && resultout == null && resultshift == null){
            return "0";
        } else{
            return "1";
        }
    }

    // 是否批次号序列号管理
    @Override
    public String manageResult(HttpServletRequest request){
        String LIST = request.getParameter("list");
        List<HashMap> list =JSON.parseArray(LIST, HashMap.class);
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            Material material = new Material();
            material.setId(list.get(i).get("theMatId").toString());
            result = repositoryMapper.checkBS(material);
            if (!result.equals("否") && list.get(i).get("theMatBnSn") == null){
                result = "1";
                break;
            }else {
                result = "0";
            }
        }
        return result;
    }

    // 设置仓库类型(类似数据字典)
    @Override
    public List<HashMap<String, Object>> reptCate() {
        List<HashMap<String, Object>> list = repositoryMapper.getCate();
        return list;
    }

    // 判断仓库类型是否重复
    @Override
    public List<HashMap<String, Object>> checkCate(HttpServletRequest request) {
        String cateName = request.getParameter("cateName");
        List<HashMap<String, Object>> list = repositoryMapper.checkCate(cateName);
        return list;
    }

    // 新建仓库类型
    @Override
    public void insertCate(HttpServletRequest request) {
        String cateName = request.getParameter("cateName");
        repositoryMapper.insertCate(cateName);
    }

    // 删除仓库类型
    @Override
    public void deleteCate(HttpServletRequest request) {
        String onlyId = request.getParameter("onlyId");
        repositoryMapper.deleteCate(onlyId);
    }

    @Override
    public String reptCanChangeState (HttpServletRequest request) {
        Repository repository = new Repository();
        repository.setReptId(request.getParameter("reptId"));
        List list = repositoryMapper.reptCanChangeState(repository);
        if (list.size() > 0) {
            return "0";
        }else {
            return "1";
        }
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


