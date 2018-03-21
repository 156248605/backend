package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmFormViewDao;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.service.IBpmFormViewService;
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
public class BpmFormViewServiceImpl implements IBpmFormViewService {
    private IBpmFormViewDao bpmFormViewDao;
    @Autowired
    public BpmFormViewServiceImpl(IBpmFormViewDao bpmFormViewDao) {
        this.bpmFormViewDao = bpmFormViewDao;
    }

    public PageInfo<BpmFormView> getByTreeFilterNew(Map<String,String> paramMap) {
        //页码
        int page = Integer.parseInt(paramMap.get("page"));
        //每页显示记录数
        int rows = Integer.parseInt(paramMap.get("rows"));

        PageHelper.startPage(page,rows);

        List<BpmFormView> list = new ArrayList<BpmFormView>();
        list = bpmFormViewDao.getByTreeFilterNew(paramMap);
        return new PageInfo<BpmFormView>(list);

//        return this.bpmFormViewDao.getByTreeFilterNew(map);
    }
    public  BpmFormView getById(String id){
        return  this.bpmFormViewDao.getById(id);
    }

    public boolean isKeyExist(String id){
         BpmFormView bpmFormView = this.bpmFormViewDao.getById(id);
          return  bpmFormView!=null;
    }
    public  void  create(BpmFormView formView){
         bpmFormViewDao.create(formView);
    }
    public int update(BpmFormView formView){
       return   bpmFormViewDao.update(formView);
    }

}
