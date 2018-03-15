package com.elex.oa.controller;

import com.elex.oa.entity.SysDic;
import com.elex.oa.service.IProjectService;
import com.elex.oa.service.ISysDicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/2/2 16:40
*/
@Api(value = "Test", description = "test the swagger API")
@RestController
public class testController {
    @Autowired
    private IProjectService projectService;

/*    @Autowired
    private ISysDicService sysDicService;
    @RequestMapping("/view")
    public List<SysDic> test(Model model){
        return  sysDicService.getByTreeId();
    }*/
}
