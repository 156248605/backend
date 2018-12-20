package com.elex.oa.controller.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Postinfo;
import com.elex.oa.service.restructure_hrService.IPostinfoService;
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

    @RequestMapping("/queryOnePostByPostcode")
    @ResponseBody
    public Postinfo queryOnePostByPostcode(
            @RequestParam("postcode")String postcode
    ){
        return iPostinfoService.queryOnePostByPostcode(postcode);
    }

    @RequestMapping("/queryPosts")
    @ResponseBody
    public List<Postinfo> queryPosts(){
        return iPostinfoService.queryPostinfoList();
    }

    @RequestMapping("/validateByPostcode")
    @ResponseBody
    public Object validateByPostcode(
            @RequestParam("postcode")String postcode
    ){
        Boolean aBoolean = iPostinfoService.validateByPostcode(postcode);
        return aBoolean? RespUtil.successResp("200","岗位编号已存在！",null):RespUtil.successResp("500","岗位编号不存在",null);
    }
}