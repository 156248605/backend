package com.elex.oa.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/2/2 16:40
*/
@Api(value = "Test", description = "test the swagger API")
@Controller
public class testController {

    @RequestMapping("/view")
    public String test(Model model){
        return  "test";

    }

    @RequestMapping("/testApi")
    @ResponseBody
    @ApiOperation(value = "测试API接口", notes = "测试API接口", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> testApi() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("str1","str1");
        map.put("str2","str2");
        return map;
    }
}
