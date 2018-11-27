package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.service.restructure_hrService.IHrdatadictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\27 0027 13:39
 * @Version 1.0
 **/
@Service
public class HrdatadictionaryServiceImpl implements IHrdatadictionaryService {
    @Resource
    IHRsetDao ihRsetDao;

    @Override
    public Boolean changeTable() {
        List<HRset> hRsetList = ihRsetDao.selectAll();
        for (HRset hRset:hRsetList
             ) {
            Hrdatadictionary hrDataDictionary = new Hrdatadictionary(hRset.getDatacode(),hRset.getDatatype(),hRset.getDatavalue());

        }
        return null;
    }
}