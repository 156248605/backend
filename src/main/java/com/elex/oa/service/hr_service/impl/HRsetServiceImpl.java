package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPostRelationshipDao;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.service.hr_service.IHRsetService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    HrUtils hrUtils;


    @Override
    public Object addOne(HRset hRset) {
        if(StringUtils.isBlank(hRset.getDatacode())){
            hRset.setDatacode(hRset.getDatatype()+"_"+System.currentTimeMillis());
        }else {
            List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset( null,null, hRset.getDatacode(), null));
            if(hRsetList!=null || hRsetList.size()!=0)return RespUtil.successResp("500","编号已经存在！",null);
        }
        ihRsetDao.insertOne(hRset);
        Resp<Integer> integerResp = RespUtil.successResp("200", "添加成功！", hRset.getId());
        return RespUtil.successResp("200","添加成功！",hRset.getId());
    }

    @Override
    public List<HRset> queryAll() {
        List<HRset> hRsetList = ihRsetDao.selectAll();
        return hRsetList;
    }

    @Override
    public List<HRset> queryByConditions(HRset hRset) {
        return ihRsetDao.selectByConditions(hRset);
    }

    @Override
    public HRset queryById(Integer id) {
        if(id==null)return null;
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(id));
        return hRsetList.size()==0?null:hRsetList.get(0);
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
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public HRset modifyOne(HRset hRset) {
        try {
            ihRsetDao.updateOne(hRset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(hRset.getId()));
        return hRsetList.size()==0?null:hRsetList.get(0);
    }

    @Override
    public Object modifyHRset(HRset hRset) {
        if(StringUtils.isBlank(hRset.getDatacode())){
            hRset.setDatacode(hRset.getDatatype()+"_"+System.currentTimeMillis());
        }else {
            HRset oldHRset = ihRsetDao.selectById(hRset.getId());
            if(!hRset.getDatacode().equals(oldHRset.getDatacode())){
                List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset( null,null, hRset.getDatacode(), null));
                if(hRsetList!=null || hRsetList.size()!=0)return RespUtil.successResp("500","编号已经存在！",null);
            }
        }
        try {
            ihRsetDao.updateOne(hRset);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("500","修改失败！",e.getStackTrace());
        }
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(hRset.getId()));
        return RespUtil.successResp("200","修改成功！",null);
    }

    @Override
    public Map<Integer, String> removeMultiple(List<Integer> ids) {
        Map<Integer,String> map = new HashMap<>();
        for(Integer i=0;i<ids.size();i++){
            try {
                ihRsetDao.deleteOne(ids.get(i));
            } catch (Exception e) {
                e.printStackTrace();
                map.put(ids.get(i),"删除失败！");
            }
        }
        return map;
    }

    @Override
    public Boolean queryValidateHRset(HRset hRset) {
        List<HRset> hRsetList = ihRsetDao.selectByConditions(hRset);
        if(hRsetList==null || hRsetList.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public List<HRset> queryPostgradeByPostfamilyid(Integer postfamilyid) {
        List<Integer> postgradeidList = iPostRelationshipDao.getPostgradeidListByPostfamilyid(postfamilyid);
        List<HRset> postgradeList = new ArrayList<>();
        if (null!=postgradeidList || postgradeidList.size()>0) {
            for (Integer id:postgradeidList
                 ) {
                HRset hRset = ihRsetDao.selectById(id);
                if(null!=hRset)postgradeList.add(hRset);
            }
        }
        return postgradeList.size()>0?postgradeList:null;
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
                    e.printStackTrace();
                    return false;
                }
            /*if(StringUtils.isBlank(hrset.getDatacode())){
            }*/
        }
        return true;
    }

    @Override
    public Object supplyDatacode() {
        List<HRset> hRsetList = ihRsetDao.selectByDatacodeIsNull();
        if(null==hRsetList || hRsetList.size()==0)return RespUtil.successResp("500","没有需要补充的字段编码",null);
        for (HRset hRset:hRsetList
             ) {
            hRset.setDatacode(hRset.getDatatype()+"_"+System.currentTimeMillis());
            try {
                ihRsetDao.updateOne(hRset);
            } catch (Exception e) {
                e.printStackTrace();
                return RespUtil.successResp("500","补充失败",e.getStackTrace());
            }
        }
        return RespUtil.successResp("200","补充成功",null);
    }

}