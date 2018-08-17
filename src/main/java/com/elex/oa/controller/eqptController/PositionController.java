package com.elex.oa.controller.eqptController;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.PositionServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/position")
@Controller
@CrossOrigin
public class PositionController {

    @Resource
    private PositionServiceImpl positionServiceimpl;

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Repository> positionList(Page page){
        PageInfo<Repository> listP = positionServiceimpl.showPosition(page);
        return listP;
    }

    @ResponseBody
    @RequestMapping("/search")
    public PageInfo<Repository> searchPosition(Page page,HttpServletRequest request){
        PageInfo<Repository> listP = positionServiceimpl.searchPosition(page,request);
        return listP;
    }

    @ResponseBody
    @RequestMapping("/change")
    public void changePosition (Repository repository,HttpServletRequest request){
        positionServiceimpl.changePosition(repository,request);
    }

    @ResponseBody
    @RequestMapping("/new")
    public String insertPosiiton(Repository repository,HttpServletRequest request){
        return positionServiceimpl.insertPosition(repository,request);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void deletePosition(Repository repository,HttpServletRequest request){
        positionServiceimpl.deleteRepository(repository,request);
    }

    @RequestMapping("/reptlist")
    @ResponseBody
    public List<Repository> ReptList(){
        return positionServiceimpl.ReptList();
    }

    @RequestMapping("/reptname")
    @ResponseBody
    public List<Repository> ReptName(HttpServletRequest request){
        return positionServiceimpl.ReptName(request);
    }

    @RequestMapping("/record")
    @ResponseBody
    public String record (HttpServletRequest request) {
        return positionServiceimpl.record(request);
    }
}
