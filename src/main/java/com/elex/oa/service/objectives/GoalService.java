package com.elex.oa.service.objectives;

import com.elex.oa.entity.objectives.Goal;
import com.elex.oa.entity.objectives.Goal2;

import java.util.Map;

public interface GoalService {
    //获取总公司的数据
    Map<String,Object> obtainDataCentral();
    //获取子公司的数据
    Map<String,Object> obtainDataBranch(String unit);
    //获取某单位的销售收入详情
    Goal2 obtainRevenueUnit(String unit);
    //获取某单位的销售毛利详情
    Goal2 obtainGrossUnit(String unit);
    //获取某单位的销售毛利详情
    Goal obtainPatentUnit(String unit);
}
