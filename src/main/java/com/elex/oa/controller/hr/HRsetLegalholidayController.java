package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetLegalholiday;
import com.elex.oa.service.hr_service.IHRsetLegalholidayService;
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
 * @Description:法定假
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetLegalholidayController {
    @Autowired
    IHRsetLegalholidayService ihRsetLegalholidayService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneLegalholiday")
    @ResponseBody
    public String addOneLegalholiday(
            @RequestParam("legalholiday") String legalholiday
    ){
        HRsetLegalholiday hRsetLegalholiday = new HRsetLegalholiday();
        hRsetLegalholiday.setLegalholiday(legalholiday);
        Integer id = ihRsetLegalholidayService.addOne(hRsetLegalholiday);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllLegalholiday")
    @ResponseBody
    public List<HRsetLegalholiday> queryAllLegalholiday(){
        List<HRsetLegalholiday> hRsetLegalholidayList = ihRsetLegalholidayService.queryAll();
        return hRsetLegalholidayList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo72")
    @ResponseBody
    public PageInfo<HRsetLegalholiday> queryHRPageInfo72(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetLegalholiday> hRsetLegalholidayPageInfo = ihRsetLegalholidayService.queryByParam(paramMap);
        return hRsetLegalholidayPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR72")
    @ResponseBody
    public Boolean queryValidateHR72(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetLegalholiday hRsetLegalholiday = null;
        try {
            hRsetLegalholiday = ihRsetLegalholidayService.queryByLegalholiday(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetLegalholiday!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR72")
    @ResponseBody
    public String addHR72(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetLegalholiday hRsetLegalholiday = new HRsetLegalholiday();
        hRsetLegalholiday.setLegalholiday(valueHR);
        try {
            ihRsetLegalholidayService.addOne(hRsetLegalholiday);
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
    @RequestMapping("/removeHR72")
    @ResponseBody
    public String removeHR72(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetLegalholidayService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR72")
    @ResponseBody
    public String modifyHR72(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetLegalholiday hRsetLegalholiday = new HRsetLegalholiday();
        hRsetLegalholiday.setLegalholiday(valueHR);
        hRsetLegalholiday.setId(id);
        ihRsetLegalholidayService.modifyOne(hRsetLegalholiday);
        return "修改成功！";
    }
}
