package com.elex.oa.controller.eqptController;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.InventoryImpl;
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

@Controller
@CrossOrigin
@RequestMapping("/inv")
public class InventoryController {

    @Resource
    private InventoryImpl inventoryImpl;

    @RequestMapping("/find")
    @ResponseBody
    public PageInfo<Repository> showInfo(Page page) {
        PageInfo<Repository> list = inventoryImpl.showAllInfo(page);
        return list;
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> searchInfo(Page page,HttpServletRequest request) {
        PageInfo<Repository> list = inventoryImpl.searchInfo(page,request);
        return list;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void deleteInfo(HttpServletRequest request) {
        inventoryImpl.deleteInfo(request);
    }

    @RequestMapping("/deleteDraft")
    @ResponseBody
    public void deleteDraft(HttpServletRequest request) {
        inventoryImpl.deleteDraft(request);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public void insertInfo(HttpServletRequest request) throws ParseException {
        inventoryImpl.insertInfo(request);
    }

    @RequestMapping("/save")
    @ResponseBody
    public void saveInfo(HttpServletRequest request) throws ParseException {
        inventoryImpl.saveInfo(request);
    }

    @RequestMapping("/checkDraft")
    @ResponseBody
    public String checkDraft(HttpServletRequest request) {
        return inventoryImpl.checkDraft(request);
    }

    @RequestMapping("/showinvid")
    @ResponseBody
    public List showInvId (HttpServletRequest request) {
        List dateToId = inventoryImpl.showInvId(request);
        return dateToId;
    }

    @RequestMapping("/chooseMat")
    @ResponseBody
    public List<Material> chooseMat(HttpServletRequest request){
        List<Material> ListMir = inventoryImpl.chooseMat(request);
        return ListMir;
    }

    @RequestMapping("/matinrept")
    @ResponseBody
    public List<Material> matinrept(HttpServletRequest request){
        List<Material> ListMir = inventoryImpl.matinrept(request);
        return ListMir;
    }

    @RequestMapping("/reptlist")
    @ResponseBody
    public List<Repository> ReptList(){
        return inventoryImpl.ReptList();
    }

    @RequestMapping("/openDraft")
    @ResponseBody
    public List<Repository> openDraft(HttpServletRequest request){
        return inventoryImpl.openDraft(request);
    }

    @RequestMapping("/changeDraft")
    @ResponseBody
    public void changeDraft(HttpServletRequest request) throws ParseException{
        inventoryImpl.changeInfo(request);
    }

    @RequestMapping("/getInvId")
    @ResponseBody
    public List getInvId(){
        return inventoryImpl.getInvId();
    }
}
