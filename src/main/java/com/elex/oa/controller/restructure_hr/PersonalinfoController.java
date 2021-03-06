package com.elex.oa.controller.restructure_hr;

import com.elex.oa.service.restructure_hrservice.IPersonalinfoService;
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
 * @Date 2018\12\13 0013 18:42
 * @Version 1.0
 **/
@Controller
@CrossOrigin
@RequestMapping("/personalinfo")
public class PersonalinfoController {
    @Autowired
    IPersonalinfoService iPersonalinfoService;

    @RequestMapping("/queryAllUsers")
    @ResponseBody
    public List<Map<String,String>> queryAllUsers(){
        return iPersonalinfoService.queryAllUsersByEmployeeON();
    }
}
