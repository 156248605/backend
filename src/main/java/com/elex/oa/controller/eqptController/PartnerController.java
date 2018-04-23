package com.elex.oa.controller.eqptController;

import com.elex.oa.entity.eqpt.Page;
import com.elex.oa.entity.eqpt.Partner;
import com.elex.oa.service.eqptImpl.PartnerImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin
@RequestMapping("/partner")
public class PartnerController {

    @Resource
    private PartnerImpl partnerImpl;

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Partner> partnerList(Page page){
        PageInfo<Partner> list = partnerImpl.showPartner(page);
        return list;
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Partner> searchPartner(Page page, HttpServletRequest request){
        PageInfo<Partner> list = partnerImpl.searchPartner(page,request);
        return list;
    }

    @RequestMapping("/new")
    @ResponseBody
    public void InsertPartner (Partner partner,HttpServletRequest request){
        partnerImpl.insertPartner(partner,request);
    }

    @RequestMapping("/name")
    @ResponseBody
    public Partner Name(HttpServletRequest request){
        return partnerImpl.name(request);
    }


    @RequestMapping("/change")
    @ResponseBody
    public void ChangePartner (Partner partner,HttpServletRequest request){
        partnerImpl.changePartner(partner,request);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void DeletePartner (Partner partner,HttpServletRequest request){
        partnerImpl.deletePartner(partner,request);
    }
}
