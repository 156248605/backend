package com.elex.oa.controller.hr;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.service.hr_service.IHRsetService;
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

    /**
     *@Author:ShiYun;
     *@Description:HRset信息的添加
     *@Date: 18:43 2018\5\11 0011
     */
    @PostMapping("/addOneHRset")
    @ResponseBody
    public String addOneHRset(
          @RequestBody  HRset hRset
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
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",new HRset(datatype));
        return ihRsetService.queryByParam(paramMap);
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
            @RequestBody HRset hRset
    ){
        Boolean aBoolean = ihRsetService.modifyHRset(hRset);
        return aBoolean?"修改成功！":"修改失败！";
    }
}
