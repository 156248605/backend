package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetResourcetype;
import com.elex.oa.service.service_shiyun.IHRsetResourcetypeService;
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
 * @Description:资源类别
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetResourcetypeController {
    @Autowired
    IHRsetResourcetypeService ihRsetResourcetypeService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneResourcetype")
    @ResponseBody
    public String addOneResourcetype(
            @RequestParam("resourcetype") String resourcetype
    ){
        HRsetResourcetype hRsetResourcetype = new HRsetResourcetype();
        hRsetResourcetype.setResourcetype(resourcetype);
        Integer id = ihRsetResourcetypeService.addOne(hRsetResourcetype);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllResourcetype")
    @ResponseBody
    public List<HRsetResourcetype> queryAllResourcetype(){
        List<HRsetResourcetype> hRsetResourcetypeList = ihRsetResourcetypeService.queryAll();
        return hRsetResourcetypeList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo83")
    @ResponseBody
    public PageInfo<HRsetResourcetype> queryHRPageInfo83(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetResourcetype> hRsetResourcetypePageInfo = ihRsetResourcetypeService.queryByParam(paramMap);
        return hRsetResourcetypePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR83")
    @ResponseBody
    public Boolean queryValidateHR83(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetResourcetype hRsetResourcetype = null;
        try {
            hRsetResourcetype = ihRsetResourcetypeService.queryByResourcetype(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetResourcetype!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR83")
    @ResponseBody
    public String addHR83(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetResourcetype hRsetResourcetype = new HRsetResourcetype();
        hRsetResourcetype.setResourcetype(valueHR);
        try {
            ihRsetResourcetypeService.addOne(hRsetResourcetype);
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
    @RequestMapping("/removeHR83")
    @ResponseBody
    public String removeHR83(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetResourcetypeService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR83")
    @ResponseBody
    public String modifyHR83(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetResourcetype hRsetResourcetype = new HRsetResourcetype();
        hRsetResourcetype.setResourcetype(valueHR);
        hRsetResourcetype.setId(id);
        ihRsetResourcetypeService.modifyOne(hRsetResourcetype);
        return "修改成功！";
    }
}
