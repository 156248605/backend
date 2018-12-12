package com.elex.oa.controller.hr;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.service.hr_service.IHRsetService;
import com.elex.oa.service.restructure_hrService.IHrdatadictionaryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
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
@RequestMapping("/hrset")
public class HRsetController {
    @Autowired
    IHRsetService ihRsetService;
    @Autowired
    IHrdatadictionaryService iHrdatadictionaryService;

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
        Integer id = (Integer) ihRsetService.addOne(hRset);
        return id!=null?"添加成功！":"添加失败！";
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
     * @Description: 查询所有的HRset字段
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
     *@Description:HRset信息的查询（分页）=>新接口
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRsetPageInfo")
    @ResponseBody
    public PageInfo<Hrdatadictionary> queryHRPageInfo(
            @RequestParam("datatype") String datatype,
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        return iHrdatadictionaryService.getPageInfo(page, rows, new Hrdatadictionary(datatype, null));
    }

    /**
     * @Author: shiyun
     * @Description: HRset信息的查询（校验）
     * @Date  2018\11\7 0007 13:51
     * @Param [datatype, datavalue]
     * @return java.lang.Boolean
     **/
    @RequestMapping("/queryValidateHRset")
    @ResponseBody
    public Boolean queryValidateHRset(
            HRset hRset
    ){
        return ihRsetService.queryValidateHRset(hRset);
    }

    /**
     * @Author: shiyun
     * @Description: HRset信息的删除
     * @Date  2018\11\7 0007 13:51
     * @Param [ids]
     * @return java.lang.String
     **/
    @RequestMapping("/removeHRset")
    @ResponseBody
    public Map<Integer,String> removeHRset(
            @RequestParam("ids") List<Integer> ids
    ){
        return ihRsetService.removeMultiple(ids);
    }

    /**
     * @Author: shiyun
     * @Description: HRset信息修改
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
     * @Description: TODO
     * @Date  2018\11\23 0023 11:14
     * @Param
     * @return
     **/
    @RequestMapping("/queryPostgradeByPostfamilyid")
    @ResponseBody
    public List<HRset> queryPostgradeByPostfamilyid(
            @RequestParam(value = "postfamilyid")Integer postfamilyid
    ){
        List<HRset> hRsetList = ihRsetService.queryPostgradeByPostfamilyid(postfamilyid);
        return hRsetList;
    }

}
