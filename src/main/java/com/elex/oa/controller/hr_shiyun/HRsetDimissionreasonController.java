package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetDimissionreason;
import com.elex.oa.service.hr_service.IHRsetDimissionreasonService;
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
 * @Description:离职原因
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetDimissionreasonController {
    @Autowired
    IHRsetDimissionreasonService ihRsetDimissionreasonService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneDimissionreason")
    @ResponseBody
    public String addOneDimissionreason(
            @RequestParam("dimissionreason") String dimissionreason
    ){
        HRsetDimissionreason hRsetDimissionreason = new HRsetDimissionreason();
        hRsetDimissionreason.setDimissionreason(dimissionreason);
        Integer id = ihRsetDimissionreasonService.addOne(hRsetDimissionreason);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllDimissionreason")
    @ResponseBody
    public List<HRsetDimissionreason> queryAllDimissionreason(){
        List<HRsetDimissionreason> hRsetDimissionreasonList = ihRsetDimissionreasonService.queryAll();
        return hRsetDimissionreasonList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo53")
    @ResponseBody
    public PageInfo<HRsetDimissionreason> queryHRPageInfo53(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetDimissionreason> hRsetDimissionreasonPageInfo = ihRsetDimissionreasonService.queryByParam(paramMap);
        return hRsetDimissionreasonPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR53")
    @ResponseBody
    public Boolean queryValidateHR53(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetDimissionreason hRsetDimissionreason = null;
        try {
            hRsetDimissionreason = ihRsetDimissionreasonService.queryByDimissionreason(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetDimissionreason!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR53")
    @ResponseBody
    public String addHR53(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetDimissionreason hRsetDimissionreason = new HRsetDimissionreason();
        hRsetDimissionreason.setDimissionreason(valueHR);
        try {
            ihRsetDimissionreasonService.addOne(hRsetDimissionreason);
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
    @RequestMapping("/removeHR53")
    @ResponseBody
    public String removeHR53(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetDimissionreasonService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR53")
    @ResponseBody
    public String modifyHR53(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetDimissionreason hRsetDimissionreason = new HRsetDimissionreason();
        hRsetDimissionreason.setDimissionreason(valueHR);
        hRsetDimissionreason.setId(id);
        ihRsetDimissionreasonService.modifyOne(hRsetDimissionreason);
        return "修改成功！";
    }
}
