package com.elex.oa.controller.restructure_hr;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.hr_entity.TitleAndCode;
import com.elex.oa.entity.restructure_hrentity.Depinfo;
import com.elex.oa.entity.restructure_hrentity.Deploginfo;
import com.elex.oa.service.restructure_hrService.IDepinfoService;
import com.elex.oa.service.restructure_hrService.IDeploginfoService;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\13 0013 13:53
 * @Version 1.0
 **/
@Controller
@CrossOrigin
@RequestMapping("/depinfo")
public class DepartmentInfoController {
    @Autowired
    IDepinfoService iDepinfoService;
    @Autowired
    IDeploginfoService iDeploginfoService;

    @RequestMapping("/listDepts")
    @ResponseBody
    public Map<String,Object> listDepts(){
        return iDepinfoService.getDepTree();
    }

    @RequestMapping("/queryOneDepByDepcode")
    @ResponseBody
    public Depinfo queryOneDepByDepcode(
            @RequestParam("depcode")String depcode
    ){
        return iDepinfoService.queryOneByDepcode(depcode);
    }

    @RequestMapping("/addOneDepartment")
    @ResponseBody
    public Object addOneDepartment(
            Depinfo depinfo,
            @RequestParam("transactorusername") String transactorusername
    ){
        Boolean aBoolean = iDepinfoService.addOneDepartment(depinfo, transactorusername);
        return aBoolean? RespUtil.successResp("200","添加成功！",null):RespUtil.successResp("500","添加失败！",null);
    }

    @RequestMapping("/queryDepartments")
    @ResponseBody
    public List<Depinfo> queryDepartments(){
        return iDepinfoService.queryDepartmentinfoList();
    }

    @RequestMapping("/queryDepartmentsRemoveChilren")
    @ResponseBody
    public List<Map<String,String>> queryDepartmentsRemoveChilren(
            @RequestParam("depcode") String depcode
    ){
        return iDepinfoService.queryDepartmentsRemoveChilren(depcode);
    }

    @RequestMapping("/updateOneDepartment")
    @ResponseBody
    public Object updateOneDepartment(
            Depinfo depinfo,
            @RequestParam("transactorusername") String transactorusername,
            @RequestParam("oldDepcode") String oldDepcode
    ){
        Boolean aBoolean = iDepinfoService.updateOneDepartment(depinfo, transactorusername,oldDepcode);
        return aBoolean? RespUtil.successResp("200","添加成功！",null):RespUtil.successResp("500","添加失败！",null);
    }

    @RequestMapping("/sortDepinformation")
    @ResponseBody
    public List<Map<String,Object>> sortDepinformation(
            @RequestParam("depcode") String depcode
    ){
        return iDepinfoService.getSortDepinformation(depcode);
    }

    @RequestMapping("/submitSortdata")
    @ResponseBody
    public Map<String,Object> submitSortdata(
            @RequestParam("sortdata") String sortdata
    ){
        //将JSON字符串转换成List集合
        List<Map> respMap = JSONObject.parseArray(sortdata, Map.class);
        return iDepinfoService.submitSortdata(respMap);
    }

    @RequestMapping("/deleteDeptsByDepcode")
    @ResponseBody
    public Object deleteDeptsByDepcode(
            @RequestParam("depcode") String depcode
    ){
        Boolean aBoolean = iDepinfoService.deleteDeptsByDepcode(depcode);
        return aBoolean? RespUtil.successResp("200","删除成功！",null):RespUtil.successResp("500","删除失败！",null);
    }

    @RequestMapping("/queryDeptLogInformations")
    @ResponseBody
    public PageInfo<Deploginfo> queryDeptLogInformations(
            @RequestParam("page")Integer page,
            @RequestParam("rows")Integer rows,
            Deploginfo deploginfo
    ){
        return iDeploginfoService.queryDeptLogInformations(page,rows,deploginfo);
    }

    @RequestMapping("/queryAllDeptLogInformations")
    @ResponseBody
    public List<Deploginfo> queryAllDeptLogInformations(){
        return iDeploginfoService.queryAllDeptLogInformations();
    }
}    