package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetDimissiontype;
import com.elex.oa.service.service_shiyun.IHRsetDimissiontypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职类型
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetDimissiontypeController {
    @Autowired
    IHRsetDimissiontypeService ihRsetDimissiontypeService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneDimissiontype")
    @ResponseBody
    public String addOneDimissiontype(
            @RequestParam("dimissiontype") String dimissiontype
    ){
        HRsetDimissiontype hRsetDimissiontype = new HRsetDimissiontype();
        hRsetDimissiontype.setDimissiontype(dimissiontype);
        Integer id = ihRsetDimissiontypeService.addOne(hRsetDimissiontype);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllDimissiontype")
    @ResponseBody
    public List<HRsetDimissiontype> queryAllDimissiontype(){
        List<HRsetDimissiontype> hRsetDimissiontypeList = ihRsetDimissiontypeService.queryAll();
        return hRsetDimissiontypeList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo51")
    @ResponseBody
    public PageInfo<HRsetDimissiontype> queryHRPageInfo51(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetDimissiontype> hRsetDimissiontypePageInfo = ihRsetDimissiontypeService.queryByParam(paramMap);
        return hRsetDimissiontypePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR51")
    @ResponseBody
    public Boolean queryValidateHR51(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetDimissiontype hRsetDimissiontype = null;
        try {
            hRsetDimissiontype = ihRsetDimissiontypeService.queryByDimissiontype(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetDimissiontype!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR51")
    @ResponseBody
    public String addHR51(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetDimissiontype hRsetDimissiontype = new HRsetDimissiontype();
        hRsetDimissiontype.setDimissiontype(valueHR);
        try {
            ihRsetDimissiontypeService.addOne(hRsetDimissiontype);
        } catch (Exception e) {
            return "添加失败！";
        }
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的删除
     *@Date: 16:36 2018\5\19 0019
     */
    @RequestMapping("/removeHR51")
    @ResponseBody
    public String removeHR51(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetDimissiontypeService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR51")
    @ResponseBody
    public String modifyHR51(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetDimissiontype hRsetDimissiontype = new HRsetDimissiontype();
        hRsetDimissiontype.setDimissiontype(valueHR);
        hRsetDimissiontype.setId(id);
        ihRsetDimissiontypeService.modifyOne(hRsetDimissiontype);
        return "修改成功！";
    }
}
