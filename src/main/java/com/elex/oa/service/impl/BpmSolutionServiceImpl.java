package com.elex.oa.service.impl;


import com.elex.oa.dao.IBpmSolutionDao;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.service.IBpmSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class BpmSolutionServiceImpl extends BaseServiceImpl<BpmSolution> implements IBpmSolutionService{
    @Autowired
    private IBpmSolutionDao solutionDao;

    @Override
    public List<BpmSolution> getSolutions(Map<String, Object> map) {
         return solutionDao.getSolutions(map);
    }

    public List<BpmSolution> getSolutionsByAdmin(Map<String,Object> map){
         return solutionDao.getSolutionsByAdmin(map);
    }

}
