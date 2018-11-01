package com.elex.oa.service.eqptImpl;

import com.elex.oa.dao.eqptDao.MaterialMtMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.service.eqptService.MaterialMtService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MaterialMtImpl implements MaterialMtService {
    @Resource
    private MaterialMtMapper materialMtMapper;

    @Override
    public PageInfo<Material> showDetail(Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Material> listM = materialMtMapper.detailList();
        return new PageInfo<>(listM);
    }

    @Override
    public PageInfo<Material> searchDetail(Page page, HttpServletRequest request) {
        String ID = request.getParameter("id");
        String IDC = request.getParameter("idC");
        String NAME = request.getParameter("name");
        String NAMEC = request.getParameter("nameC");
        String SPEC = request.getParameter("spec");
        String SPECC = request.getParameter("specC");
        String BRAND = request.getParameter("brand");
        String BRANDC = request.getParameter("brandC");
        String CATEGORY = request.getParameter("category");
        String CATEGORYC = request.getParameter("categoryC");
        String REPTID = request.getParameter("reptId");
        String REPTIDC = request.getParameter("reptIdC");
        String REPTNAME = request.getParameter("reptName");
        String REPTNAMEC = request.getParameter("reptNameC");
        String POSTID = request.getParameter("postId");
        String POSTIDC = request.getParameter("postIdC");
        String NUM = request.getParameter("num");
        String NUMC = request.getParameter("numC");
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        if (ID.equals("") && NAME.equals("") && CATEGORY.equals("") && REPTID.equals("") && SPEC.equals("") && BRAND.equals("") && POSTID.equals("") && NUM.equals("") && REPTNAME.equals("")){
            List<Material> listM = materialMtMapper.detailList();
            return new PageInfo<>(listM);
        }else {
            Material material = new Material();
            material.setId(ID);
            material.setIdC(IDC);
            material.setName(NAME);
            material.setNameC(NAMEC);
            material.setSpec(SPEC);
            material.setSpecC(SPECC);
            material.setBrand(BRAND);
            material.setBrandC(BRANDC);
            material.setCategory(CATEGORY);
            material.setCategoryC(CATEGORYC);
            material.setReptId(REPTID);
            material.setReptIdC(REPTIDC);
            material.setReptName(REPTNAME);
            material.setReptNameC(REPTNAMEC);
            material.setReptId(REPTID);
            material.setPostId(POSTID);
            material.setPostIdC(POSTIDC);
            material.setNum(NUM);
            material.setNumC(NUMC);
            List<Material> listM = materialMtMapper.searchDetail(material);
            return new PageInfo<>(listM);
        }
    }

    // 是否有记录
    @Override
    public String record (HttpServletRequest request){
        Material material = new Material();
        material.setId(request.getParameter("materialId"));
        String resultin = materialMtMapper.recordin(material);
        String resultout = materialMtMapper.recordout(material);
        String resultshift = materialMtMapper.recordshift(material);
        // 有记录返回1
        if ( resultin == null && resultout == null && resultshift == null){
            return "0";
        } else{
            return "1";
        }
    }

    /*@Override
    public Material changeMaterialMt(HttpServletRequest request) {
        Material material = new Material();
        material.setOnlyId( parseInt(request.getParameter("onlyId")) );
        Material material1 = materialMtMapper.MaterialMtId(material);
        return material1;
    }

    @Override
    public void saveMaterialMt(HttpServletRequest request) {
        int onlyId = parseInt(request.getParameter("onlyId"));
        Material material = new Material();
        material.setId(request.getParameter("id"));
        material.setPartner(request.getParameter("partner"));
        material.setName(request.getParameter("name"));
        material.setSpec(request.getParameter("spec"));
        material.setCategory(request.getParameter("category"));
        material.setMaterial(request.getParameter("material"));
        material.setBrand(request.getParameter("brand"));
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        material.setPrice(request.getParameter("price"));
        material.setRemark(request.getParameter("remark"));
        material.setBSManage(request.getParameter("BSManage"));
        material.setNeedCheck(request.getParameter("needCheck"));
        material.setMaterialState(request.getParameter("materialState"));
        material.setSingleManage(request.getParameter("singleManage"));
        material.setNotSingle(request.getParameter("notSingle"));
        material.setOnlyId(onlyId);
        materialMtMapper.saveMaterialMt(material);
    }

    @Override
    public void deleteMaterialMt(HttpServletRequest request) {
        Material material = new Material();
        material.setOnlyId( parseInt(request.getParameter("onlyId")) );
        materialMtMapper.deleteMaterialMt(material);
    }

    @Override
    public void insertMaterialMt(Material material, HttpServletRequest request) {
        material.setId(request.getParameter("id"));
        material.setPartner(request.getParameter("partner"));
        material.setName(request.getParameter("name"));
        material.setSpec(request.getParameter("spec"));
        material.setCategory(request.getParameter("category"));
        material.setMaterial(request.getParameter("material"));
        material.setBrand(request.getParameter("brand"));
        material.setPrice(request.getParameter("price"));
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        material.setRemark(request.getParameter("remark"));
        material.setBSManage(request.getParameter("BSManage"));
        material.setNeedCheck(request.getParameter("needCheck"));
        material.setMaterialState(request.getParameter("materialState"));
        material.setSingleManage(request.getParameter("singleManage"));
        material.setNotSingle(request.getParameter("notSingle"));
        materialMtMapper.newMaterialMt(material);
    }



    // 是否是启用
    @Override
    public String state (HttpServletRequest request){
        Material material = new Material();
        material.setId(request.getParameter("materialId"));
        String result = materialMtMapper.state(material);
        // 启用返回1,停用返回0
        if ( result != null ){
            return "1";
        } else{
            return "0";
        }
    }*/

}
