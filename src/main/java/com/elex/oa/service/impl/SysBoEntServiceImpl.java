package com.elex.oa.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.ISysBoAttrDao;
import com.elex.oa.dao.ISysBoEntDao;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.service.ISysBoEntService;
import com.elex.oa.sys.bo.service.impl.AttrParseUtil;
import com.elex.oa.sys.bo.service.parse.BoAttrParseFactory;
import com.elex.oa.sys.bo.service.parse.IBoAttrParse;
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

    @Autowired
    private BoAttrParseFactory boAttrParseFactory;

    public SysBoEnt getByBoDefId(String boDefId) {
        return this.getByBoDefId(boDefId, true);
    }

    @Override
    public JSONObject getInitData(SysBoEnt boEnt) {
        JSONObject rtnJson = this.getJsonByEnt(boEnt);
        List subList = boEnt.getBoEntList();
        if(BeanUtil.isEmpty(subList)) {
            return rtnJson;
        } else {
            JSONObject initJson = new JSONObject();
            Iterator var5 = subList.iterator();

            while(var5.hasNext()) {
                SysBoEnt subEnt = (SysBoEnt)var5.next();
                JSONObject subJson = this.getJsonByEnt(subEnt);
                initJson.put(subEnt.getName(), subJson);
                JSONArray subAry = new JSONArray();
                rtnJson.put("SUB_" + subEnt.getName(), subAry);
            }

            rtnJson.put("initData", initJson);
            return rtnJson;
        }
    }

    @Override
    public List<SysBoEnt> getListByBoDefId(String boDefId, boolean needAttr) {
       // List list = this.sysBoEntQueryDao.getByBoDefId(boDefId);
        List list = this.sysBoEntDao.getByBoDefId(boDefId);
        if(needAttr) {
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                SysBoEnt ent = (SysBoEnt)var4.next();
                //List mainAttrs = this.sysBoAttrQueryDao.getAttrsByEntId(ent.getId());
                List mainAttrs = this.sysBoAttrDao.getAttrsByEntId(ent.getId());
                ent.setSysBoAttrs(mainAttrs);
            }
        }
        return list;
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

    private JSONObject getJsonByEnt(SysBoEnt ent) {
        JSONObject json = new JSONObject();
        List list = ent.getSysBoAttrs();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            SysBoAttr attr = (SysBoAttr)var4.next();
            String plugin = attr.getControl();
            IBoAttrParse parse = this.boAttrParseFactory.getByPluginName(plugin);
            JSONObject obj = parse.getInitData(attr);
            String key = AttrParseUtil.getKey(obj);
            String name = AttrParseUtil.getName(obj);
            json.put(attr.getName(), key);
            if(!attr.single()) {
                json.put(attr.getName() + "_NAME".toLowerCase(), name);
            }
        }

        return json;
    }

}
