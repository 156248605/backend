package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IHRsetPostfamilyDao;
import com.elex.oa.dao.hr.IPostRelationshipDao;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.hr_entity.hr_set.PostfamilyAndPostgrade;
import com.elex.oa.service.hr_service.IHRsetService;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: HR设置
 * @Author shiyun
 * @Date 2018\11\6 0006 17:23
 * @Version 1.0
 **/
@Service
public class HRsetServiceImpl implements IHRsetService {
    @Resource
    private IHRsetDao ihRsetDao;
    @Resource
    private IPostRelationshipDao iPostRelationshipDao;
    @Resource
    private IHRsetPostfamilyDao ihRsetPostfamilyDao;

    private static Logger logger = LoggerFactory.getLogger(HRsetServiceImpl.class);

    @Override
    public Object addOne(HRset hRset) {
        if(StringUtils.isBlank(hRset.getDatacode())){
            hRset.setDatacode(hRset.getDatatype()+"_"+System.currentTimeMillis());
        }else {
            List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset( null,null, hRset.getDatacode(), null));
            if(!hRsetList.isEmpty())return RespUtil.response("500","编号已经存在！",null);
        }
        ihRsetDao.insertOne(hRset);
        return RespUtil.response("200","添加成功！",hRset.getId());
    }

    @Override
    public List<HRset> queryAll() {
        return ihRsetDao.selectAll();
    }

    @Override
    public List<HRset> queryByConditions(HRset hRset) {
        return ihRsetDao.selectByConditions(hRset);
    }

    @Override
    public HRset queryById(Integer id) {
        if(id==null)return null;
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(id));
        return hRsetList.isEmpty()?null:hRsetList.get(0);
    }

    @Override
    public PageInfo queryByParam(Map<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        HRset hRset = (HRset) paramMap.get("entity");
        PageHelper.startPage(pageNum, pageSize);
        List<HRset> hRsetList = ihRsetDao.selectByConditions(hRset);
        return new PageInfo<HRset>(hRsetList);
    }

    @Override
    public Boolean removeOne(Integer id) {
        try {
            ihRsetDao.deleteOne(id);
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    @Override
    public HRset modifyOne(HRset hRset) {
        try {
            ihRsetDao.updateOne(hRset);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return null;
        }
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(hRset.getId()));
        return hRsetList.isEmpty()?null:hRsetList.get(0);
    }

    @Override
    public Object modifyHRset(HRset hRset) {
        if(StringUtils.isBlank(hRset.getDatacode())){
            hRset.setDatacode(hRset.getDatatype()+"_"+System.currentTimeMillis());
        }else {
            HRset oldHRset = ihRsetDao.selectById(hRset.getId());
            if(!hRset.getDatacode().equals(oldHRset.getDatacode())){
                List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset( null,null, hRset.getDatacode(), null));
                if(!hRsetList.isEmpty())return RespUtil.response("500","编号已经存在！",null);
            }
        }
        try {
            ihRsetDao.updateOne(hRset);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","修改失败！",e.getStackTrace());
        }
        return RespUtil.response("200","修改成功！",null);
    }

    @Override
    public Map<Integer, String> removeMultiple(List<Integer> ids) {
        Map<Integer,String> map = new HashMap<>();
        for(Integer i=0;i<ids.size();i++){
            try {
                ihRsetDao.deleteOne(ids.get(i));
            } catch (Exception e) {
                logger.info(String.valueOf(e.getCause()));
                map.put(ids.get(i),"删除失败！");
            }
        }
        return map;
    }

    @Override
    public Boolean queryValidateHRset(HRset hRset) {
        List<HRset> hRsetList = ihRsetDao.selectByConditions(hRset);
        return !hRsetList.isEmpty();
    }

    @Override
    public List<HRset> queryPostgradeByPostfamilyid(Integer postfamilyid) {
        List<Integer> postgradeidList = iPostRelationshipDao.getPostgradeidListByPostfamilyid(postfamilyid);
        List<HRset> postgradeList = new ArrayList<>();
        if (!postgradeidList.isEmpty()) {
            for (Integer id:postgradeidList
                 ) {
                HRset hRset = ihRsetDao.selectById(id);
                if(null!=hRset)postgradeList.add(hRset);
            }
        }
        return postgradeList.isEmpty()? Collections.emptyList() :postgradeList;
    }

    @Override
    public Boolean updateDatacode() {
        List<HRset> hRsetList = ihRsetDao.selectAll();
        for (HRset hrset:hRsetList
             ) {
            String datacode = hrset.getDatatype()+"_"+System.currentTimeMillis();
            hrset.setDatacode(datacode);
            try {
                ihRsetDao.updateOne(hrset);
            } catch (Exception e) {
                logger.info(String.valueOf(e.getCause()));
                return false;
            }
        }
        return true;
    }

    @Override
    public Object supplyDatacode() {
        List<HRset> hRsetList = ihRsetDao.selectByDatacodeIsNull();
        if(hRsetList.isEmpty())return RespUtil.response("500","没有需要补充的字段编码",null);
        for (HRset hRset:hRsetList
             ) {
            hRset.setDatacode(hRset.getDatatype()+"_"+System.currentTimeMillis());
            try {
                ihRsetDao.updateOne(hRset);
            } catch (Exception e) {
                logger.info(String.valueOf(e.getCause()));
                return RespUtil.response("500","补充失败",e.getStackTrace());
            }
        }
        return RespUtil.response("200","补充成功",null);
    }

    @Override
    public Object addPostfamilyAndPostgrade(Integer postfamilyid, Integer postgradeid) {
        if(null==postfamilyid || null==postgradeid)return RespUtil.response("500","职系和职等都不能为空",null);
        try {
            HRset postfamily = ihRsetDao.selectById(postfamilyid);
            if(null==postfamily)return RespUtil.response("500","职系不存在",postfamily);
            HRset postgrade = ihRsetDao.selectById(postgradeid);
            if(null==postgrade)return RespUtil.response("500","职等不存在",postgradeid);
            PostfamilyAndPostgrade postfamilyAndPostgrade = new PostfamilyAndPostgrade("family_" + System.currentTimeMillis(), postfamilyid, postgradeid);
            ihRsetPostfamilyDao.insert(postfamilyAndPostgrade);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","请求失败",e.getCause());
        }
        return RespUtil.response("200","请求成功",null);
    }

}