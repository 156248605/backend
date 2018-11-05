package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetSalarystandard;
import com.elex.oa.service.hr_service.IHRsetSalarystandardService;
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
 * @Description:薪资标准
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetSalartstandardController {
    @Autowired
    IHRsetSalarystandardService ihRsetSalarystandardService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneSalarystandard")
    @ResponseBody
    public String addOneSalarystandard(
            @RequestParam("salarystandard") String salarystandard
    ){
        HRsetSalarystandard hRsetByyx = new HRsetSalarystandard();
        hRsetByyx.setSalarystandard(salarystandard);
        Integer id = ihRsetSalarystandardService.addOne(hRsetByyx);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllSalarystandard")
    @ResponseBody
    public List<HRsetSalarystandard> queryAllSalarystandard(){
        List<HRsetSalarystandard> hRsetSalarystandards = ihRsetSalarystandardService.queryAll();
        return hRsetSalarystandards;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo29")
    @ResponseBody
    public PageInfo<HRsetSalarystandard> queryHRPageInfo29(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetSalarystandard> hRsetSalarystandardPageInfo = ihRsetSalarystandardService.queryByParam(paramMap);
        return hRsetSalarystandardPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR29")
    @ResponseBody
    public Boolean queryValidateHR29(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetSalarystandard hRsetSalarystandard = null;
        try {
            hRsetSalarystandard = ihRsetSalarystandardService.queryBySalarystandard(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetSalarystandard!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR29")
    @ResponseBody
    public String addHR29(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetSalarystandard hRsetSalarystandard = new HRsetSalarystandard();
        hRsetSalarystandard.setSalarystandard(valueHR);
        try {
            ihRsetSalarystandardService.addOne(hRsetSalarystandard);
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
    @RequestMapping("/removeHR29")
    @ResponseBody
    public String removeHR29(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetSalarystandardService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR29")
    @ResponseBody
    public String modifyHR29(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetSalarystandard hRsetSalarystandard = new HRsetSalarystandard();
        hRsetSalarystandard.setSalarystandard(valueHR);
        hRsetSalarystandard.setId(id);
        ihRsetSalarystandardService.modifyOne(hRsetSalarystandard);
        return "修改成功！";
    }
}
