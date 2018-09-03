package com.elex.oa.controller.objectives;

import com.elex.oa.entity.objectives.Goal;
import com.elex.oa.entity.objectives.Goal2;
import com.elex.oa.service.objectives.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/goal")
public class GoalController {
    @Autowired
    private GoalService goalService;



    //获取总公司的数据
    @RequestMapping("/obtain_data_central")
    @ResponseBody
    public Map<String,Object> obtainDataCentral() {
        return goalService.obtainDataCentral();
    }

    //获取子公司的数据
    @RequestMapping("/obtain_data_branch")
    @ResponseBody
    public Map<String,Object> obtainDataBranch(String unit) {
        return goalService.obtainDataBranch(unit);
    }

    //获取某单位的销售收入详情
    @RequestMapping("/obtain_revenue_unit")
    @ResponseBody
    public Goal2 obtainRevenueUnit(String unit) {
        return goalService.obtainRevenueUnit(unit);
    }

    //获取某单位的销售毛利详情
    @RequestMapping("/obtain_gross_unit")
    @ResponseBody
    public Goal2 obtainGrossUnit(String unit) {
        return goalService.obtainGrossUnit(unit);
    }

    //获取某单位的销售毛利详情
    @RequestMapping("/obtain_patent_unit")
    @ResponseBody
    public Goal obtainPatentUnit(String unit) {
        return goalService.obtainPatentUnit(unit);
    }

    //获取公司相应数据(手机端)
    @RequestMapping("/obtain_company")
    @ResponseBody
    public List<Map<String,String>> obtainCompany(String unit) {
        return goalService.obtainCompany(unit);
    }

    //获取公司销售收入或税后净利相应数据（手机端）
    @RequestMapping("/obtain_various")
    @ResponseBody
    public List<Map<String,String>> obtainVarious(String unit, String various) {
        return goalService.obtainVarious(unit, various);
    }

    //获取单位对应的销售收入或税后净利相应数据详情（手机端）
    @RequestMapping("/obtain_details")
    @ResponseBody
    public Map<String,String> obtainDetails(String unit, String various) {
        return goalService.obtainDetails(unit, various);
    }
}
