package com.elex.oa.controller.eqptController;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.RepositoryMtImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/repositorymt")
public class RepositoryMtController {

    @Resource
    private RepositoryMtImpl repositoryMtImpl;

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Repository> repositoryList(Page page) {
        PageInfo<Repository> ListR = repositoryMtImpl.showRepositoryMt(page);
        return ListR;
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> repositorySearch(Page page, HttpServletRequest request) {
        PageInfo<Repository> ListR = repositoryMtImpl.searchRepositoryMt(page,request);
        return ListR;
    }

    @RequestMapping("/new")
    @ResponseBody
    public void insertRepositoryMt (HttpServletRequest request){
        repositoryMtImpl.insertRepositoryMt(request);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void deleteRepositoryMt(HttpServletRequest request){
        repositoryMtImpl.deleteRepositoryMt(request);
    }

    @RequestMapping("/save")
    @ResponseBody
    public void changeRepositoryMt(HttpServletRequest request){
        repositoryMtImpl.changeRepositoryMt(request);
    }

    @RequestMapping("/matlist")
    @ResponseBody
    public List<Material> ReptList(){
        return repositoryMtImpl.MatList();
    }

    @RequestMapping("/updFix")
    @ResponseBody
    public void updFix(HttpServletRequest request) {
        repositoryMtImpl.updFix(request);
    }

    @RequestMapping("/updOtherFix")
    @ResponseBody
    public void updOtherFix(HttpServletRequest request) {
        repositoryMtImpl.updOtherFix(request);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public List<User> getUser() {
        List<User> getUser = repositoryMtImpl.username();
        return getUser;
    }
}
