package com.elex.oa.controller.restructure_hr;

import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.service.hr_service.IHRsetService;
import com.elex.oa.service.restructure_hrService.IHrdatadictionaryService;
import com.elex.oa.service.restructure_hrService.IPostlevelrelationshipinfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    IPostlevelrelationshipinfoService iPostlevelrelationshipinfoService;


    /**
     *@Author:ShiYun;
     *@Description:HRset信息的添加=>新接口
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneHRset")
    @ResponseBody
    public String addOneHRset(
          Hrdatadictionary hrdatadictionary
    ){
        Boolean aBoolean = iHrdatadictionaryService.add(hrdatadictionary);
        return aBoolean?"添加成功！":"添加失败！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HRset信息的查询（全部）=>新接口
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllHRset")
    @ResponseBody
    public List<Hrdatadictionary> queryAllHRset(
            Hrdatadictionary hrdatadictionary
    ){
        return iHrdatadictionaryService.queryByConditions(hrdatadictionary);
    }

    /**
     * @Author: shiyun
     * @Description:查询所有的HRset字段=>新接口
     * @Date  2018\11\14 0014 10:34
     * @Param
     * @return
     **/
    @RequestMapping("/queryAllHRsetByNull")
    @ResponseBody
    public List<Hrdatadictionary> queryAllHRsetByNull(){
        return iHrdatadictionaryService.queryAll();
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
     * @Description:HRset信息的查询（校验:存在返回true，不存在返回false）=>新接口
     * @Date  2018\11\7 0007 13:51
     * @Param [datatype, datavalue]
     * @return java.lang.Boolean
     **/
    @RequestMapping("/queryValidateHRset")
    @ResponseBody
    public Boolean queryValidateHRset(
            Hrdatadictionary hrdatadictionary
    ){
        return iHrdatadictionaryService.queryValidateHrdatadictionary(hrdatadictionary);
    }

    /**
     * @Author: shiyun
     * @Description:HRset信息的删除=>新接口
     * @Date  2018\11\7 0007 13:51
     * @Param [ids]
     * @return java.lang.String
     **/
    @RequestMapping("/removeHRset")
    @ResponseBody
    public Map<String,String> removeHRset(
            @RequestParam("ids") List<String> ids
    ){
        return iHrdatadictionaryService.removeMultipleByCodes(ids);
    }

    /**
     * @Author: shiyun
     * @Description:HRset信息修改=>新接口
     * @Date  2018\11\7 0007 13:50
     * @Param [datatype, datavalue, id]
     * @return java.lang.String
     **/
    @RequestMapping("/modifyHRset")
    @ResponseBody
    public String modifyHRset(
            Hrdatadictionary hrdatadictionary
    ){
        Boolean aBoolean = iHrdatadictionaryService.modifyHrdatadictionary(hrdatadictionary);
        return aBoolean?"修改成功！":"修改失败！";
    }

    /**
     * @Author: shiyun
     * @Description:根据职系查询职等=>新接口
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

}
