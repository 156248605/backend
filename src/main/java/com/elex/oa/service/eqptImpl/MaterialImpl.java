package com.example.oa_file.service.impl;

import com.example.oa_file.entity.Material;
import com.example.oa_file.entity.Page;
import com.example.oa_file.entity.Repository;
import com.example.oa_file.mapper.MaterialMapper;
import com.example.oa_file.service.MaterialService;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Param;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NavigableMap;

@Service
public class MaterialImpl implements MaterialService{

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
        String SN = request.getParameter("sn");
        String BN = request.getParameter("bn");
        String PARTNER = request.getParameter("partner");
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        if (ID.equals("") && SN.equals("") && BN.equals("") && PARTNER.equals("")){
            List<Material> listM = materialMapper.MaterialList();
            return new PageInfo<>(listM);
        }else {
            Material material = new Material();
            material.setId(ID);
            material.setSn(SN);
            material.setBn(BN);
            material.setPartner(PARTNER);
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
    public void saveMaterial (HttpServletRequest request) {
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
        material.setDate(request.getParameter("date"));
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        materialMapper.saveMaterial(material);
    }

    // 删除物料
    @Override
    public void deleteMaterial (HttpServletRequest request){
        Material material = new Material();
        material.setId(request.getParameter("id"));
        material.setSn(request.getParameter("sn"));
        material.setBn(request.getParameter("bn"));
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
        String date = request.getParameter("date");
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        Date d = format.parse(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(d);
        material.setDate(sDate);
        material.setMaxlimit(request.getParameter("maxlimit"));
        material.setMinlimit(request.getParameter("minlimit"));
        String SN = materialMapper.searchSN(material);
        if ( SN != request.getParameter("sn") || SN == "无" ){
            materialMapper.newMaterial(material);
            return "0";
        }else {
            return "1";
        }
    }
}
