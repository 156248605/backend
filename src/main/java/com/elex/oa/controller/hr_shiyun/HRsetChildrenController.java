package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetChildren;
import com.elex.oa.service.hr_service.IHRsetChildrenService;
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
 * @Description:HR设置（生育）
 * @Date:Created in  17:22 2018\5\11 0011
 * @Modify By:
 */
@Controller
@CrossOrigin
public class HRsetChildrenController {
    @Autowired
    IHRsetChildrenService ihRsetChildrenService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 17:22 2018\5\11 0011
     */
    @RequestMapping("/addOneHRsetChildren")
    @ResponseBody
    public String addOneHRsetChildren(
            @RequestParam("children") String children
    ){
        HRsetChildren hRsetChildren = new HRsetChildren();
        hRsetChildren.setChildren(children);
        Integer hrsetchildrenid = ihRsetChildrenService.addOne(hRsetChildren);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 17:27 2018\5\11 0011
     */
    @RequestMapping("/queryAllChildren")
    @ResponseBody
    public List<HRsetChildren> queryAllChildren(){
        List<HRsetChildren> hRsetChildren = ihRsetChildrenService.queryAll();
        return hRsetChildren;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo14")
    @ResponseBody
    public PageInfo<HRsetChildren> queryHRPageInfo14(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetChildren> hRsetChildrenPageInfo = ihRsetChildrenService.queryByParam(paramMap);
        return hRsetChildrenPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR14")
    @ResponseBody
    public Boolean queryValidateHR14(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetChildren hRsetChildren = null;
        try {
            hRsetChildren = ihRsetChildrenService.queryByChildren(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetChildren!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR14")
    @ResponseBody
    public String addHR14(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetChildren hRsetChildren = new HRsetChildren();
        hRsetChildren.setChildren(valueHR);
        try {
            ihRsetChildrenService.addOne(hRsetChildren);
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
    @RequestMapping("/removeHR14")
    @ResponseBody
    public String removeHR14(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetChildrenService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR14")
    @ResponseBody
    public String modifyHR14(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetChildren hRsetChildren = new HRsetChildren();
        hRsetChildren.setChildren(valueHR);
        hRsetChildren.setId(id);
        ihRsetChildrenService.modifyOne(hRsetChildren);
        return "修改成功！";
    }
}
