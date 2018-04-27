package com.elex.oa.controller.eqptController;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.InRepositoryImpl;
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
@RequestMapping("/in")
public class InRepositoryController {

    @Resource
    public InRepositoryImpl inRepositoryImpl;

    @RequestMapping("/find")
    @ResponseBody
    public PageInfo<Repository> ShowRepository (Page page, HttpServletRequest request) {
        PageInfo<Repository> list = inRepositoryImpl.ShowRepository(page,request);
        return list;
    }

    @RequestMapping("/new")
    @ResponseBody
    public String InsertRepository (HttpServletRequest request) throws ParseException {
        return inRepositoryImpl.NewRepository(request);
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
        PageInfo<Repository> list = inRepositoryImpl.searchRepository(page,request);
        return list;
    }

    @RequestMapping("/material")
    @ResponseBody
    public void InsertMaterial (HttpServletRequest request) throws ParseException {
        inRepositoryImpl.InsertMaterial(request);
    }

    @RequestMapping("/repository")
    @ResponseBody
    public void newRepository (HttpServletRequest request) {
        inRepositoryImpl.InsertRepository(request);
    }

}
