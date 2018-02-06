package com.elex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@author hugo.zhao
 *@since 2018/2/2 16:40
*/
@Controller
public class testController {

    @RequestMapping("/view")
    public String test(Model model){
        return  "test";

    }



}
