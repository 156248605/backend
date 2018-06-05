package com.elex.oa.service.impl.handler;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.service.IFormDataHandler;
import com.elex.oa.service.ISysBoEntService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
public abstract class AbstractFormDataHandler implements IFormDataHandler {
     @Resource
     private  ISysBoEntService sysBoEntService;
    public AbstractFormDataHandler() {
    }

    public JSONObject getInitData(String boDefId) {
        if(StringUtils.isEmpty(boDefId)) {
            return null;
        } else {
            SysBoEnt ent = this.sysBoEntService.getByBoDefId(boDefId);
            return ent == null?null:this.sysBoEntService.getInitData(ent);
        }
    }
}
