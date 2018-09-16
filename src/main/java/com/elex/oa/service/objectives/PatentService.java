package com.elex.oa.service.objectives;

import com.elex.oa.entity.objectives.Goal;

public interface PatentService {
    //根据年度查询发明专利信息
    Goal queryData(Goal goal);
    //保存发明专利信息
    String saveData(Goal goal);
}
