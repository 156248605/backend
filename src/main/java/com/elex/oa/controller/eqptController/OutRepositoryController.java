package com.elex.oa.controller.eqptController;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.OutRepositoryImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/out")
public class OutRepositoryController {

    @Resource
    private OutRepositoryImpl outRepositoryImpl;

    @RequestMapping("/find")
    @ResponseBody
    public PageInfo<Repository> ShowRepository (Page page, HttpServletRequest request){
        PageInfo<Repository> list = outRepositoryImpl.ShowRepository(page,request);
        return list;
    }

    @RequestMapping("/new")
    @ResponseBody
    public void InsertNew (HttpServletRequest request) throws ParseException {
        outRepositoryImpl.InsertRepository(request);
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
        PageInfo<Repository> list = outRepositoryImpl.searchRepository(page,request);
        return list;
    }

    @RequestMapping("/repository")
    @ResponseBody
    public void OutRepository (HttpServletRequest request) {
        outRepositoryImpl.OutRepository(request);
    }

    @RequestMapping("/material")
    @ResponseBody
    public void OutMaterial (HttpServletRequest request) throws ParseException  {
        outRepositoryImpl.OutMaterial(request);
    }


    @RequestMapping("/showoutid")
    @ResponseBody
    public List showOutId (HttpServletRequest request) {
        List dateToId = outRepositoryImpl.showOutId(request);
        return dateToId;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void DeleteInRept (HttpServletRequest request) { outRepositoryImpl.DeleteOutRept(request); }

    @RequestMapping("/check")
    @ResponseBody
    public String checkId (HttpServletRequest request) {
        String result = outRepositoryImpl.canOut(request);
        return result;
    }

    @RequestMapping("/rcly")
    @ResponseBody
    public List<Repository> rcly () {
        return outRepositoryImpl.wdbhR();
    }

    @RequestMapping("/xsfh")
    @ResponseBody
    public List<Repository> xsfh () {
        return outRepositoryImpl.wdbhX();
    }

    @RequestMapping("/cgth")
    @ResponseBody
    public List<Repository> cgth () {
        return outRepositoryImpl.wdbhC();
    }

    @RequestMapping("/showmatR")
    @ResponseBody
    public List<Repository> showmatR (HttpServletRequest request) {
        return outRepositoryImpl.showmatR(request);
    }

    @RequestMapping("/showmatX")
    @ResponseBody
    public List<Repository> showmatX (HttpServletRequest request) {
        return outRepositoryImpl.showmatX(request);
    }

    @RequestMapping("/showmatC")
    @ResponseBody
    public List<Repository> showmatC (HttpServletRequest request) {
        return outRepositoryImpl.showmatC(request);
    }
}
