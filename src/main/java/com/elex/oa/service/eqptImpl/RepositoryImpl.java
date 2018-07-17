package com.elex.oa.service.eqptImpl;


import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.InRepositoryMapper;
import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.RepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Service
public class RepositoryImpl implements RepositoryService {

    @Resource
    private RepositoryMapper repositoryMapper;

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
        String reptId = request.getParameter("reptId");
        String reptIdC = request.getParameter("reptIdC");
        String reptCategory = request.getParameter("reptCategory");
        String reptCategoryC = request.getParameter("reptCategoryC");
        String position = request.getParameter("position");
        String positionC = request.getParameter("positionC");
        String materialId = request.getParameter("materialId");
        String materialIdC = request.getParameter("materialIdC");
        String num = request.getParameter("num");
        String numC = request.getParameter("numC");
        if ( !reptIdC.equals("") || !reptCategory.equals("") || !positionC.equals("") || !materialIdC.equals("") || !numC.equals("") ){
            Repository repository = new Repository();
            repository.setNum(num);
            repository.setNumC(numC);
            repository.setReptId(reptId);
            repository.setReptIdC(reptIdC);
            repository.setReptCategory(reptCategory);
            repository.setReptCateC(reptCategoryC);
            repository.setPosition(position);
            repository.setPositionC(positionC);
            repository.setMaterialId(materialId);
            repository.setMatIdC(materialIdC);
            List<Repository> listR = repositoryMapper.searchRepository(repository);
            return new PageInfo<>(listR);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = repositoryMapper.RepositoryList();
            return new PageInfo<>(listR);
        }
    }

    // 插入新的仓库信息
    @Override
    public String insertRepository(Repository repository,HttpServletRequest request) {
        repository.setReptCategory(request.getParameter("reptCategory"));
        repository.setReptId(request.getParameter("reptId"));
        repository.setPosition(request.getParameter("position"));
        repository.setNum(request.getParameter("num"));
        repository.setMaterialId(request.getParameter("materialId"));
        String A;
        if (repositoryMapper.theCategory(repository).isEmpty()){
            A = request.getParameter("reptCategory");
        }else {
            A = repositoryMapper.theCategory(repository).get(0).toString();
        }
        /*Material material = new Material();
        material.setId(request.getParameter("materialId"));
        material.setSn(request.getParameter("sn"));
        material.setBn(request.getParameter("bn"));
        List<Material> list = repositoryMapper.otherInfo(material);*/
        repository.setPrice("0");
        repository.setMaterialName("无");
        repository.setSpec("无");
        repository.setCategory("无");
        repository.setRemark("无");
        /*Material material = new Material();
        material.setId(request.getParameter("materialId"));
        material.setSn(request.getParameter("sn"));
        material.setBn(request.getParameter("bn"));
        List<Material> listInv = repositoryMapper.invInfo(material);
        repository.setPrice(listInv.get(0).getPrice());
        repository.setSpec(listInv.get(0).getSpec());
        repository.setCategory(listInv.get(0).getCategory());
        repository.setMaterialName(listInv.get(0).getName());*/
        if (repositoryMapper.Position(repository) == null) {
            if (request.getParameter("reptCategory").equals(A)) {
                repositoryMapper.insertRepository(repository);
                return "1";
            }else{
                return "2";
            }
        }else {
            return "0";
        }
    }

    // 更新仓库信息
    @Override
    public void changeRepository(Repository repository,HttpServletRequest request) {
        repository.setReptCategory(request.getParameter("reptCategory"));
        repository.setReptId(request.getParameter("reptId"));
        String position;
        String result = repositoryMapper.managePost(repository);
        if (result.equals("否")) {
            position = "无";
        }else {
            position = request.getParameter("position");
        }
        repository.setPosition(position);
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setNum(request.getParameter("num"));
        repository.setOnlyIdR( Integer.parseInt(request.getParameter("onlyIdR")) );
        repositoryMapper.changeRepository(repository);
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

    /*@Override
    public List<Repository> matInRept(HttpServletRequest request) {
        List<Repository> reptList = null;
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
            reptList = repositoryMapper.matInRept(repository);
        }
        return reptList;
    }

    @Override
    public List<Repository> matOutRept(HttpServletRequest request) {
        List<Repository> reptList = null;
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
            reptList = repositoryMapper.matOutRept(repository);
        }
        return reptList;
    }*/

    // 获取库位
    @Override
    public List<Repository> PostList() {
        List<Repository> postlist = repositoryMapper.postlist();
        return postlist;
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


