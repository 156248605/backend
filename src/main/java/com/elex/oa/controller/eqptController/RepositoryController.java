package com.elex.oa.controller.eqptController;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.hr_entity.User;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.RepositoryImpl;
import com.elex.oa.service.hr_service.IDeptService;
import com.elex.oa.service.hr_service.impl.DeptServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/repository")
public class RepositoryController {

    @Resource
    private RepositoryImpl repositoryImpl;

    @Resource
    private IDeptService iDeptService;


    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Repository> repositoryList(Page page){
        PageInfo<Repository> list = repositoryImpl.showRepository(page);
        return list;
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
        PageInfo<Repository> list = repositoryImpl.searchRepository(page,request);
        return list;
    }

    @RequestMapping("/new")
    @ResponseBody
    public String InsertRepository (Repository repository,HttpServletRequest request){
        return repositoryImpl.insertRepository(repository,request);
    }

    /*@RequestMapping("/position")
    @ResponseBody
    public Repository Position(HttpServletRequest request){
        return repositoryImpl.position(request);
    }*/


    @RequestMapping("/change")
    @ResponseBody
    public void ChangeRepository (Repository repository,HttpServletRequest request){
        repositoryImpl.changeRepository(repository,request);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void DeleteRepository (Repository repository,HttpServletRequest request){
        repositoryImpl.deleteRepository(repository,request);
    }

    @RequestMapping("/reptlist")
    @ResponseBody
    public List<Repository> ReptList(){
        return repositoryImpl.ReptList();
    }

    @RequestMapping("/getPost")
    @ResponseBody
    public List<Repository> getPost(HttpServletRequest request){
        return repositoryImpl.getPost(request);
    }

    @RequestMapping("/postlist")
    @ResponseBody
    public List<Repository> PostList(){
        return repositoryImpl.PostList();
    }

    @RequestMapping("/matlist")
    @ResponseBody
    public List<Material> MatList(){
        return repositoryImpl.MatList();
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public List<User> getUser() {
        List<User> getUser = repositoryImpl.username();
        return getUser;
    }

    @RequestMapping("/matOutRept")
    @ResponseBody
    public List<Repository> matOutRept(HttpServletRequest request){
        List<Repository> list = repositoryImpl.matOutRept(request);
        return list;
    }

    @RequestMapping("/matOutPost")
    @ResponseBody
    public List<Repository> matOutPost(HttpServletRequest request){
        List<Repository> list = repositoryImpl.matOutPost(request);
        return list;
    }

    @RequestMapping("/matInRept")
    @ResponseBody
    public List<Repository> matInRept(HttpServletRequest request){
        List<Repository> list = repositoryImpl.matInRept(request);
        return list;
    }

    @RequestMapping("/matInPost")
    @ResponseBody
    public List<Repository> matInPost(HttpServletRequest request){
        List<Repository> list = repositoryImpl.matInPost(request);
        return list;
    }

    @RequestMapping("/record")
    @ResponseBody
    public String record (HttpServletRequest request) {
        return repositoryImpl.record(request);
    }

    @RequestMapping("/checkBS")
    @ResponseBody
    public String checkBS(HttpServletRequest request){
        return repositoryImpl.manageResult(request);
    }

    @RequestMapping("/getCate")
    @ResponseBody
    public List<HashMap<String, Object>> getCate(){
        return repositoryImpl.reptCate();
    }

    @RequestMapping("/checkCate")
    @ResponseBody
    public List<HashMap<String, Object>> checkCate(HttpServletRequest request){
        return repositoryImpl.checkCate(request);
    }

    @RequestMapping("/insertCate")
    @ResponseBody
    public void insertCate(HttpServletRequest request){
        repositoryImpl.insertCate(request);
    }

    @RequestMapping("/deleteCate")
    @ResponseBody
    public void deleteCate(HttpServletRequest request){
        repositoryImpl.deleteCate(request);
    }

    @RequestMapping("/queryByTruename")
    @ResponseBody
    public String queryByTruename (String name) {
        return iDeptService.queryByTruename(name);
    }

    @RequestMapping("/reptCanChangeState")
    @ResponseBody
    public String reptCanChangeState (HttpServletRequest request) {
        return repositoryImpl.reptCanChangeState(request);
    }

    /*@RequestMapping("/matInRept")
    @ResponseBody
    public List<Repository> matInRept(HttpServletRequest request){
        return repositoryImpl.matInRept(request);
    }

    @RequestMapping("/matInPost")
    @ResponseBody
    public List<Repository> matInPost(HttpServletRequest request){
        return repositoryImpl.matInPost(request);
    }

    @RequestMapping("/matOutRept")
    @ResponseBody
    public Map matOutRept(HttpServletRequest request){
        return repositoryImpl.matOutRept(request);
    }

    @RequestMapping("/matOutPost")
    @ResponseBody
    public List<Repository> matOutPost(HttpServletRequest request){
        return repositoryImpl.matOutPost(request);
    }*/
}
