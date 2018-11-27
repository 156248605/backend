package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.service.restructure_hrService.IHrdatadictionaryService;
import org.apache.commons.lang.StringUtils;
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
    @Resource
    IHrdatadictionaryDao iHrdatadictionaryDao;

    @Override
    public Boolean changeTable() {
        Boolean valBoolean = true;
        List<HRset> hRsetList = ihRsetDao.selectAll();
        for (HRset hRset:hRsetList
             ) {
            Hrdatadictionary hrDataDictionary = new Hrdatadictionary(hRset.getDatacode(),hRset.getDatatype(),hRset.getDatavalue());
            String val = addHrdatadictionary(hrDataDictionary);
            if(null==val){
                valBoolean=false;
                System.out.println(hrDataDictionary.toString()+"==========================已存在==================");
            }
        }
        return valBoolean;
    }

    private String addHrdatadictionary(Hrdatadictionary hrDataDictionary){
        Integer val = validateHrdatadictionary(hrDataDictionary);
        if (val==0) {
            iHrdatadictionaryDao.insert(getDatacodeByEntity(hrDataDictionary));
            System.out.println(hrDataDictionary.getDatacode()+"==========");
            return hrDataDictionary.getDatacode();
        }else {
            return null;
        }
    }

    private Integer validateHrdatadictionary(Hrdatadictionary hrDataDictionary){
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(hrDataDictionary);
        if(null==hrdatadictionaryList || hrdatadictionaryList.size()==0){
            return 0;
        }else if(hrdatadictionaryList.size()==1){
            return 1;
        }else if(hrdatadictionaryList.size()>1){
            return 2;
        }
        return -1;
    }

    private Hrdatadictionary getDatacodeByEntity(Hrdatadictionary hrDataDictionary){
        String datacode = hrDataDictionary.getDatacode();
        if(StringUtils.isNotEmpty(datacode) && validateDatacode(datacode)==0){
            Integer validateDatacode = validateDatacode(datacode);
                return hrDataDictionary;
        }else {
            datacode = hrDataDictionary.getDatatype()+"_"+System.currentTimeMillis();
        }
        hrDataDictionary.setDatacode(datacode);
        return hrDataDictionary;
    }

    private Integer validateDatacode(String datacode){
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(datacode));
        return hrdatadictionaryList.size()==0?0:-1;
    }
}