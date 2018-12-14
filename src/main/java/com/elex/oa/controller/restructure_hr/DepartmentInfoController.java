package com.elex.oa.controller.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Depinfo;
import com.elex.oa.service.restructure_hrService.IDepinfoService;
import com.elex.oa.util.resp.RespUtil;
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
        System.out.println(123);
        Boolean aBoolean = iDepinfoService.addOneDepartment(depinfo, transactorusername);
        return aBoolean? RespUtil.successResp("200","添加成功！",null):RespUtil.successResp("500","添加失败！",null);
    }

    @RequestMapping("/queryDepartments")
    @ResponseBody
    public List<Depinfo> queryDepartments(){
        return iDepinfoService.queryDepartmentinfoList();
    }
}    