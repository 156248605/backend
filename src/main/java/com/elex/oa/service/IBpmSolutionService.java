package com.elex.oa.service;


import com.elex.oa.entity.BpmSolution;

import java.util.List;
import java.util.Map;

public interface IBpmSolutionService extends BaseService<BpmSolution> {
    List<BpmSolution> getSolutions(Map<String,Object> map);

    List<BpmSolution> getSolutionsByAdmin(Map<String,Object> map);

    BpmSolution getByKey(String key, String tenantId);
}
