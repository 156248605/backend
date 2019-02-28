package com.elex.oa.controller.hr;

import com.elex.oa.entity.restructure_hrentity.Postlevelrelationshipinfo;
import com.elex.oa.service.hr_service.IPostRelationshipService;
import com.elex.oa.service.restructure_hrService.IPostlevelrelationshipinfoService;
import com.elex.oa.util.resp.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    IPostlevelrelationshipinfoService iPostlevelrelationshipinfoService;

    /**
     * @Author: shiyun
     * @Description:添加一条岗位关系=>新接口
     * @Date  2018\11\22 0022 16:32
     * @Param
     * @return
     **/

    @RequestMapping("/addPostrelationship")
    @ResponseBody
    public String addPostrelationship(
           Postlevelrelationshipinfo postlevelrelationshipinfo
    ){
        Boolean aBoolean = iPostlevelrelationshipinfoService.add(postlevelrelationshipinfo);
        return aBoolean?"添加成功！":"添加失败！";
    }

    /**
     * @Author: shiyun
     * @Description:查询所有的岗位关系=>新接口
     * @Date  2018\11\22 0022 16:32
     * @Param
     * @return
     **/
    @RequestMapping("/queryAllPostrelationship")
    @ResponseBody
    public List<Postlevelrelationshipinfo> queryAllPostrelationship(){
        return iPostlevelrelationshipinfoService.queryAllPostlevelrelationshipinfo();
    }

    /**
     * @Author: shiyun
     * @Description:根据ID删除岗位关系=>新接口
     * @Date  2018\11\23 0023 9:43
     * @Param
     * @return
     **/
    @RequestMapping("/removePostrelationship")
    @ResponseBody
    public Object removePostrelationship(
            @RequestParam(value = "postrpids") List<String> ids
    ){
        Boolean aBoolean = iPostlevelrelationshipinfoService.removeByIds(ids);
        return aBoolean? RespUtil.response("200","删除成功！",null):RespUtil.response("500","删除失败！",null);
    }
}
