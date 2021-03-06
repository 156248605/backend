package com.elex.oa.controller.eqptController;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.service.eqptImpl.MaterialImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/material")
public class MaterialController {

    @Resource
    private MaterialImpl materialImpl;

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Material> materialList(Page page,HttpServletRequest request){
        PageInfo<Material> list = materialImpl.showMaterial(page,request);
        return list;
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Material> materialSearch(Page page, HttpServletRequest request){
        PageInfo<Material> list = materialImpl.searchMaterial(page,request);
        return list;
    }

    @RequestMapping("/new")
    @ResponseBody
    public void InsertMaterial (Material material,HttpServletRequest request) {
        materialImpl.insertMaterial(material,request);
    }

    @RequestMapping("/change")
    @ResponseBody
    public Material materialChange(HttpServletRequest request){
        return materialImpl.changeMaterial(request);
    }

    @RequestMapping("/save")
    @ResponseBody
    public void saveMaterial(HttpServletRequest request) {
        materialImpl.saveMaterial(request);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void deleteMaterial(HttpServletRequest request){
        materialImpl.deleteMaterial(request);
    }

    @RequestMapping("/check")
    @ResponseBody
    public String checkId (HttpServletRequest request){
        return materialImpl.checkId(request);
    }

    @RequestMapping("/record")
    @ResponseBody
    public String record (HttpServletRequest request) {
        return materialImpl.record(request);
    }

    @RequestMapping("/getCategory")
    @ResponseBody
    public List<HashMap<String, Object>> getCategory (HttpServletRequest request) {
        return materialImpl.getCategory(request);
    }
}
