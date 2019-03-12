package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPostRelationshipDao;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.hr_entity.hr_set.PostRelationship;
import com.elex.oa.service.hr_service.IPostRelationshipService;
import com.elex.oa.util.resp.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    private static Logger logger = LoggerFactory.getLogger(PostRelationshipServiceImpl.class);

    @Override
    public Integer addPostrp(PostRelationship postRelationship) {
        Boolean b = true;
        if(null == postRelationship.getPostfamilyid() || null==postRelationship.getPostgradeid() || null == postRelationship.getPostrankid() || null==postRelationship.getPostlevelid())b=false;
        List<PostRelationship> postRelationshipList = iPostRelationshipDao.selectByCondition(postRelationship);
        if (null!=postRelationshipList && !postRelationshipList.isEmpty())b=false;
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
            String hRsetValuePostfamily = getHRsetValue(p.getPostfamilyid(), p);
            String hRsetValuePostgrade = getHRsetValue(p.getPostgradeid(), p);
            String hRsetValuePostrank = getHRsetValue(p.getPostrankid(), p);
            String hRsetValuePostlevel = getHRsetValue(p.getPostlevelid(), p);
            if(null==hRsetValuePostfamily||null==hRsetValuePostgrade||null==hRsetValuePostrank||null==hRsetValuePostlevel)continue;
            p.setPostfamily(hRsetValuePostfamily);
            p.setPostgrade(hRsetValuePostgrade);
            p.setPostrank(hRsetValuePostrank);
            p.setPostlevel(hRsetValuePostlevel);
        }
        return postRelationships;
    }

    @Override
    public Object removeById(List<Integer> postrpIds) {
        if(null==postrpIds || postrpIds.isEmpty())return RespUtil.response("400","没有要删除的选项",null);
        for (Integer id:postrpIds
        ) {
            PostRelationship postRelationship = iPostRelationshipDao.selectOneById(id);
            if (null==postRelationship)return RespUtil.response("500","服务器忙，请稍后再试",id);
            try {
                iPostRelationshipDao.deleteById(id);
            } catch (Exception e) {
                logger.info(String.valueOf(e.getCause()));
                return RespUtil.response("500","服务器忙，请稍后再试",null);
            }
        }
        return RespUtil.response("200","删除成功！",null);
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
