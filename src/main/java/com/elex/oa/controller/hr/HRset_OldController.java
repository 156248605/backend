package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.service.hr_service.IHRsetService;
import com.elex.oa.service.restructure_hrService.IHrdatadictionaryService;
import com.elex.oa.service.restructure_hrService.IPostlevelrelationshipinfoService;
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
public class HRset_OldController {
    @Autowired
    IHRsetService ihRsetService;
    @Autowired
    IPostlevelrelationshipinfoService iPostlevelrelationshipinfoService;


    /**
     *@Author:ShiYun;
     *@Description:HRset信息的添加
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneHRset")
    @ResponseBody
    public String addOneHRset(
          HRset hRset
    ){
        Integer integer = ihRsetService.addOne(hRset);
        return null!=integer?"添加成功！":"添加失败！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HRset信息的查询（全部）
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllHRset")
    @ResponseBody
    public List<HRset> queryAllHRset(
            HRset hRset
    ){
        return ihRsetService.queryByConditions(hRset);
    }

    /**
     * @Author: shiyun
     * @Description:查询所有的HRset字段
     * @Date  2018\11\14 0014 10:34
     * @Param
     * @return
     **/
    @RequestMapping("/queryAllHRsetByNull")
    @ResponseBody
    public List<HRset> queryAllHRsetByNull(){
        return ihRsetService.queryAll();
    }


    /**
     *@Author:ShiYun;
     *@Description:HRset信息的查询（分页）
     *@Date: 11:32 2018\5\19 0019
     */
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

    /**
     * @Author: shiyun
     * @Description:HRset信息的查询（校验:存在返回true，不存在返回false）
     * @Date  2018\11\7 0007 13:51
     * @Param [datatype, datavalue]
     * @return java.lang.Boolean
     **/
    @RequestMapping("/queryValidateHRset")
    @ResponseBody
    public Boolean queryValidateHRset(
            HRset hRset
    ){
        Boolean aBoolean = ihRsetService.queryValidateHRset(hRset);
        return aBoolean;
    }

    /**
     * @Author: shiyun
     * @Description:HRset信息的删除
     * @Date  2018\11\7 0007 13:51
     * @Param [ids]
     * @return java.lang.String
     **/
    @RequestMapping("/removeHRset")
    @ResponseBody
    public Object removeHRset(
            @RequestParam("ids") List<Integer> ids
    ){
        Map<Integer, String> map = ihRsetService.removeMultiple(ids);
        Boolean aBoolean = map.size()==0?true:false;
        return aBoolean? RespUtil.successResp("200","删除成功！",null):RespUtil.successResp("500","删除失败！", JSONObject.toJSONString(map));
    }

    /**
     * @Author: shiyun
     * @Description:HRset信息修改
     * @Date  2018\11\7 0007 13:50
     * @Param [datatype, datavalue, id]
     * @return java.lang.String
     **/
    @RequestMapping("/modifyHRset")
    @ResponseBody
    public String modifyHRset(
            HRset hRset
    ){
        Boolean aBoolean = ihRsetService.modifyHRset(hRset);
        return aBoolean?"修改成功！":"修改失败！";
    }

    /**
     * @Author: shiyun
     * @Description:根据职系查询职等
     * @Date  2018\11\23 0023 11:14
     * @Param
     * @return
     **/
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
        return aBoolean?RespUtil.successResp("200","更新成功！",null):RespUtil.successResp("500","更新失败！",null);
    }
}
