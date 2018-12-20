package com.elex.oa.controller.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Postinfo;
import com.elex.oa.service.restructure_hrService.IPostinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\20 0020 16:37
 * @Version 1.0
 **/
@Controller
@CrossOrigin
@RequestMapping("/postinfo")
public class PostInfoController {
    @Autowired
    IPostinfoService iPostinfoService;

    @RequestMapping("/listPosts")
    @ResponseBody
    public Map<String,Object> listPosts(){
        return iPostinfoService.getPostTree();
    }
}    