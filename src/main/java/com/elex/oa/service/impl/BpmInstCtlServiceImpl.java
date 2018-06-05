package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmInstCtlDao;
import com.elex.oa.entity.BpmInstCtl;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.UserService;
import com.elex.oa.service.IBpmInstCtlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

@Service
public class BpmInstCtlServiceImpl implements IBpmInstCtlService {

    @Resource
    private IBpmInstCtlDao bpmInstCtlDao;

    @Resource
    private UserService userService;
    @Override
    public List<BpmInstCtl> getBySolAndTypeAndRight(String instId, String type, String right) {
        BpmInstCtl bpmInstCtl = new BpmInstCtl();
        bpmInstCtl.setInstId(instId);
        bpmInstCtl.setType(type);
        bpmInstCtl.setRight(right);
        return this.bpmInstCtlDao.select(bpmInstCtl);
    }

    @Override
    public boolean sysFileCtl(String userId, String instId, String right) {
        BpmInstCtl bpmInstCtl = new BpmInstCtl();
        bpmInstCtl.setUserIds(userId);
        bpmInstCtl.setRight(right);
        bpmInstCtl.setInstId("FILE");
        List<BpmInstCtl> list = this.bpmInstCtlDao.select(bpmInstCtl);
        if(list != null && list.size() > 0) {
            BpmInstCtl ctl = list.get(0);
            String[] groupIds;
            String[] var7;
            int var8;
            int var9;
            String groupId;
            if(StringUtils.isNotEmpty(ctl.getUserIds())) {
                groupIds = ctl.getUserIds().split(",");
                var7 = groupIds;
                var8 = groupIds.length;
                for(var9 = 0; var9 < var8; ++var9) {
                    groupId = var7[var9];
                    if(userId.equals(groupId)) {
                        return true;
                    }
                }
            }
            if(StringUtils.isNotEmpty(ctl.getGroupIds())) {
                groupIds = ctl.getGroupIds().split(",");
                var7 = groupIds;
                var8 = groupIds.length;

                for(var9 = 0; var9 < var8; ++var9) {
                    groupId = var7[var9];
                    List osusers = this.userService.getByGroupId(groupId);
                    Iterator var12 = osusers.iterator();

                    while(var12.hasNext()) {
                        IUser osuser = (IUser)var12.next();
                        if(userId.equals(osuser.getUserId())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            return true;
        }
    }
}
