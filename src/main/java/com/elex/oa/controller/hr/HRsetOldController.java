package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.service.hr_service.IHRsetService;
import com.elex.oa.service.restructure_hrservice.IPostlevelrelationshipinfoService;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class HRsetOldController {
    @Autowired
    IHRsetService ihRsetService;
    @Autowired
    IPostlevelrelationshipinfoService iPostlevelrelationshipinfoService;

    @RequestMapping("/addOneHRset")
    @ResponseBody
    public Object addOneHRset(
          HRset hRset
    ){
        return ihRsetService.addOne(hRset);
    }

    @RequestMapping("/queryAllHRset")
    @ResponseBody
    public List<HRset> queryAllHRset(
            HRset hRset
    ){
        return ihRsetService.queryByConditions(hRset);
    }

    @RequestMapping("/queryAllHRsetByNull")
    @ResponseBody
    public List<HRset> queryAllHRsetByNull(){
        return ihRsetService.queryAll();
    }

    @RequestMapping("/queryHRsetPageInfo")
    @ResponseBody
    public PageInfo<HRset> queryHRPageInfo(
            @RequestParam("datatype") String datatype,
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",new HRset(datatype));
        return ihRsetService.queryByParam(paramMap);
    }

    @RequestMapping("/queryValidateHRset")
    @ResponseBody
    public Boolean queryValidateHRset(
            HRset hRset
    ){
        return ihRsetService.queryValidateHRset(hRset);
    }

    @RequestMapping("/removeHRset")
    @ResponseBody
    public Object removeHRset(
            @RequestParam("ids") List<Integer> ids
    ){
        Map<Integer, String> map = ihRsetService.removeMultiple(ids);
        return map.size()==0? RespUtil.response("200","删除成功！",null):RespUtil.response("500","删除失败！", JSONObject.toJSONString(map));
    }

    @RequestMapping("/modifyHRset")
    @ResponseBody
    public Object modifyHRset(
            HRset hRset
    ){
        return ihRsetService.modifyHRset(hRset);
    }

    @RequestMapping("/queryPostgradeByPostfamilyid")
    @ResponseBody
    public List<Hrdatadictionary> queryPostgradeByPostfamilyid(
            @RequestParam(value = "postfamilyid")String postfamilycode
    ){
        return  iPostlevelrelationshipinfoService.queryPostgradeByPostfamilycode(postfamilycode);
    }

    @RequestMapping("/updateDatacode")
    @ResponseBody
    public Object updateDatacode(){
        Boolean aBoolean = ihRsetService.updateDatacode();
        return aBoolean?RespUtil.response("200","更新成功！",null):RespUtil.response("500","更新失败！",null);
    }

    @RequestMapping("/oldHrset/supplyDatacode")
    @ResponseBody
    public Object supplyDatacode(){
        return ihRsetService.supplyDatacode();
    }

    @RequestMapping("/oldHrset/addPostfamilyAndPostgrade")
    @ResponseBody
    public Object addPostfamilyAndPostgrade(
            @RequestParam("postfamilyid")Integer postfamilyid,
            @RequestParam("postgradeid")Integer postgradeid
    ){
        return ihRsetService.addPostfamilyAndPostgrade(postfamilyid,postgradeid);
    }
}
