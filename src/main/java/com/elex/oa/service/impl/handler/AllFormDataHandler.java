package com.elex.oa.service.impl.handler;


import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.BoResult;
import com.elex.oa.json.JSONUtil;
import com.elex.oa.service.ISysBoEntService;
import com.elex.oa.sys.bo.service.SysBoDataHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AllFormDataHandler extends AbstractFormDataHandler {


     @Resource
     ISysBoEntService sysBoEntService;

     @Resource
     SysBoDataHandler sysBoDataHandler;


    @Override
    public JSONObject getData(String boDefId, String id) {
        return null;
    }

    @Override
    public BoResult saveData(String boDefId, String instId, JSONObject jsonObj) {
        return null;
    }

    protected String saveJson(String instId, boolean isAdd, String json) {
       return  null;
    }



}
