package com.elex.oa.service.eqptImpl;

import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.service.eqptService.MaterialService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

@Service
public class MaterialImpl implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;

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
        /*List<String> listID = null;
        if (ID.length() > 2) {
            JSONArray jsonArrayID = JSONArray.fromObject(ID);
            listID = (List) JSONArray.toCollection(jsonArrayID);
        }*/
        String IDC = request.getParameter("idC");
        String NAME = request.getParameter("name");
        /*List<String> listName = null;
        if (NAME.length() > 2) {
            JSONArray jsonArrayNAME = JSONArray.fromObject(NAME);
            listName = (List) JSONArray.toCollection(jsonArrayNAME);
        }*/
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
        /*String POSITION = request.getParameter("position");
        String POSITIONC = request.getParameter("positionC");*/
        String NUM = request.getParameter("num");
        String NUMC = request.getParameter("numC");
        String UNIT = request.getParameter("unit");
        String UNITC = request.getParameter("unitC");
        String BSMANAGE = request.getParameter("BSManage");
        String BSMANAGEC = request.getParameter("BSManageC");
        String NEEDCHECK = request.getParameter("needCheck");
        String NEEDCHECKC = request.getParameter("needCheckC");
        String MATERIALSTATE = request.getParameter("materialState");
        String MATERIALSTATEC = request.getParameter("materialStateC");
        String SINGLEMANAGE = request.getParameter("singleManage");
        String SINGLEMANAGEC = request.getParameter("singleManageC");
        String NOTSINGLE = request.getParameter("notSingle");
        String NOTSINGLEC = request.getParameter("notSingleC");
        if (ID.equals("") && NAME.equals("") && SPEC.equals("") && MAT.equals("") && BRAND.equals("") && CATEGORY.equals("") && MAXLIMIT.equals("") && MINLIMIT.equals("") && UNIT.equals("") && NUM.equals("") && PRICE.equals("") && REMARK.equals("") && SPEC.equals("") && NOTSINGLE.equals("") && SINGLEMANAGE.equals("") && BSMANAGE.equals("") && NEEDCHECK.equals("") && MATERIALSTATE.equals("")) {
            PageHelper.startPage(page.getCurrentPage(), page.getRows());
            List<Material> listM = materialMapper.MaterialList();
            return new PageInfo<>(listM);
        }else {
            Material material = new Material();
            material.setId(ID);
            material.setIdC(IDC);
            material.setName(NAME);
            material.setNameC(NAMEC);
            /*material.setsDate(SDATE);
            material.seteDate(EDATE);*/
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
            /*material.setPosition(POSITION);
            material.setPositionC(POSITIONC);*/
            material.setUnit(UNIT);
            material.setUnitC(UNITC);
            material.setNum(NUM);
            material.setNumC(NUMC);
            material.setBSManage(BSMANAGE);
            material.setBSManageC(BSMANAGEC);
            material.setNeedCheck(NEEDCHECK);
            material.setNeedCheckC(NEEDCHECKC);
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
        /*material.setPosition(request.getParameter("position"));*/
        material.setBrand(request.getParameter("brand"));
        material.setBSManage(request.getParameter("BSManage"));
        material.setNeedCheck(request.getParameter("needCheck"));
        material.setMaterialState(request.getParameter("materialState"));
        material.setSingleManage(request.getParameter("singleManage"));
        material.setNotSingle(request.getParameter("notSingle"));
        /*String date = "";
        String sDate = "";
        if (request.getParameter("date") == null ){
            System.out.println("...");
        } else {
            date = request.getParameter("date");
            date = date.replace("Z", " UTC");//注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            sDate = sdf.format(d);
        }
        material.setDate(sDate);*/
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        material.setPrice(request.getParameter("price"));
        /*material.setNum(request.getParameter("num"));*/
        material.setUnit(request.getParameter("unit"));
        material.setOnlyId(onlyId);
        /*String State = "";
        if (request.getParameter("materialState") == null){
            State = "启用";
        }else {
            State = request.getParameter("materialState");
        }
        material.setMaterialState(State);*/
        materialMapper.saveMaterial(material);
        Material material1 = new Material();
        material1.setId(request.getParameter("id"));
        material1.setMaterialState(request.getParameter("materialState"));
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
        /*material.setPosition(request.getParameter("position"));*/
        material.setBrand(request.getParameter("brand"));
        material.setPrice(request.getParameter("price"));
        material.setNum("0");
        material.setUnit(request.getParameter("unit"));
        /*String date = request.getParameter("date");
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date d = format.parse(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(d);
        material.setDate(sDate);*/
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        material.setBSManage(request.getParameter("BSManage"));
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
