package com.elex.oa.service.objectives;

import com.elex.oa.entity.objectives.Goal2;

public interface RevenueService {
    //根据年度查询销售收入信息
    Goal2 queryData(Goal2 goal2);
    //保存销售收入信息
    String saveData(Goal2 goal2);
}
