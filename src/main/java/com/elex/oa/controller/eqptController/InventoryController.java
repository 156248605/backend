package com.elex.oa.controller.eqptController;

import antlr.debug.ParserReporter;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.InventoryImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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

    @RequestMapping("/insert")
    @ResponseBody
    public void insertInfo(HttpServletRequest request) throws ParseException {
        inventoryImpl.insertInfo(request);
    }

    @RequestMapping("/showinvid")
    @ResponseBody
    public List showInvId (HttpServletRequest request) {
        List dateToId = inventoryImpl.showInvId(request);
        return dateToId;
    }

    @RequestMapping("/matinrept")
    @ResponseBody
    public List<Repository> matinrept(HttpServletRequest request){
        List<Repository> ListMir = inventoryImpl.matinrept(request);
        return ListMir;
    }

    @RequestMapping("/reptlist")
    @ResponseBody
    public List<Repository> ReptList(){
        return inventoryImpl.ReptList();
    }
}
