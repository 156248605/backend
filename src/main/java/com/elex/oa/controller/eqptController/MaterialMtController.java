package com.elex.oa.controller.eqptController;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.service.eqptImpl.MaterialMtImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@CrossOrigin
@Controller
@RequestMapping("/materialD")
public class MaterialMtController {

    @Resource
    private MaterialMtImpl materialMtImpl;

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Material> materialList(Page page,HttpServletRequest request){
        PageInfo<Material> list = materialMtImpl.showDetail(page,request);
        return list;
    }

    @RequestMapping("/listInRept")
    @ResponseBody
    public PageInfo<Material> materialInRept(Page page,HttpServletRequest request){
        PageInfo<Material> list = materialMtImpl.materialInRept(page,request);
        return list;
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Material> materialSearch(Page page, HttpServletRequest request){
        PageInfo<Material> list = materialMtImpl.searchDetail(page,request);
        return list;
    }

    @RequestMapping("/record")
    @ResponseBody
    public String record (HttpServletRequest request) {
        return materialMtImpl.record(request);
    }
    /*@RequestMapping("/new")
    @ResponseBody
    public void InsertMaterial (Material material,HttpServletRequest request) throws ParseException {
        materialMtImpl.insertMaterialMt(material,request);
    }

    @RequestMapping("/change")
    @ResponseBody
    public Material materialChange(HttpServletRequest request){
        return materialMtImpl.changeMaterialMt(request);
    }

    @RequestMapping("/save")
    @ResponseBody
    public void saveMaterial(HttpServletRequest request) throws ParseException{
        materialMtImpl.saveMaterialMt(request);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void deleteMaterial(HttpServletRequest request){
        materialMtImpl.deleteMaterialMt(request);
    }



    @RequestMapping("/state")
    @ResponseBody
    public String state (HttpServletRequest request) {
        return materialMtImpl.state(request);
    }*/

}
