package com.elex.oa.controller.eqptController;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Linkman;
import com.elex.oa.entity.eqpt.Partner;
import com.elex.oa.service.eqptImpl.LinkmanImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/linkman")
public class LinkmanController {

    @Resource
    private LinkmanImpl linkmanImpl;

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<Linkman> LinkmanList(Page page){
        PageInfo<Linkman> listL = linkmanImpl.showLinkman(page);
        return listL;
    }

    @ResponseBody
    @RequestMapping("/new")
    public String newLinkMan(HttpServletRequest request){
        return linkmanImpl.newLinkman(request);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public void  deleteLinkMan(HttpServletRequest request){
        linkmanImpl.deleteLinkman(request);
    }

    @ResponseBody
    @RequestMapping("/change")
    public void  changeLinkman(HttpServletRequest request){
        linkmanImpl.changeLinkman(request);
    }


    @ResponseBody
    @RequestMapping("search")
    public PageInfo<Linkman> searchLinkman(Page page,HttpServletRequest request){
        PageInfo<Linkman> listL = linkmanImpl.searchLinkman(page,request);
        return listL;
    }
}
