package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmDefDao;
import com.elex.oa.entity.BpmDef;
import com.elex.oa.service.IBpmDefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:20
*/
@Service
public class BpmDefServiceImpl implements IBpmDefService {
    private IBpmDefDao bpmDefDao;
    @Autowired
    public BpmDefServiceImpl(IBpmDefDao bpmDefDao) {
        this.bpmDefDao = bpmDefDao;
    }
    //查询流程定义数据
    public PageInfo<BpmDef> query(Map<String,Object> paramMap){
        //页码
        int page = Integer.parseInt(paramMap.get("page").toString());
        //每页显示记录数
        int rows = Integer.parseInt(paramMap.get("rows").toString());

        PageHelper.startPage(page,rows);

        List<BpmDef> list = new ArrayList<BpmDef>();
        list = bpmDefDao.query(paramMap);

        return new PageInfo<BpmDef>(list);
    }

}
