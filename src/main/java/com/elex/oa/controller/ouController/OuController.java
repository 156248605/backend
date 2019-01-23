package com.elex.oa.controller.ouController;

import com.elex.oa.entity.ou.OuDep;
import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.service.ouService.IOuDepService;
import com.elex.oa.service.ouService.IOuPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 14:54
 * @Version 1.0
 **/
@RequestMapping("/ou")
@CrossOrigin
@Controller
public class OuController {
    @Autowired
    private IOuDepService iOuDepService;
    @Autowired
    private IOuPostService iOuPostService;


    @RequestMapping("/dep/createDep")
    @ResponseBody
    public Object createDep(
            OuDep ouDep
    ){
        return iOuDepService.addOuDep(ouDep);
    }

    @RequestMapping("/post/createPost")
    @ResponseBody
    public Object createPost(
            OuPost ouPost
    ){
        return iOuPostService.addOuPost(ouPost);
    }
}    