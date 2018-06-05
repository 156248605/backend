package com.elex.oa.dao;

import com.elex.oa.core.entity.SqlModel;
import com.elex.oa.dao.mybatis.domain.DefaultPage;
import com.elex.oa.dao.mybatis.domain.PageList;
import com.elex.oa.dao.mybatis.domain.PageResult;
import com.elex.oa.query.Page;
import com.elex.oa.query.QueryFilter;
import com.elex.oa.query.SortParam;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.apache.ibatis.annotations.Mapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
@Component
public class CommonDao {
    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;
    private static final String NAME_SPACE = "com.redxun.sql.common";

    public CommonDao() {
    }

    public void execute(String sql) {
        HashMap map = new HashMap();
        map.put("sql", sql);
        String key = this.getNameSpace("execute");
        this.sqlSessionTemplate.update(key, map);
    }

    public void execute(String sql, Map<String, Object> params) {
        HashMap map = new HashMap();
        map.put("sql", sql);
        if(BeanUtil.isNotEmpty(params)) {
            map.putAll(params);
        }

        String key = this.getNameSpace("execute");
        this.sqlSessionTemplate.update(key, map);
    }

    public void execute(SqlModel sqlModel) {
        HashMap map = new HashMap();
        map.put("sql", sqlModel.getSql());
        if(BeanUtil.isNotEmpty(sqlModel.getParams())) {
            map.putAll(sqlModel.getParams());
        }

        String key = this.getNameSpace("execute");
        this.sqlSessionTemplate.update(key, map);
    }

    public List query(String sql) {
        HashMap map = new HashMap();
        map.put("sql", sql);
        String key = this.getNameSpace("query");
        return this.sqlSessionTemplate.selectList(key, map);
    }

    public List query(String sql, Map<String, Object> params) {
        HashMap map = new HashMap();
        map.put("sql", sql);
        if(BeanUtil.isNotEmpty(params)) {
            map.putAll(params);
        }

        String key = this.getNameSpace("query");
        return this.sqlSessionTemplate.selectList(key, map);
    }

    public List query(SqlModel sqlModel) {
        HashMap map = new HashMap();
        map.put("sql", sqlModel.getSql());
        if(BeanUtil.isNotEmpty(sqlModel.getParams())) {
            map.putAll(sqlModel.getParams());
        }

        String key = this.getNameSpace("query");
        return this.sqlSessionTemplate.selectList(key, map);
    }

    public Object queryOne(SqlModel sqlModel) {
        HashMap map = new HashMap();
        map.put("sql", sqlModel.getSql());
        if(BeanUtil.isNotEmpty(sqlModel.getParams())) {
            map.putAll(sqlModel.getParams());
        }

        String key = this.getNameSpace("queryObject");
        return this.sqlSessionTemplate.selectOne(key, map);
    }

    public Map queryForMap(SqlModel sqlModel) {
        HashMap map = new HashMap();
        map.put("sql", sqlModel.getSql());
        if(BeanUtil.isNotEmpty(sqlModel.getParams())) {
            map.putAll(sqlModel.getParams());
        }

        String key = this.getNameSpace("query");
        return (Map)this.sqlSessionTemplate.selectOne(key, map);
    }

    public PageList query(String sql, Map<String, Object> params, Page page) {
        HashMap map = new HashMap();
        map.put("sql", sql);
        if(BeanUtil.isNotEmpty(params)) {
            map.putAll(params);
        }

        String key = this.getNameSpace("query");
        return (PageList)this.sqlSessionTemplate.selectList(key, map, new RowBounds(page.getStartIndex().intValue(), page.getPageSize().intValue()));
    }

    private String getNameSpace(String sqlKey) {
        return "com.redxun.sql.common." + sqlKey;
    }

    public List queryForList(String sql, QueryFilter queryFilter, Map<String, Object> params) {
        if(CollectionUtils.isEmpty((Map)params)) {
            params = new HashMap();
        }

        ((Map)params).put("sql", sql);
        Map filter = this.parseGridFilter(queryFilter);
        ((Map)params).putAll(filter);
        return this.sqlSessionTemplate.selectList(this.getNameSpace("queryList"), params);
    }

    public PageList queryDynamicList(String sql, QueryFilter filter, Map<String, Object> params) {
        if(CollectionUtils.isEmpty((Map)params)) {
            params = new HashMap();
        }

        Map newParams = this.parseGridFilter(filter);
        ((Map)params).putAll(newParams);
        this.buildSqlClause(sql, (Map)params);
        DefaultPage p = new DefaultPage(new RowBounds(filter.getPage().getStartIndex().intValue(), filter.getPage().getPageSize().intValue()));
        return (PageList)this.sqlSessionTemplate.selectList(this.getNameSpace("queryDynamic"), params, p);
    }

    private void buildSqlClause(String sql, Map<String, Object> params) {
        sql = sql.replaceAll("(?is)\\s+", " ");
        String nSql = sql.toUpperCase();
        int lWhereIndex = nSql.lastIndexOf(" WHERE ");
        int orderByIndex = nSql.lastIndexOf(" ORDER BY ");
        String whereClause = null;
        String orderClause = null;
        String selectClause = null;
        if(lWhereIndex != -1 && orderByIndex != -1) {
            whereClause = sql.substring(lWhereIndex, orderByIndex);
            selectClause = sql.substring(0, lWhereIndex);
        } else if(lWhereIndex != -1 && orderByIndex == -1) {
            whereClause = sql.substring(lWhereIndex);
            selectClause = sql.substring(0, lWhereIndex);
        } else if(lWhereIndex == -1 && orderByIndex != -1) {
            whereClause = " WHERE 1=1 ";
            selectClause = sql.substring(0, orderByIndex);
        } else {
            whereClause = " WHERE 1=1 ";
            selectClause = sql;
        }

        if(params.containsKey("whereSql")) {
            whereClause = whereClause + " AND (" + params.get("whereSql") + ")";
        }

        if(!params.containsKey("orderBySql") && orderByIndex != -1) {
            orderClause = sql.substring(orderByIndex);
            params.put("orderBySql", orderClause);
        } else if(params.containsKey("orderBySql")) {
            params.put("orderBySql", " ORDER BY " + params.get("orderBySql"));
        }

        params.put("sql", selectClause);
        params.put("whereSql", whereClause);
    }

    public PageList queryForListPage(String sql, QueryFilter queryFilter, Map<String, Object> params) {
        Assert.notNull(sql);
        if(CollectionUtils.isEmpty((Map)params)) {
            params = new HashMap();
        }

        ((Map)params).put("sql", sql);
        Map filter = this.parseGridFilter(queryFilter);
        ((Map)params).putAll(filter);
        DefaultPage p = new DefaultPage(new RowBounds(queryFilter.getPage().getStartIndex().intValue(), queryFilter.getPage().getPageSize().intValue()));
        return (PageList)this.sqlSessionTemplate.selectList(this.getNameSpace("queryList"), params, p);
    }

    public PageList queryByCusPage(String sql, String countSql, Map<String, Object> params, Integer startIndex, Integer pageSize) {
        List list = this.queryByCusPage(sql, params);
        Integer tc = this.getCount(countSql, params);
        return new PageList(list, new PageResult(startIndex.intValue(), pageSize.intValue(), tc.intValue()));
    }

    private List queryByCusPage(String sql, Map<String, Object> params) {
        if(CollectionUtils.isEmpty((Map)params)) {
            params = new HashMap();
        }

        ((Map)params).put("sql", sql);
        return this.sqlSessionTemplate.selectList(this.getNameSpace("query"), params);
    }

    private Integer getCount(String sql, Map<String, Object> params) {
        Assert.notNull(sql);
        if(CollectionUtils.isEmpty((Map)params)) {
            params = new HashMap();
        }

        ((Map)params).put("sql", sql);
        return (Integer)this.sqlSessionTemplate.selectOne(this.getNameSpace("getCount"), params);
    }

    public Map<String, Object> parseGridFilter(QueryFilter queryFilter) {
        queryFilter.setParams();
        Map params = queryFilter.getParams();
        String dynamicWhereSql = queryFilter.getFieldLogic().getSql();
        if(StringUtil.isNotEmpty(dynamicWhereSql)) {
            params.put("whereSql", dynamicWhereSql);
        }

        if(queryFilter.getOrderByList().size() > 0) {
            StringBuffer sb = new StringBuffer();
            Iterator var5 = queryFilter.getOrderByList().iterator();

            while(var5.hasNext()) {
                SortParam sortParam = (SortParam)var5.next();
                sb.append(sortParam.getSql()).append(",");
            }

            sb.deleteCharAt(sb.length() - 1);
            params.put("orderBySql", sb.toString());
        }

        return params;
    }
}

