package com.elex.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@author hugo.zhao
 *@since 2018/5/4 13:47
*/
@Controller
@RequestMapping({"/bpm/core/bpmInst/"})
public class BpmInstController {

    @RequestMapping({"start"})
    public String start(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return this.start(request,"");
    }

    public String  start(HttpServletRequest request, String solKey) throws Exception {



        
    }

}
