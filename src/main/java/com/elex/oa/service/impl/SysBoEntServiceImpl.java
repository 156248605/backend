package com.elex.oa.service.impl;

import com.elex.oa.dao.ISysBoAttrDao;
import com.elex.oa.dao.ISysBoEntDao;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.service.ISysBoEntService;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/13 11:51
*/
@Service
public class SysBoEntServiceImpl extends BaseServiceImpl<SysBoEnt> implements ISysBoEntService {
    @Autowired
    private ISysBoEntDao sysBoEntDao;

    @Autowired
    private ISysBoAttrDao sysBoAttrDao;

    public SysBoEnt getByBoDefId(String boDefId) {
        return this.getByBoDefId(boDefId, true);
    }

    public SysBoEnt getByBoDefId(String boDefId, boolean needAttr) {
        if(StringUtil.isEmpty(boDefId)) {
            return null;
        } else {
            List list = this.sysBoEntDao.getByBoDefId(boDefId);
            SysBoEnt mainEnt = this.getMain(list);
            if(mainEnt == null) {
                return new SysBoEnt();
            } else {
                List subList;
                if(needAttr) {
                   // subList = this.sysBoAttrQueryDao.getAttrsByEntId(mainEnt.getId());
                    subList = this.sysBoAttrDao.getAttrsByEntId(mainEnt.getId());
                    mainEnt.setSysBoAttrs(subList);
                }

                subList = this.getSub(list);
                if(needAttr && BeanUtil.isNotEmpty(subList)) {
                    Iterator var6 = subList.iterator();

                    while(var6.hasNext()) {
                        SysBoEnt subEnt = (SysBoEnt)var6.next();
                        List subAttrs = this.sysBoAttrDao.getAttrsByEntId(subEnt.getId());
                        subEnt.setSysBoAttrs(subAttrs);
                    }
                }
                mainEnt.setBoEntList(subList);
                return mainEnt;
            }
        }
    }

    private SysBoEnt getMain(List<SysBoEnt> list) {
        Iterator var2 = list.iterator();

        SysBoEnt ent;
        do {
            if(!var2.hasNext()) {
                return null;
            }

            ent = (SysBoEnt)var2.next();
        } while(!"main".equals(ent.getRelationType()));

        return ent;
    }

    private List<SysBoEnt> getSub(List<SysBoEnt> list) {
        ArrayList rtnList = new ArrayList();
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            SysBoEnt ent = (SysBoEnt)var3.next();
            if("sub".equals(ent.getRelationType())) {
                rtnList.add(ent);
            }
        }

        return rtnList;
    }



}
