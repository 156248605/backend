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
    @ApiOperation("根据datatype和datavalue添加HRset的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "datatype",value = "HRset的数据类型"),
            @ApiImplicitParam(paramType = "query",dataType = "String",name = "datavalue",value = "HRset数据的值")
    })
    @ApiResponses({
            @ApiResponse(code = Commons.REST_SUCCESS, message = "成功"),
            @ApiResponse(code = Commons.REST_SERVER_ERROR, message = "请求错误"),
            @ApiResponse(code = Commons.REST_PARAM_ERROR, message = "请求参数无效")
    })
    @PostMapping("/addOneHRset")
    @ResponseBody
    public String addOneHRset(
            @RequestParam("datatype") String datatype,
            @RequestParam("datavalue") String datavalue
    ){
        Integer id = (Integer) ihRsetService.addOne(new HRset(datatype,datavalue));
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HRset信息的查询（全部）
     *@Date: 18:45 2018\5\11 0011
     */
    @GetMapping("/queryAllHRset")
    @ResponseBody
    public List<HRset> queryAllHRset(
            @RequestParam("datatype") String datatype
    ){
        return ihRsetService.queryByConditions(new HRset(datatype));
    }

    /**
     *@Author:ShiYun;
     *@Description:HRset信息的查询（分页）
     *@Date: 11:32 2018\5\19 0019
     */
    @GetMapping("/queryHRsetPageInfo")
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
    @GetMapping("/queryValidateHRset")
    @ResponseBody
    public Boolean queryValidateHR(
            @RequestParam("datatype") String datatype,
            @RequestParam("datavalue") String datavalue
    ){
        List<HRset> hRsetList = ihRsetService.queryByConditions(new HRset(datatype, datavalue));
        if(hRsetList==null || hRsetList.size()==0){
            return false;
        }
        return true;
    }

    /**
     * @Author: shiyun
     * @Description: HRset信息的删除
     * @Date  2018\11\7 0007 13:51
     * @Param [ids]
     * @return java.lang.String
     **/
    @DeleteMapping("/removeHRset")
    @ResponseBody
    public String removeHRset(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     * @Author: shiyun
     * @Description: HRset信息修改
     * @Date  2018\11\7 0007 13:50
     * @Param [datatype, datavalue, id]
     * @return java.lang.String
     **/
    @PutMapping("/modifyHRset")
    @ResponseBody
    public String modifyHRset(
            @RequestParam("datatype") String datatype,
            @RequestParam("datavalue")String datavalue,
            @RequestParam("id")Integer id
    ){
        HRset hRset = ihRsetService.modifyOne(new HRset(id, datatype, datavalue));
        if(hRset!=null){
            return "修改成功！";
        }
        return "修改失败！";
    }
}
