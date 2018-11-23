package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPostRelationshipDao;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.hr_entity.PostRelationship;
import com.elex.oa.service.hr_service.IPostRelationshipService;
import com.elex.oa.util.resp.RespUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\22 0022 15:42
 * @Version 1.0
 **/
@Service
public class PostRelationshipServiceImpl implements IPostRelationshipService {
    @Resource
    IPostRelationshipDao iPostRelationshipDao;
    @Resource
    IHRsetDao ihRsetDao;

    @Override
    public Integer addPostrp(PostRelationship postRelationship) {
        Boolean b = true;
        if(null == postRelationship.getPostfamilyid() || null==postRelationship.getPostgradeid() || null == postRelationship.getPostrankid() || null==postRelationship.getPostlevelid())b=false;
        List<PostRelationship> postRelationshipList = iPostRelationshipDao.selectByCondition(postRelationship);
        if (null!=postRelationshipList && postRelationshipList.size()>0)b=false;
        Integer id = null;
        if (b) {
            iPostRelationshipDao.insert(postRelationship);
            id = postRelationship.getId();
        }
        return id;
    }

    @Override
    public List<PostRelationship> queryAllPostrelationship() {
        List<PostRelationship> postRelationships = iPostRelationshipDao.selectAll();
        for (PostRelationship p:postRelationships) {
            String hRsetValue_postfamily = getHRsetValue(p.getPostfamilyid(), p);
            if(null!=hRsetValue_postfamily){p.setPostfamily(hRsetValue_postfamily);}else {continue;}
            String hRsetValue_postgrade = getHRsetValue(p.getPostgradeid(), p);
            if(null!=hRsetValue_postgrade){p.setPostgrade(hRsetValue_postgrade);}else {continue;}
            String hRsetValue_postrank = getHRsetValue(p.getPostrankid(), p);
            if(null!=hRsetValue_postrank){p.setPostrank(hRsetValue_postrank);}else {continue;}
            String hRsetValue_postlevel = getHRsetValue(p.getPostlevelid(), p);
            if(null!=hRsetValue_postlevel){p.setPostlevel(hRsetValue_postlevel);}else {continue;}
        }
        return postRelationships;
    }

    @Override
    public Object removeById(List<Integer> postrp_ids) {
        if(null==postrp_ids || postrp_ids.size()==0)return RespUtil.successResp("400","没有要删除的选项",null);
        for (Integer id:postrp_ids
        ) {
            PostRelationship postRelationship = iPostRelationshipDao.selectOneById(id);
            if (null==postRelationship)return RespUtil.successResp("500","服务器忙，请稍后再试",id);
            try {
                iPostRelationshipDao.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
                return RespUtil.successResp("500","服务器忙，请稍后再试",null);
            }
        }
        return RespUtil.successResp("200","删除成功！",null);
    }

    private String getHRsetValue(Integer id,PostRelationship p){
        HRset hRset = ihRsetDao.selectById(id);
        if(null!=hRset){
            return hRset.getDatavalue();
        }else {
            //HRset表中没有此字段则说明此条关系不成立，即可删除此条信息！
            iPostRelationshipDao.deleteById(p.getId());
            return null;
        }
    }
}