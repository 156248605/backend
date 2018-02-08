package com.elex.oa.controller;

import com.elex.oa.service.IProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@author hugo.zhao
 *@since 2018/2/2 16:40
*/
@Api(value = "Test", description = "test the swagger API")
@Controller
public class testController {
    @Autowired
    private IProjectService projectService;
    @RequestMapping("/view")
    public String test(Model model){
        return  "test";

    }
}
