package com.elex.oa.controller.eqptController;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.RepositoryImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/repository")
public class RepositoryController {

    @Resource
    private RepositoryImpl repositoryImpl;

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

    @RequestMapping("/postlist")
    @ResponseBody
    public List<Repository> PostList(){
        return repositoryImpl.PostList();
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
