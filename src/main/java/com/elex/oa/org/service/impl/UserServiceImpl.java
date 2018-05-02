package com.elex.oa.org.service.impl;

import com.elex.oa.dao.IOsUserDao;
import com.elex.oa.entity.OsUser;
import com.elex.oa.entity.SysInst;
import com.elex.oa.org.model.ITenant;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/26 11:21
*/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IOsUserDao osUserDao;

    @Override
    public IUser getByUserId(String userId) {
        OsUser osUser = this.osUserDao.selectByPrimaryKey(userId);
      /*  if(osUser == null) {
            return osUser;
        } else {
            ITenant tenant
        }*/
        return osUser;
    }

 /*   private ITenant getByTenant(String tenantId) {
        List insts = SysInstManager.getTenants();
        Iterator var3 = insts.iterator();

        SysInst inst;
        do {
            if(!var3.hasNext()) {
                return null;
            }

            inst = (SysInst)var3.next();
        } while(!inst.getInstId().equals(tenantId));

        return inst;
    }*/


    @Override
    public IUser getByUsername(String var1) {
        return null;
    }

    @Override
    public List<IUser> getByGroupIdAndType(String var1, String var2) {
        return null;
    }

    @Override
    public List<IUser> getByGroupId(String var1) {
        return null;
    }
}
