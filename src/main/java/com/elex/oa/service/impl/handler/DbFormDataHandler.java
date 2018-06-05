package com.elex.oa.service.impl.handler;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.BoResult;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.service.ISysBoEntService;
import com.elex.oa.sys.bo.service.SysBoDataHandler;
import com.elex.oa.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DbFormDataHandler  extends AbstractFormDataHandler{

    @Resource
    ISysBoEntService sysBoEntManager;

    @Resource
    SysBoDataHandler sysBoDataHandler;

    public DbFormDataHandler() {
    }

    @Override
    public JSONObject getData(String boDefId, String id) {
        SysBoEnt ent = this.sysBoEntManager.getByBoDefId(boDefId);
        JSONObject json = null;
        if(StringUtil.isEmpty(id)) {
            json = this.getInitData(boDefId);
        } else {
            json = this.sysBoDataHandler.getDataByPk(id, ent);
        }

        return json;
    }

    @Override
    public BoResult saveData(String boDefId, String instId, JSONObject jsonObj) {
        SysBoEnt ent = this.sysBoEntManager.getByBoDefId(boDefId);
        BoResult result = this.sysBoDataHandler.handleData(ent, jsonObj);
        return result;
    }
}
