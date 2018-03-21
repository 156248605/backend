package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmFormViewDao;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.service.IBpmFormViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:20
*/
@Service
public class BpmFormViewImpl implements IBpmFormViewService {
    private IBpmFormViewDao bpmFormViewDao;
    @Autowired
    public BpmFormViewImpl(IBpmFormViewDao bpmFormViewDao) {
        this.bpmFormViewDao = bpmFormViewDao;
    }

    public List<BpmFormView> getByTreeFilterNew(Map<String,String> map) {
        return this.bpmFormViewDao.getByTreeFilterNew(map);
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
