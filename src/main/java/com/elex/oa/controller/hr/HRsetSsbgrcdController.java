package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetSsbgrcd;
import com.elex.oa.service.hr_service.IHRsetSsbgrcdService;
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
 * @Description:社保个人缴费比例
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetSsbgrcdController {
    @Autowired
    IHRsetSsbgrcdService ihRsetSsbgrcdService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneSsbgrcd")
    @ResponseBody
    public String addOneSsbgrcd(
            @RequestParam("ssbgrcd") String ssbgrcd
    ){
        HRsetSsbgrcd hRsetSsbgrcd = new HRsetSsbgrcd();
        hRsetSsbgrcd.setSsbgrcd(ssbgrcd);
        Integer id = ihRsetSsbgrcdService.addOne(hRsetSsbgrcd);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllSsbgrcd")
    @ResponseBody
    public List<HRsetSsbgrcd> queryAllSsbgrcd(){
        List<HRsetSsbgrcd> hRsetSsbgrcds = ihRsetSsbgrcdService.queryAll();
        return hRsetSsbgrcds;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo33")
    @ResponseBody
    public PageInfo<HRsetSsbgrcd> queryHRPageInfo33(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetSsbgrcd> hRsetSsbgrcdPageInfo = ihRsetSsbgrcdService.queryByParam(paramMap);
        return hRsetSsbgrcdPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR33")
    @ResponseBody
    public Boolean queryValidateHR33(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetSsbgrcd hRsetSsbgrcd = null;
        try {
            hRsetSsbgrcd = ihRsetSsbgrcdService.queryBySsbgrcd(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetSsbgrcd!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR33")
    @ResponseBody
    public String addHR33(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetSsbgrcd hRsetSsbgrcd = new HRsetSsbgrcd();
        hRsetSsbgrcd.setSsbgrcd(valueHR);
        try {
            ihRsetSsbgrcdService.addOne(hRsetSsbgrcd);
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
    @RequestMapping("/removeHR33")
    @ResponseBody
    public String removeHR33(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetSsbgrcdService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR33")
    @ResponseBody
    public String modifyHR33(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetSsbgrcd hRsetSsbgrcd = new HRsetSsbgrcd();
        hRsetSsbgrcd.setSsbgrcd(valueHR);
        hRsetSsbgrcd.setId(id);
        ihRsetSsbgrcdService.modifyOne(hRsetSsbgrcd);
        return "修改成功！";
    }
}
