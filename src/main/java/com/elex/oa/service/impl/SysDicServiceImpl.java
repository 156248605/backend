package com.elex.oa.service.impl;

import com.elex.oa.dao.ISysDicDao;
import com.elex.oa.entity.SysDic;
import com.elex.oa.service.ISysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/3/13 17:35
*/
@Service
public class SysDicServiceImpl extends BaseServiceImpl<SysDic> implements ISysDicService {
    @Autowired
    private ISysDicDao sysDicDao;


    public List<SysDic> getByTreeId(String treeId){

      return  this.sysDicDao.getByTreeId(treeId);

    }

}
