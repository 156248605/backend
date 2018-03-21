package com.elex.oa.service.impl;

import com.elex.oa.dao.ISysTreeDao;
import com.elex.oa.entity.SysTree;
import com.elex.oa.service.ISysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/15 11:31
*/
@Service
public class SysTreeServiceImpl extends BaseServiceImpl<SysTree> implements ISysTreeService {

    private ISysTreeDao sysTreeDao;
    @Autowired
    public SysTreeServiceImpl(ISysTreeDao sysTreeDao) {
        this.sysTreeDao = sysTreeDao;
    }
    public List<SysTree> selectByCatKey(Map<String,String> map){
      return   this.sysTreeDao.selectByCatKey(map);
    }
}
