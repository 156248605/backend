package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.hr_entity.DeptTree;
import com.elex.oa.service.restructure_hrService.IDepinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/dep")
public class DepartmentInfoController {
    @Autowired
    IDepinfoService iDepinfoService;

    @RequestMapping("/listDepts")
    @ResponseBody
    public Map<String,Object> listDepts(){
        return iDepinfoService.gerDepTree();
    }
}    