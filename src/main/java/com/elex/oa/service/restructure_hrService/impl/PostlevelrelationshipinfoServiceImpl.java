package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPostRelationshipDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.dao.restructure_hr.IPostlevelrelationshipinfoDao;
import com.elex.oa.entity.hr_entity.PostRelationship;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.entity.restructure_hrentity.Postlevelrelationshipinfo;
import com.elex.oa.service.restructure_hrService.IPostlevelrelationshipinfoService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\28 0028 15:59
 * @Version 1.0
 **/
@Service
public class PostlevelrelationshipinfoServiceImpl implements IPostlevelrelationshipinfoService {
    @Resource
    IPostRelationshipDao iPostRelationshipDao;
    @Resource
    IPostlevelrelationshipinfoDao iPostlevelrelationshipinfoDao;
    @Resource
    IHrdatadictionaryDao iHrdatadictionaryDao;

    @Override
    public Boolean changeTable() {
        Boolean valBean = true;
        List<PostRelationship> postRelationshipList = iPostRelationshipDao.selectAll();
        if(null==postRelationshipList || postRelationshipList.size()==0)return false;
        for (PostRelationship p:postRelationshipList
             ) {
            //将旧表的旧对象数据放入新对象中
            Postlevelrelationshipinfo newBean = getNewBeanNyOldBean(p);
            //添加数据前先校验一下里面是否有空值
            valBean = validateBeforeAddOrModify(newBean);
            if(!valBean)continue;
            //随机生成id
            newBean.setId("postlevel_relationship_"+System.currentTimeMillis());
            iPostlevelrelationshipinfoDao.insert(newBean);
        }
        return valBean;
    }

    @Override
    public List<Hrdatadictionary> queryPostgradeByPostfamilycode(String postfamilycode) {
        List<Postlevelrelationshipinfo> postlevelrelationshipinfoList = iPostlevelrelationshipinfoDao.selectByEntity(new Postlevelrelationshipinfo(postfamilycode));
        List<Hrdatadictionary> hrdatadictionaryList = new ArrayList<>();
        for (Postlevelrelationshipinfo p:postlevelrelationshipinfoList
             ) {
            Hrdatadictionary hrdatadictionary = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(p.getPostgradeid())).get(0);
            if(null==hrdatadictionary)continue;
            hrdatadictionaryList.add(hrdatadictionary);
        }
        return hrdatadictionaryList;
    }

    private Boolean getValBeforeAddOrModify(Postlevelrelationshipinfo newBean) {
        //根据不同的情况不同处理
        List<Postlevelrelationshipinfo> postlevelrelationshipinfoList = iPostlevelrelationshipinfoDao.selectByEntity(newBean);
        if(null==postlevelrelationshipinfoList || postlevelrelationshipinfoList.size()==0){
            //没有则添加/更新（正常情况）
            return true;
        }else if(postlevelrelationshipinfoList.size()==1){
            //有则停止
            return false;
        }else {
            //多个则说明有重复数据（数据问题需清理多余的数据）
            for(int i=0;i<postlevelrelationshipinfoList.size()-1;i++){
                iPostlevelrelationshipinfoDao.deleteById(postlevelrelationshipinfoList.get(i).getId());
            }
            return false;
        }
    }

    private Boolean validateBeforeAddOrModify(Postlevelrelationshipinfo newBean) {
        Boolean valBean = true;
        if(StringUtils.isEmpty(newBean.getPostfamilyid()))valBean=false;
        if(StringUtils.isEmpty(newBean.getPostgradeid()))valBean=false;
        if(StringUtils.isEmpty(newBean.getPostrankid()))valBean=false;
        if(StringUtils.isEmpty(newBean.getPostlevelid()))valBean=false;
        if (valBean) {
            valBean = getValBeforeAddOrModify(newBean);
        }
        return valBean;
    }

    private Postlevelrelationshipinfo getNewBeanNyOldBean(PostRelationship p) {
        Postlevelrelationshipinfo postlevelrelationshipinfo = new Postlevelrelationshipinfo();
        postlevelrelationshipinfo.setPostfamilyid(new HrUtilsTemp().getDatacodeByHrsetid(p.getPostfamilyid()));
        postlevelrelationshipinfo.setPostgradeid(new HrUtilsTemp().getDatacodeByHrsetid(p.getPostgradeid()));
        postlevelrelationshipinfo.setPostrankid(new HrUtilsTemp().getDatacodeByHrsetid(p.getPostrankid()));
        postlevelrelationshipinfo.setPostlevelid(new HrUtilsTemp().getDatacodeByHrsetid(p.getPostlevelid()));
        return postlevelrelationshipinfo;
    }
}