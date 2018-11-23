package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.hr_entity.PostRelationship;
import com.elex.oa.service.hr_service.IHRsetService;
import com.elex.oa.service.hr_service.IPostRelationshipService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: shiyun
 * @Description: @CrossOrigin允许跨域请求
 * @Date  2018\11\7 0007 13:57
 **/

@CrossOrigin
@Controller
@RequestMapping("/postrelationship")
public class PostRelationshipController {
    @Autowired
    IPostRelationshipService iPostRelationshipService;

    /**
     * @Author: shiyun
     * @Description: TODO
     * @Date  2018\11\22 0022 16:32
     * @Param
     * @return
     **/

    @RequestMapping("/addPostrelationship")
    @ResponseBody
    public String addPostrelationship(
           PostRelationship postRelationship
    ){
        Integer id = iPostRelationshipService.addPostrp(postRelationship);
        return id!=null?"添加成功！":"添加失败！";
    }

    /**
     * @Author: shiyun
     * @Description: TODO
     * @Date  2018\11\22 0022 16:32
     * @Param
     * @return
     **/
    @RequestMapping("/queryAllPostrelationship")
    @ResponseBody
    public List<PostRelationship> queryAllPostrelationship(){
        return iPostRelationshipService.queryAllPostrelationship();
    }

    /**
     * @Author: shiyun
     * @Description: TODO
     * @Date  2018\11\23 0023 9:43
     * @Param
     * @return
     **/
    @RequestMapping("/removePostrelationship")
    @ResponseBody
    public Object removePostrelationship(
            @RequestParam(value = "postrpids") List<Integer> postrpids
    ){
        return iPostRelationshipService.removeById(postrpids);
    }
}
