package com.elex.oa.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.elex.oa.core.entity.BpmNodeJump;
import com.elex.oa.dao.IBpmNodeJumpDao;
import com.elex.oa.entity.Page;
import com.elex.oa.service.IBpmNodeJumpService;
import com.elex.oa.util.BeanUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@Service
public class BpmNodeJumpServiceImpl implements IBpmNodeJumpService {

    @Resource
    private IBpmNodeJumpDao bpmNodeJumpDao;

    @Override
    public Set<String> getNodeHandleUserIds(String actInstId) {
        HashSet userIds = new HashSet();
        List list = this.getByActInstId(actInstId);
        Iterator var4 = list.iterator();
        while(var4.hasNext()) {
            BpmNodeJump node = (BpmNodeJump)var4.next();
            if(!BeanUtil.isEmpty(node.getCompleteTime()) && !StringUtils.isEmpty(node.getHandlerId())) {
                userIds.add(node.getHandlerId());
            }
        }
        return userIds;
    }

    @Override
    public List<BpmNodeJump> getByActInstId(String actInstId) {
        BpmNodeJump bpmNodeJump = new BpmNodeJump();
        bpmNodeJump.setActInstId(actInstId);
        return this.bpmNodeJumpDao.select(bpmNodeJump);
    }

    @Override
    public List<BpmNodeJump> getByActInstId(String actInstId, String status) {
        BpmNodeJump bpmNodeJump = new BpmNodeJump();
        bpmNodeJump.setActInstId(actInstId);
        bpmNodeJump.setStatus(status);
        return this.bpmNodeJumpDao.select(bpmNodeJump);
    }

    @Override
    public List<BpmNodeJump> getByActInstId(String actInstId, Page page) {
        return null;
    }

    @Override
    public List<BpmNodeJump> getFormOpinionByActInstId(String actInstId) {
        Example example = new Example(BpmNodeJump.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("actInstId",actInstId);
        criteria.andIsNotNull("opinionName");
        criteria.andNotEqualTo("opinionName","");
        return bpmNodeJumpDao.selectByExample(criteria);
    }

    @Override
    public List<BpmNodeJump> getByActInstIdNodeId(String actInstId, String nodeId) {
        BpmNodeJump bpmNodeJump = new BpmNodeJump();
        bpmNodeJump.setActInstId(actInstId);
        bpmNodeJump.setNodeId(nodeId);
        return this.bpmNodeJumpDao.select(bpmNodeJump);
    }
}
