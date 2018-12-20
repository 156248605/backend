package com.elex.oa.service.eqptImpl;

import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.dao.eqptDao.MaterialMtMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.service.eqptService.MaterialService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;;
import java.util.*;

import static java.lang.Integer.parseInt;

@Service
public class MaterialImpl implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private MaterialMtMapper materialMtMapper;

    // 物料信息
    @Override
    public PageInfo<Material> showMaterial(Page page){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Material> listM = materialMapper.MaterialList();
        return new PageInfo<>(listM);
    }

    // 查找物料
    @Override
    public PageInfo<Material> searchMaterial(Page page, HttpServletRequest request){
        String ID = request.getParameter("id");
        String IDC = request.getParameter("idC");
        String NAME = request.getParameter("name");
        String NAMEC = request.getParameter("nameC");
        String MAT = request.getParameter("material");
        String MATC = request.getParameter("materialC");
        String SPEC = request.getParameter("spec");
        String SPECC = request.getParameter("specC");
        String BRAND = request.getParameter("brand");
        String BRANDC = request.getParameter("brandC");
        /*String SDATE = "";
        String EDATE = "";
        if (request.getParameter("date").length() > 10) {
            SDATE = request.getParameter("date").substring(2,12);
            EDATE = request.getParameter("date").substring(15,25);
        }*/
        String CATEGORY = request.getParameter("category");
        String CATEGORYC = request.getParameter("categoryC");
        String PRICE = request.getParameter("price");
        String PRICEC = request.getParameter("priceC");
        String REMARK = request.getParameter("remark");
        String PARTNER = request.getParameter("partner");
        String MAXLIMIT = request.getParameter("maxlimit");
        String MAXLIMITC = request.getParameter("maxlimitC");
        String MINLIMIT = request.getParameter("minlimit");
        String MINLIMITC = request.getParameter("minlimitC");
        String NUM = request.getParameter("num");
        String NUMC = request.getParameter("numC");
        String UNIT = request.getParameter("unit");
        String UNITC = request.getParameter("unitC");
        String BSMANAGE = request.getParameter("BSManage");
        String BSMANAGEC = request.getParameter("BSManageC");
        String NEEDCHECK = request.getParameter("needCheck");
        String NEEDCHECKC = request.getParameter("needCheckC");
        String FIXPOSITION = request.getParameter("fixPosition");
        String FIXPOSITIONC = request.getParameter("fixPositionC");
        String MATERIALSTATE = request.getParameter("materialState");
        String MATERIALSTATEC = request.getParameter("materialStateC");
        String SINGLEMANAGE = request.getParameter("singleManage");
        String SINGLEMANAGEC = request.getParameter("singleManageC");
        String NOTSINGLE = request.getParameter("notSingle");
        String NOTSINGLEC = request.getParameter("notSingleC");
        if (ID.equals("") && NAME.equals("") && SPEC.equals("") && MAT.equals("") && BRAND.equals("") && CATEGORY.equals("") && MAXLIMIT.equals("") && MINLIMIT.equals("") && UNIT.equals("") && NUM.equals("") && PRICE.equals("") && REMARK.equals("") && SPEC.equals("") && NOTSINGLE.equals("") && SINGLEMANAGE.equals("") && BSMANAGE.equals("") && FIXPOSITION.equals("") && NEEDCHECK.equals("") && MATERIALSTATE.equals("")) {
            PageHelper.startPage(page.getCurrentPage(), page.getRows());
            List<Material> listM = materialMapper.MaterialList();
            return new PageInfo<>(listM);
        }else {
            PageHelper.startPage(page.getCurrentPage(), page.getRows());
            Material material = new Material();
            material.setId(ID);
            material.setIdC(IDC);
            material.setName(NAME);
            material.setNameC(NAMEC);
            material.setSpec(SPEC);
            material.setSpecC(SPECC);
            material.setMaterial(MAT);
            material.setMaterialC(MATC);
            material.setBrand(BRAND);
            material.setBrandC(BRANDC);
            material.setCategory(CATEGORY);
            material.setCategoryC(CATEGORYC);
            material.setMaxlimit(MAXLIMIT);
            material.setMaxlimitC(MAXLIMITC);
            material.setMinlimit(MINLIMIT);
            material.setMinlimitC(MINLIMITC);
            material.setPartner(PARTNER);
            material.setPrice(PRICE);
            material.setPriceC(PRICEC);
            material.setRemark(REMARK);
            material.setUnit(UNIT);
            material.setUnitC(UNITC);
            material.setNum(NUM);
            material.setNumC(NUMC);
            material.setBSManage(BSMANAGE);
            material.setBSManageC(BSMANAGEC);
            material.setNeedCheck(NEEDCHECK);
            material.setNeedCheckC(NEEDCHECKC);
            material.setFixPosition(FIXPOSITION);
            material.setFixPositionC(FIXPOSITIONC);
            material.setMaterialState(MATERIALSTATE);
            material.setMaterialStateC(MATERIALSTATEC);
            material.setSingleManage(SINGLEMANAGE);
            material.setSingleManageC(SINGLEMANAGEC);
            material.setNotSingle(NOTSINGLE);
            material.setNotSingleC(NOTSINGLEC);
            List<Material> listM = materialMapper.SearchMaterial(material);
            return new PageInfo<>(listM);
        }
    }

    // 是否有记录
    @Override
    public String record(HttpServletRequest request){
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

    // 修改物料(获取修改前信息)
    @Override
    public Material changeMaterial (HttpServletRequest request) {
        Material material = new Material();
        material.setId(request.getParameter("id"));
        Material material1 = materialMapper.MaterialId(material);
        return material1;
    }

    // 修改物料(保存修改后数据)
    @Override
    public void saveMaterial (HttpServletRequest request) {
        int onlyId = parseInt(request.getParameter("onlyId"));
        Material material = new Material();
        material.setId(request.getParameter("id"));
        material.setPartner(request.getParameter("partner"));
        material.setName(request.getParameter("name"));
        material.setSpec(request.getParameter("spec"));
        material.setCategory(request.getParameter("category"));
        material.setMaterial(request.getParameter("material"));
        material.setBrand(request.getParameter("brand"));
        material.setBSManage(request.getParameter("BSManage"));
        material.setFixPosition(request.getParameter("fixPosition"));
        material.setNeedCheck(request.getParameter("needCheck"));
        material.setMaterialState(request.getParameter("materialState"));
        material.setSingleManage(request.getParameter("singleManage"));
        material.setNotSingle(request.getParameter("notSingle"));
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        material.setPrice(request.getParameter("price"));
        material.setUnit(request.getParameter("unit"));
        material.setRemark(request.getParameter("remark"));
        material.setOnlyId(onlyId);
        materialMapper.saveMaterial(material);
        String id = materialMapper.getId(material);
        Material material1 = new Material();
        material1.setId(request.getParameter("id"));
        material1.setMaterialState(request.getParameter("materialState"));
        material1.setName(request.getParameter("name"));
        material1.setSpec(request.getParameter("spec"));
        material1.setCategory(request.getParameter("category"));
        material1.setBrand(request.getParameter("brand"));
        material1.setPrice(request.getParameter("price"));
        material1.setBn(id);
        materialMapper.saveDetail(material1);
    }

    // 删除物料
    @Override
    public void deleteMaterial (HttpServletRequest request){
        Material material = new Material();
        material.setOnlyId( parseInt(request.getParameter("onlyId")) );
        material.setId(request.getParameter("id"));
        materialMapper.deleteMaterial(material);
        materialMapper.deleteDetail(material);
    }

    // 插入物料
    @Override
    public void insertMaterial(Material material, HttpServletRequest request) {
        material.setId(request.getParameter("id"));
        material.setPartner(request.getParameter("partner"));
        material.setName(request.getParameter("name"));
        material.setSpec(request.getParameter("spec"));
        material.setCategory(request.getParameter("category"));
        material.setMaterial(request.getParameter("material"));
        material.setBrand(request.getParameter("brand"));
        material.setPrice(request.getParameter("price"));
        material.setNum("0");
        material.setUnit(request.getParameter("unit"));
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        material.setBSManage(request.getParameter("BSManage"));
        material.setFixPosition(request.getParameter("fixPosition"));
        material.setNeedCheck(request.getParameter("needCheck"));
        material.setMaterialState(request.getParameter("materialState"));
        material.setSingleManage(request.getParameter("singleManage"));
        material.setNotSingle(request.getParameter("notSingle"));
        materialMapper.newMaterial(material);
        materialMapper.newDetail(material);
    }

    @Override
    public String checkId(HttpServletRequest request) {
        Material material = new Material();
        material.setId(request.getParameter("id"));
        Material material1 = materialMapper.MaterialId(material);
        if (material1 == null){
            return "1";
        }else {
            return "0";
        }
    }

}
