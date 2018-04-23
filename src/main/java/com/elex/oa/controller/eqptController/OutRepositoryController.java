package com.elex.oa.controller.eqptController;


import com.elex.oa.entity.eqpt.Page;
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
}
