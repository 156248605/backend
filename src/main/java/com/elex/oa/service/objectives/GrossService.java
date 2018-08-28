package com.elex.oa.service.objectives;

import com.elex.oa.entity.objectives.Goal2;

public interface GrossService {
    //根据年度查询销售毛利信息
    Goal2 queryData(Goal2 goal2);
    //保存销售毛利信息
    String saveData(Goal2 goal2);
}
