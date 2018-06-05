package com.elex.oa.sys.bo.service.impl;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

import com.elex.oa.activiti.util.ProcessHandleHelper;
import com.elex.oa.api.ContextHandlerFactory;
import com.elex.oa.core.entity.SqlModel;
import com.elex.oa.entity.bo.SysBoEnt;
import com.elex.oa.entity.core.KeyValEnt;
import com.elex.oa.org.model.IGroup;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.sys.bo.service.ISqlBuilder;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.springframework.stereotype.Service;
@Service
public class DefaultSqlBuilder implements ISqlBuilder {
    @Resource
    GroupService groupService;
    @Resource
    ContextHandlerFactory contextHandlerFactory;

    public DefaultSqlBuilder() {
    }

    public SqlModel getByFk(SysBoEnt boEnt, String fk) {
        String sql = "select * from " + boEnt.getTableName() + " where " + "REF_ID_" + "=#{fk}";
        SqlModel sqlModel = new SqlModel(sql);
        sqlModel.addParam("fk", fk);
        this.bulidCondition(sqlModel, boEnt.getName());
        return sqlModel;
    }

    private void bulidCondition(SqlModel sqlModel, String tableName) {
        Object config = ProcessHandleHelper.getObjectLocal();
        if(!BeanUtil.isEmpty(config)) {
            String right = (String)config;
            if(!StringUtil.isEmpty(right)) {
                JSONObject json = JSONObject.parseObject(right);
                if(json.containsKey(tableName)) {
                    JSONObject rightJson = json.getJSONObject(tableName);
                    String type = rightJson.getString("type");
                    if("user".equals(type)) {
                        this.handUser(sqlModel);
                    } else if("group".equals(type)) {
                        this.handGroup(sqlModel);
                    } else if("sql".equals(type)) {
                        String tmpSql = rightJson.getString("sql");
                        this.handSql(sqlModel, tmpSql);
                    }

                }
            }
        }
    }

    private void handUser(SqlModel sqlModel) {
        String userId = ContextUtil.getCurrentUserId();
        String sql = sqlModel.getSql() + " and " + "CREATE_USER_ID_" + "=#{" + "CREATE_USER_ID_" + "}";
        sqlModel.addParam("CREATE_USER_ID_", userId);
        sql = sql + " order by CREATE_TIME_ desc ";
        sqlModel.setSql(sql);
    }

    private void handGroup(SqlModel sqlModel) {
        String userId = ContextUtil.getCurrentUserId();
        IGroup group = this.groupService.getMainByUserId(userId);
        if(group != null) {
            String sql = sqlModel.getSql() + " and " + "CREATE_GROUP_ID_" + "=#{" + "CREATE_GROUP_ID_" + "}";
            sqlModel.addParam("CREATE_GROUP_ID_", group.getIdentityId());
            sql = sql + " order by CREATE_TIME_ desc ";
            sqlModel.setSql(sql);
        }
    }

    private void handSql(SqlModel sqlModel, String sql) {
        List list = this.contextHandlerFactory.getHandlers();
        HashMap vars = new HashMap();
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            KeyValEnt ent = (KeyValEnt)var5.next();
            String key = ent.getKey().replace("[", "").replace("]", "");
            if(sql.indexOf(ent.getKey()) != -1) {
                sql = sql.replace(ent.getKey(), "#{" + key + "}");
                Object val = this.contextHandlerFactory.getValByKey(ent.getKey(), vars);
                sqlModel.addParam(key, val);
            }
        }

        sqlModel.setSql(sql);
    }
}
