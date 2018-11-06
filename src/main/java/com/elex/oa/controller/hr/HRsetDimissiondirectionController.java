package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetDimissiondirection;
import com.elex.oa.service.hr_service.IHRsetDimissiondirectionService;
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
 * @Description:离职去向
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetDimissiondirectionController {
    @Autowired
    IHRsetDimissiondirectionService ihRsetDimissiondirectionService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneDimissiondirection")
    @ResponseBody
    public String addOneDimissiondirection(
            @RequestParam("dimissiondirection") String dimissiondirection
    ){
        HRsetDimissiondirection hRsetDimissiondirection = new HRsetDimissiondirection();
        hRsetDimissiondirection.setDimissiondirection(dimissiondirection);
        Integer id = ihRsetDimissiondirectionService.addOne(hRsetDimissiondirection);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllDimissiondirection")
    @ResponseBody
    public List<HRsetDimissiondirection> queryAllDimissiondirection(){
        List<HRsetDimissiondirection> hRsetDimissiondirectionList = ihRsetDimissiondirectionService.queryAll();
        return hRsetDimissiondirectionList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo52")
    @ResponseBody
    public PageInfo<HRsetDimissiondirection> queryHRPageInfo52(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetDimissiondirection> hRsetDimissiondirectionPageInfo = ihRsetDimissiondirectionService.queryByParam(paramMap);
        return hRsetDimissiondirectionPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR52")
    @ResponseBody
    public Boolean queryValidateHR52(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetDimissiondirection hRsetDimissiondirection = null;
        try {
            hRsetDimissiondirection = ihRsetDimissiondirectionService.queryByDimissiondirection(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetDimissiondirection!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR52")
    @ResponseBody
    public String addHR52(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetDimissiondirection hRsetDimissiondirection = new HRsetDimissiondirection();
        hRsetDimissiondirection.setDimissiondirection(valueHR);
        try {
            ihRsetDimissiondirectionService.addOne(hRsetDimissiondirection);
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
    @RequestMapping("/removeHR52")
    @ResponseBody
    public String removeHR52(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetDimissiondirectionService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR52")
    @ResponseBody
    public String modifyHR52(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetDimissiondirection hRsetDimissiondirection = new HRsetDimissiondirection();
        hRsetDimissiondirection.setDimissiondirection(valueHR);
        hRsetDimissiondirection.setId(id);
        ihRsetDimissiondirectionService.modifyOne(hRsetDimissiondirection);
        return "修改成功！";
    }
}
