package com.elex.oa.controller.eqptController;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.ShiftRepositoryImpl;
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
@RequestMapping("/shift")
public class ShiftRepositoryController {

    @Resource
    private ShiftRepositoryImpl shiftRepositoryImpl;

    @RequestMapping("/find")
    @ResponseBody
    public PageInfo<Repository> showrepository(Page page, HttpServletRequest request){
        PageInfo<Repository> listR = shiftRepositoryImpl.ShowRepository(page,request);
        return listR;
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> searchShift(Page page, HttpServletRequest request){
        PageInfo<Repository> listR = shiftRepositoryImpl.searchRepository(page,request);
        return listR;
    }

    @RequestMapping("/new")
    @ResponseBody
    public void newRepository(HttpServletRequest request) throws ParseException{
        shiftRepositoryImpl.NewRepository(request);
    }

    @RequestMapping("/repository")
    @ResponseBody
    public void changeRepository(HttpServletRequest request){
        shiftRepositoryImpl.changeRepository(request);
    }

    @RequestMapping("/showshiftid")
    @ResponseBody
    public List showShiftId (HttpServletRequest request) {
        List dateToId = shiftRepositoryImpl.showShiftId(request);
        return dateToId;
    }

    @RequestMapping("/postCap")
    @ResponseBody
    public String postCap(HttpServletRequest request){
        String result = shiftRepositoryImpl.postCap(request);
        return result;
    }

    @RequestMapping("/jy")
    @ResponseBody
    public List<Repository> jy () {
        return shiftRepositoryImpl.wdbhJ();
    }

    @RequestMapping("/scll")
    @ResponseBody
    public List<Repository> scll () {
        return shiftRepositoryImpl.wdbhS();
    }

    @RequestMapping("/showmatJ")
    @ResponseBody
    public List<Repository> showmatJ (HttpServletRequest request) {
        return shiftRepositoryImpl.showmatJ(request);
    }

    @RequestMapping("/showmatS")
    @ResponseBody
    public List<Repository> showmatS (HttpServletRequest request) {
        return shiftRepositoryImpl.showmatS(request);
    }

}
