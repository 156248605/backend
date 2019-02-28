package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.service.restructure_hrService.IHrdatadictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public PageInfo<Hrdatadictionary> getPageInfo(Integer pageNum, Integer pageSize, Hrdatadictionary hrdatadictionary) {
        PageHelper.startPage(pageNum,pageSize);
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(hrdatadictionary);
        return new PageInfo<>(hrdatadictionaryList);
    }

    @Override
    public Boolean add(Hrdatadictionary hrdatadictionary) {
        String resp = addHrdatadictionary(hrdatadictionary);
        if(null==resp)return false;
        return true;
    }

    @Override
    public List<Hrdatadictionary> queryByConditions(Hrdatadictionary hrdatadictionary) {
        return iHrdatadictionaryDao.selectByEntity(hrdatadictionary);
    }

    @Override
    public List<Hrdatadictionary> queryAll() {
        return iHrdatadictionaryDao.selectAll();
    }

    @Override
    public Boolean queryValidateHrdatadictionary(Hrdatadictionary hrdatadictionary) {
        Integer resp = validateHrdatadictionary(hrdatadictionary);
        if(resp==0)return false;
        return true;
    }

    @Override
    public Map<String, String> removeMultipleByCodes(List<String> ids) {
        if(null==ids)return null;
        Map<String,String> respMap = new HashMap<>();
        for (String datacode:ids
             ) {
            //如果不存在则不删除
            if(validateDatacode(datacode)==0){
                respMap.put(datacode,"删除失败");
                continue;
            }
            try {
                iHrdatadictionaryDao.deleteByDatacode(datacode);
                respMap.put(datacode,"删除成功！");
            } catch (Exception e) {
                e.printStackTrace();
                respMap.put(datacode,"删除失败");
            }
        }
        return respMap;
    }

    @Override
    public Boolean modifyHrdatadictionary(Hrdatadictionary hrdatadictionary) {
        Integer validateDatacode = validateDatacode(hrdatadictionary.getDatacode());
        if(validateDatacode==0)return false;
        try {
            iHrdatadictionaryDao.update(hrdatadictionary);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String addHrdatadictionary(Hrdatadictionary hrDataDictionary){
        if(null==hrDataDictionary)return null;
        if(StringUtils.isEmpty(hrDataDictionary.getDatacode()))hrDataDictionary.setDatacode(hrDataDictionary.getDatatype()+"_"+System.currentTimeMillis());
        Integer val = validateHrdatadictionary(hrDataDictionary);
        if (val==0) {
            iHrdatadictionaryDao.insert(getDatacodeByEntity(hrDataDictionary));
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
        if(StringUtils.isEmpty(datacode))return 0;
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(datacode));
        return hrdatadictionaryList.size()==0?0:-1;
    }
}