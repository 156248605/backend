package com.elex.oa.sys.bo.service;

import com.alibaba.druid.proxy.jdbc.ClobProxyImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.annotation.Resource;

import com.elex.oa.core.entity.SqlModel;
import com.elex.oa.dao.CommonDao;
import com.elex.oa.entity.BoResult;
import com.elex.oa.entity.bo.SysBoAttr;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.org.model.IGroup;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
@Service
public class SysBoDataHandler {
    @Resource
    CommonDao commonDao;
    @Resource
    ISqlBuilder iSqlBuilder;
    @Resource
    GroupService groupService;

    public SysBoDataHandler() {
    }

    public BoResult handleData(SysBoEnt ent, JSONObject jsonData) {
        String userId = ContextUtil.getCurrentUserId();
        IGroup group = this.groupService.getMainByUserId(userId);
        String groupId = "";
        if(group != null) {
            groupId = group.getIdentityId();
        }

        Object list = null;
        BoResult result = this.isAdd(jsonData);
        result.setBoEnt(ent);
        if("add".equals(result.getAction())) {
            String parentId = this.getParentId(jsonData);
            this.handAdd(result.getPk(), parentId, ent, jsonData, userId, groupId);
        } else {
            this.handUpd(result.getPk(), ent, jsonData, userId, groupId);
        }

        return result;
    }

    private List<BoResult> handAdd(String pk, String parentId, SysBoEnt ent, JSONObject jsonData, String userId, String groupId) {
        ArrayList resultList = new ArrayList();
        BoResult mainResult = this.handInsert(pk, parentId, ent, jsonData, userId, groupId);
        mainResult.setMain(true);
        resultList.add(mainResult);
        if(BeanUtil.isEmpty(ent.getBoEntList())) {
            return resultList;
        } else {
            Iterator var9 = ent.getBoEntList().iterator();

            while(var9.hasNext()) {
                SysBoEnt subEnt = (SysBoEnt)var9.next();
                String name = subEnt.getName();
                String key = "SUB_" + name;
                if(jsonData.containsKey(key)) {
                    JSONArray jsonAry = jsonData.getJSONArray(key);
                    this.handInsertList(pk, subEnt, jsonAry, resultList, userId, groupId);
                }
            }

            return resultList;
        }
    }

    private void addCommonFields(List<String> fieldNameList, List<String> valNameList, Map<String, Object> params, String pk, String parentId, String userId, String groupId, JSONObject jsonObj) {
        fieldNameList.add("ID_");
        valNameList.add(this.getParamsName("ID_", "varchar"));
        params.put("ID_", pk);
        fieldNameList.add("REF_ID_");
        valNameList.add(this.getParamsName("REF_ID_", "varchar"));
        params.put("REF_ID_", parentId);
        fieldNameList.add("CREATE_USER_ID_");
        valNameList.add(this.getParamsName("CREATE_USER_ID_", "varchar"));
        params.put("CREATE_USER_ID_", userId);
        fieldNameList.add("CREATE_GROUP_ID_");
        valNameList.add(this.getParamsName("CREATE_GROUP_ID_", "varchar"));
        params.put("CREATE_GROUP_ID_", groupId);
        fieldNameList.add("CREATE_TIME_");
        valNameList.add(this.getParamsName("CREATE_TIME_", "date"));
        params.put("CREATE_TIME_", new Date());
        fieldNameList.add("UPDATE_TIME_");
        valNameList.add(this.getParamsName("UPDATE_TIME_", "date"));
        params.put("UPDATE_TIME_", new Date());
        String tenantId = ContextUtil.getCurrentTenantId();
        fieldNameList.add("TENANT_ID_");
        valNameList.add(this.getParamsName("TENANT_ID_", "varchar"));
        params.put("TENANT_ID_", tenantId);
        String status;
        if(jsonObj.containsKey("INST_ID_")) {
            status = jsonObj.getString("INST_ID_");
            fieldNameList.add("INST_ID_");
            valNameList.add(this.getParamsName("INST_ID_", "varchar"));
            params.put("INST_ID_", status);
        }

        if(jsonObj.containsKey("INST_STATUS_")) {
            status = jsonObj.getString("INST_STATUS_");
            fieldNameList.add("INST_STATUS_");
            valNameList.add(this.getParamsName("INST_STATUS_", "varchar"));
            params.put("INST_STATUS_", status);
        }

    }

    private BoResult handInsert(String pk, String parentId, SysBoEnt ent, JSONObject jsonData, String userId, String groupId) {
        ArrayList fieldNameList = new ArrayList();
        ArrayList valNameList = new ArrayList();
        HashMap params = new HashMap();
        this.addCommonFields(fieldNameList, valNameList, params, pk, parentId, userId, groupId, jsonData);
        List attrs = ent.getSysBoAttrs();
        Iterator sql = attrs.iterator();

        while(sql.hasNext()) {
            SysBoAttr result = (SysBoAttr)sql.next();
            if("base".equals(result.getStatus())) {
                String name = result.getName();
                if(jsonData.containsKey(name)) {
                    boolean isSingle = result.single();
                    this.handField(fieldNameList, valNameList, params, result, true, jsonData);
                    if(!isSingle) {
                        this.handField(fieldNameList, valNameList, params, result, false, jsonData);
                    }
                }
            }
        }

        String sql1 = "insert into " + ent.getTableName() + "(" + StringUtil.join(fieldNameList, ",") + ") values (" + StringUtil.join(valNameList, ",") + ")";
        this.commonDao.execute(sql1, params);
        BoResult result1 = new BoResult(pk, "add", ent);
        return result1;
    }

    private void handField(List<String> fieldNameList, List<String> valNameList, Map<String, Object> params, SysBoAttr attr, boolean isSingle, JSONObject jsonData) {
        String name = attr.getName();
        String fieldName = attr.getFieldName();
        String tmpName = isSingle?name:name + "_NAME";
        String tmpFieldName = isSingle?fieldName:fieldName + "_NAME";
        fieldNameList.add(tmpFieldName);
        valNameList.add(this.getParamsName(tmpName, attr.getDataType()));
        Object val = this.getVal(attr, jsonData, isSingle);
        params.put(tmpName, val);
    }

    private Object getVal(SysBoAttr attr, JSONObject jsonData, boolean isSingle) {
        String name = isSingle?attr.getName():attr.getName() + "_NAME".toLowerCase();
        String dataType = attr.getDataType();
        String val = jsonData.getString(name);
        return "mini-time".equals(attr.getControl())?this.handTime(attr, val):("mini-month".equals(attr.getControl())?this.handMonth(attr, val):(!"varchar".equals(dataType) && !"clob".equals(dataType)?("number".equals(dataType)?this.handNumber(attr, val):("date".equals(dataType)?this.handDate(attr, val):val)):val));
    }

    private String handTime(SysBoAttr attr, String val) {
        if(StringUtil.isEmpty(val)) {
            return val;
        } else {
            val = val.replace("T", " ");
            if(val.length() == 8) {
                return val;
            } else {
                Date date = DateUtil.parseDate(val, "yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat format = new SimpleDateFormat(attr.getPropByName("format"));
                String rtn = format.format(date);
                return rtn;
            }
        }
    }

    private String handMonth(SysBoAttr attr, String val) {
        if(StringUtil.isEmpty(val)) {
            return val;
        } else if(val.indexOf("T") == -1 && val.length() == 7) {
            return val;
        } else {
            val = val.replace("T", " ");
            Date date = DateUtil.parseDate(val);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            return format.format(date);
        }
    }

    private Object handNumber(SysBoAttr attr, String val) {
        if(StringUtil.isEmpty(val)) {
            return null;
        } else {
            val = val.trim();
            int length = attr.getLength().intValue();
            int decimalLen = attr.getDecimalLength().intValue();
            return decimalLen == 0?(length < 10?Integer.valueOf(Integer.parseInt(val)):Long.valueOf(Long.parseLong(val))):Float.valueOf(Float.parseFloat(val));
        }
    }

    private Object handDate(SysBoAttr attr, String val) {
        if(StringUtil.isEmpty(val)) {
            return null;
        } else {
            val = val.replace("T", " ");
            if(StringUtil.isEmpty(attr.getExtJson())) {
                return DateUtil.parseDate(val);
            } else {
                String format = "";
                String json = attr.getExtJson();
                if(StringUtil.isNotEmpty(json)) {
                    JSONObject rtn = JSONObject.parseObject(attr.getExtJson());
                    if(rtn != null && rtn.containsKey("format")) {
                        format = rtn.getString("format");
                    }
                }

                if(val.length() == 16) {
                    val = val + ":00";
                }

                Date rtn1 = DateUtil.parseDate(val, format);
                return rtn1;
            }
        }
    }

    private String getParamsName(String name, String type) {
        String dataType = "jdbcType=VARCHAR";
        if("varchar".equals(type)) {
            dataType = "jdbcType=VARCHAR";
        } else if("date".equals(type)) {
            dataType = "jdbcType=TIMESTAMP";
        } else if("number".equals(type)) {
            dataType = "jdbcType=NUMERIC";
        } else if("clob".equals(type)) {
            dataType = "jdbcType=CLOB";
        }

        return "#{" + name + "," + dataType + "}";
    }

    private List<BoResult> handUpd(String pk, SysBoEnt ent, JSONObject jsonData, String userId, String groupId) {
        this.handUpdRow(jsonData, ent);
        if(BeanUtil.isEmpty(ent.getBoEntList())) {
            return null;
        } else {
            JSONObject originJson = this.getDataByPk(pk, ent);
            List subList = ent.getBoEntList();
            ArrayList resultList = new ArrayList();
            Iterator var9 = subList.iterator();

            while(var9.hasNext()) {
                SysBoEnt subEnt = (SysBoEnt)var9.next();
                String name = subEnt.getName();
                String key = "SUB_" + name;
                if(jsonData.containsKey(key)) {
                    JSONArray subJsonList = jsonData.getJSONArray(key);
                    JSONArray originSubJsonList = originJson.getJSONArray(key);
                    this.handUpd(pk, subJsonList, originSubJsonList, resultList, subEnt, userId, groupId);
                }
            }

            return resultList;
        }
    }

    private void handUpd(String pk, JSONArray subJsonList, JSONArray originSubJsonList, List<BoResult> resultList, SysBoEnt ent, String userId, String groupId) {
        JSONArray newJsonAry = new JSONArray();
        Iterator it = subJsonList.iterator();

        while(true) {
            JSONObject i;
            Object jsonObj;
            do {
                if(!it.hasNext()) {
                    this.handInsertList(pk, ent, newJsonAry, resultList, userId, groupId);
                    this.handDel(subJsonList, originSubJsonList, resultList, ent);

                    for(int var13 = 0; var13 < subJsonList.size(); ++var13) {
                        JSONObject var14 = subJsonList.getJSONObject(var13);
                        BoResult result = this.handUpdRow(var14, ent);
                        if(result != null) {
                            resultList.add(result);
                        }
                    }

                    return;
                }

                i = (JSONObject)it.next();
                jsonObj = i.get("ID_");
            } while(jsonObj != null && !StringUtils.isEmpty(jsonObj.toString()));

            newJsonAry.add(i);
            it.remove();
        }
    }

    private void handDel(JSONArray subJsonList, JSONArray originSubJsonList, List<BoResult> resultList, SysBoEnt ent) {
        if(!BeanUtil.isEmpty(originSubJsonList)) {
            Set pkSet = this.getPkSet(subJsonList);

            for(int i = 0; i < originSubJsonList.size(); ++i) {
                JSONObject json = originSubJsonList.getJSONObject(i);
                String pk = this.getPk(json);
                if(!pkSet.contains(pk)) {
                    BoResult result = this.delByPK(pk, ent);
                    resultList.add(result);
                }
            }

        }
    }

    private String getPk(JSONObject json) {
        return json.getString("ID_");
    }

    private String getParentId(JSONObject json) {
        return json.containsKey("REF_ID_")?json.getString("REF_ID_"):"0";
    }

    private BoResult delByPK(String pk, SysBoEnt ent) {
        String sql = "delete from " + ent.getTableName() + " where " + "ID_" + "=#{" + "ID_" + "}";
        HashMap params = new HashMap();
        params.put("ID_", pk);
        this.commonDao.execute(sql, params);
        BoResult result = new BoResult(pk, "del", ent);
        return result;
    }

    private Set<String> getPkSet(JSONArray subJsonList) {
        HashSet set = new HashSet();

        for(int i = 0; i < subJsonList.size(); ++i) {
            JSONObject json = subJsonList.getJSONObject(i);
            String pk = this.getPk(json);
            set.add(pk);
        }

        return set;
    }

    private void handInsertList(String pk, SysBoEnt boEnt, JSONArray jsonAry, List<BoResult> list, String userId, String groupId) {
        if(!BeanUtil.isEmpty(jsonAry)) {
            Iterator var7 = jsonAry.iterator();

            while(var7.hasNext()) {
                Object obj = var7.next();
                JSONObject subRow = (JSONObject)obj;
                String id = IdUtil.getId();
                BoResult result = this.handInsert(id, pk, boEnt, subRow, userId, groupId);
                result.setMain(false);
                list.add(result);
            }

        }
    }

    private BoResult handUpdRow(JSONObject curJson, SysBoEnt ent) {
        String pk = this.getPk(curJson);
        ArrayList updList = new ArrayList();
        HashMap params = new HashMap();
        updList.add("UPDATE_TIME_=" + this.getParamsName("UPDATE_TIME_", "date"));
        params.put("UPDATE_TIME_", new Date());
        List attrs = ent.getSysBoAttrs();
        Iterator sql = attrs.iterator();

        while(sql.hasNext()) {
            SysBoAttr result = (SysBoAttr)sql.next();
            if("base".equals(result.getStatus())) {
                String name = result.getName();
                if(curJson.containsKey(name)) {
                    boolean isSingle = result.single();
                    updList.add(result.getFieldName() + "=" + this.getParamsName(name, result.getDataType()));
                    Object val = this.getVal(result, curJson, true);
                    params.put(name, val);
                    if(!isSingle) {
                        updList.add(result.getFieldName() + "_NAME" + "=" + this.getParamsName(name + "_NAME", result.getDataType()));
                        Object valName = this.getVal(result, curJson, false);
                        params.put(name + "_NAME", valName);
                    }
                }
            }
        }

        if(BeanUtil.isEmpty(updList)) {
            return null;
        } else {
            String sql1 = "update " + ent.getTableName() + " set " + StringUtil.join(updList, ",") + " where " + "ID_" + "=#{" + "ID_" + "}";
            params.put("ID_", pk);
            this.commonDao.execute(sql1, params);
            BoResult result1 = new BoResult(pk, "upd", ent);
            return result1;
        }
    }

    public BoResult isAdd(JSONObject jsonData) {
        BoResult result = new BoResult();
        String pkName = "ID_";
        String pk = "";
        result.setAction("add");
        if(jsonData.containsKey(pkName)) {
            pk = jsonData.getString(pkName);
            if(StringUtil.isNotEmpty(pk)) {
                result.setAction("upd");
                result.setPk(pk);
            } else {
                result.setPk(IdUtil.getId());
            }
        } else {
            result.setPk(IdUtil.getId());
        }

        return result;
    }

    public JSONObject getDataByPk(String pk, SysBoEnt boEnt) {
        JSONObject jsonObj = this.getByPk(pk, boEnt);
        List list = boEnt.getBoEntList();
        if(BeanUtil.isEmpty(list)) {
            return jsonObj;
        } else {
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                SysBoEnt subEnt = (SysBoEnt)var5.next();
                JSONArray jsonAry = this.getByFk(pk, subEnt);
                jsonObj.put("SUB_" + subEnt.getName(), jsonAry);
            }

            return jsonObj;
        }
    }

    private JSONArray getByFk(String fk, SysBoEnt boEnt) {
        String columnPre = DbUtil.getColumnPre().toLowerCase();
        SqlModel sqlModel = this.iSqlBuilder.getByFk(boEnt, fk);
        List list = this.commonDao.query(sqlModel);
        JSONArray jsonAry = new JSONArray();
        Map fieldMap = this.getAttrMap(boEnt);
        Map attrMap = this.getAttrObjMap(boEnt);

        for(int i = 0; i < list.size(); ++i) {
            Map map = (Map)list.get(i);
            JSONObject json = this.getJsonByRow(fieldMap, attrMap, map, columnPre);
            jsonAry.add(json);
        }

        return jsonAry;
    }

    public JSONObject getByPk(String pk, SysBoEnt boEnt) {
        String columnPre = DbUtil.getColumnPre().toLowerCase();
        String sql = "select * from " + boEnt.getTableName() + " where " + "ID_" + "=#{" + "ID_" + "}";
        SqlModel sqlModel = new SqlModel(sql);
        sqlModel.addParam("ID_", pk);
        Map row = this.commonDao.queryForMap(sqlModel);
        Map fieldMap = this.getAttrMap(boEnt);
        Map attrMap = this.getAttrObjMap(boEnt);
        JSONObject json = this.getJsonByRow(fieldMap, attrMap, row, columnPre);
        return json;
    }

    public List<JSONObject> getData(SysBoEnt boEnt, String parentId) {
        String columnPre = DbUtil.getColumnPre().toLowerCase();
        String sql = "";
        String tableName = boEnt.getTableName();
        if(StringUtil.isEmpty(parentId)) {
            sql = "select * from " + tableName;
        } else {
            sql = "SELECT a.*,(select count(*) from " + tableName + " b where b." + "REF_ID_" + "=a." + "ID_" + " ) CHILDS_AMOUNT_  from " + tableName + " a  where a." + "REF_ID_" + "=#{" + "REF_ID_" + "}";
        }

        SqlModel sqlModel = new SqlModel(sql);
        if(StringUtil.isNotEmpty(parentId)) {
            sqlModel.addParam("REF_ID_", parentId);
        }

        Map fieldMap = this.getAttrMap(boEnt);
        Map attrMap = this.getAttrObjMap(boEnt);
        List list = this.commonDao.query(sqlModel);
        ArrayList rtnList = new ArrayList();

        JSONObject json;
        for(Iterator var11 = list.iterator(); var11.hasNext(); rtnList.add(json)) {
            Map map = (Map)var11.next();
            json = this.getJsonByRow(fieldMap, attrMap, map, columnPre);
            if(map.containsKey("CHILDS_AMOUNT_")) {
                Integer amount = Integer.valueOf(Integer.parseInt(map.get("CHILDS_AMOUNT_").toString()));
                boolean isLeaf = amount.intValue() == 0;
                json.put("isLeaf", Boolean.valueOf(isLeaf));
                json.put("expanded", Boolean.valueOf(false));
            }
        }

        return rtnList;
    }

    private JSONObject getJsonByRow(Map<String, String> fieldMap, Map<String, SysBoAttr> attrMap, Map<String, Object> row, String columnPre) {
        JSONObject json = new JSONObject();
        Iterator it = row.entrySet().iterator();

        while(it.hasNext()) {
            Entry ent = (Entry)it.next();
            String name = StringUtil.trimPrefix(((String)ent.getKey()).toLowerCase(), columnPre);
            String attrName = (String)fieldMap.get(name);
            if(fieldMap.containsKey(name)) {
                Object val = this.getValue(name, attrMap, ent.getValue());
                json.put(attrName, val);
            } else {
                json.put((String)ent.getKey(), ent.getValue());
            }
        }

        return json;
    }

    private Object getValue(String attrName, Map<String, SysBoAttr> attrMap, Object val) {
        if(BeanUtil.isEmpty(val)) {
            return "";
        } else if(!attrMap.containsKey(attrName)) {
            return val;
        } else if(val instanceof ClobProxyImpl) {
            ClobProxyImpl attr1 = (ClobProxyImpl)val;
            return FileUtil.clobToString(attr1);
        } else {
            SysBoAttr attr = (SysBoAttr)attrMap.get(attrName);
            return "date".equals(attr.getDataType())?this.convertDate(attr, (Date)val):val;
        }
    }

    private String convertDate(SysBoAttr attr, Date val) {
        if(StringUtil.isEmpty(attr.getExtJson())) {
            return DateUtil.formatDate(val);
        } else {
            String format = "";
            String json = attr.getExtJson();
            if(StringUtil.isNotEmpty(json)) {
                JSONObject jsonObj = JSONObject.parseObject(attr.getExtJson());
                if(jsonObj != null && jsonObj.containsKey("format")) {
                    format = jsonObj.getString("format");
                }
            }

            return DateUtil.formatDate(val, format);
        }
    }

    private Map<String, String> getAttrMap(SysBoEnt boEnt) {
        HashMap mapField = new HashMap();
        List list = boEnt.getSysBoAttrs();
        mapField.put("ID_".toLowerCase(), "ID_");
        mapField.put("REF_ID_".toLowerCase(), "REF_ID_");
        mapField.put("CREATE_USER_ID_".toLowerCase(), "CREATE_USER_ID_");
        mapField.put("CREATE_GROUP_ID_".toLowerCase(), "CREATE_GROUP_ID_");
        mapField.put("CREATE_TIME_".toLowerCase(), "CREATE_TIME_");
        mapField.put("UPDATE_TIME_".toLowerCase(), "UPDATE_TIME_");

        SysBoAttr attr;
        for(Iterator var4 = list.iterator(); var4.hasNext(); mapField.put(attr.getName().toLowerCase(), attr.getName())) {
            attr = (SysBoAttr)var4.next();
            if(!attr.single()) {
                mapField.put(attr.getName().toLowerCase() + "_NAME".toLowerCase(), attr.getName() + "_NAME".toLowerCase());
            }
        }

        return mapField;
    }

    private Map<String, SysBoAttr> getAttrObjMap(SysBoEnt boEnt) {
        HashMap mapField = new HashMap();
        List list = boEnt.getSysBoAttrs();

        SysBoAttr attr;
        for(Iterator var4 = list.iterator(); var4.hasNext(); mapField.put(attr.getName().toLowerCase(), attr)) {
            attr = (SysBoAttr)var4.next();
            if(!attr.single()) {
                mapField.put(attr.getName().toLowerCase() + "_NAME".toLowerCase(), attr);
            }
        }

        return mapField;
    }
}
