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
        String IDC = request.getParameter("idC");
        String NAME = request.getParameter("name");
        String NAMEC = request.getParameter("nameC");
        String BN = request.getParameter("bn");
        String BNC = request.getParameter("bnC");
        String SN = request.getParameter("sn");
        String SNC = request.getParameter("snC");
        String MAT = request.getParameter("material");
        String MATC = request.getParameter("materialC");
        String SPEC = request.getParameter("spec");
        String SPECC = request.getParameter("specC");
        String BRAND = request.getParameter("brand");
        String BRANDC = request.getParameter("brandC");
        String SDATE = "";
        String EDATE = "";
        if (request.getParameter("date").length() > 10) {
            SDATE = request.getParameter("date").substring(2,12);
            EDATE = request.getParameter("date").substring(15,25);
        }
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
        String POSITION = request.getParameter("position");
        String POSITIONC = request.getParameter("positionC");
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        if (ID.equals("") && NAME.equals("") && CATEGORY.equals("") && PRICE.equals("") && REMARK.equals("") && PARTNER.equals("") && MAXLIMIT.equals("") && MINLIMIT.equals("") && POSITION.equals("") && SN.equals("") && BN.equals("") && request.getParameter("date").length() <= 10 && BRAND.equals("") && MAT.equals("") && SPEC.equals("") ){
            List<Material> listM = materialMapper.MaterialList();
            return new PageInfo<>(listM);
        }else {
            Material material = new Material();
            material.setId(ID);
            material.setIdC(IDC);
            material.setSn(SN);
            material.setSn(SNC);
            material.setBn(BN);
            material.setBnC(BNC);
            material.setName(NAME);
            material.setNameC(NAMEC);
            material.setsDate(SDATE);
            material.seteDate(EDATE);
            material.setSpec(SPEC);
            material.setSpecC(SPECC);
            material.setMaterial(MAT);
            material.setMaterialC(MATC);
            material.setBrand(BRAND);
            material.setBnC(BRANDC);
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
            material.setPosition(POSITION);
            material.setPositionC(POSITIONC);
            List<Material> listM = materialMapper.SearchMaterial(material);
            return new PageInfo<>(listM);
        }
    }

    // 修改物料(获取修改前信息)
    @Override
    public Material changeMaterial (HttpServletRequest request) {
        Material material = new Material();
        material.setId(request.getParameter("id"));
        material.setBn(request.getParameter("bn"));
        material.setSn(request.getParameter("sn"));
        Material material1 = materialMapper.MaterialId(material);
        return material1;
    }

    // 修改物料(保存修改后数据)
    @Override
    public void saveMaterial (HttpServletRequest request) throws ParseException{
        int onlyId = parseInt(request.getParameter("onlyId"));
        Material material = new Material();
        material.setId(request.getParameter("id"));
        material.setPartner(request.getParameter("partner"));
        material.setBn(request.getParameter("bn"));
        material.setSn(request.getParameter("sn"));
        material.setName(request.getParameter("name"));
        material.setSpec(request.getParameter("spec"));
        material.setCategory(request.getParameter("category"));
        material.setMaterial(request.getParameter("material"));
        material.setPosition(request.getParameter("position"));
        material.setBrand(request.getParameter("brand"));
        String date = request.getParameter("date");
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date d = format.parse(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(d);
        material.setDate(sDate);
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        material.setPrice(request.getParameter("price"));
        material.setOnlyId(onlyId);
        materialMapper.saveMaterial(material);
    }

    // 删除物料
    @Override
    public void deleteMaterial (HttpServletRequest request){
        Material material = new Material();
        material.setOnlyId( parseInt(request.getParameter("onlyId")) );
        materialMapper.deleteMaterial(material);
    }

    // 插入物料
    @Override
    public String insertMaterial(Material material, HttpServletRequest request)throws ParseException {
        material.setId(request.getParameter("id"));
        material.setPartner(request.getParameter("partner"));
        material.setBn(request.getParameter("bn"));
        material.setSn(request.getParameter("sn"));
        material.setName(request.getParameter("name"));
        material.setSpec(request.getParameter("spec"));
        material.setCategory(request.getParameter("category"));
        material.setMaterial(request.getParameter("material"));
        material.setPosition(request.getParameter("position"));
        material.setBrand(request.getParameter("brand"));
        material.setPrice(request.getParameter("price"));
        String date = request.getParameter("date");
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date d = format.parse(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(d);
        material.setDate(sDate);
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        String SN = materialMapper.searchSN(material).toString();
        if ( SN != request.getParameter("sn") || SN == "无" ){
            materialMapper.newMaterial(material);
            return "0";
        }else {
            return "1";
        }
    }
}
