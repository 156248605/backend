package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/23 10:28
*/
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import com.elex.oa.json.JSONUtil;
import com.elex.oa.query.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QueryFilterBuilder {
    private static Log logger = LogFactory.getLog(QueryFilterBuilder.class);
    public static final String ORDER_ASC = "1";
    public static final String ORDER_DESC = "2";
    private static Pattern regex3 = Pattern.compile("Q_(.+)_(.+)_(.+)", 106);
    private static Pattern regex2 = Pattern.compile("Q_(.+)_(.+)", 106);
    private static Pattern regex1 = Pattern.compile("Q_(.+)", 106);

    public QueryFilterBuilder() {
    }

    public static SqlQueryFilter createSqlQueryFilter(HttpServletRequest request) {
        SqlQueryFilter filter = new SqlQueryFilter();
        Page page = new Page();
        int pageIndex = RequestUtil.getInt(request, "pageIndex", 0);
        int pageSize = RequestUtil.getInt(request, "pageSize", 20);
        page.setPageSize(Integer.valueOf(pageSize));
        page.setPageIndex(Integer.valueOf(pageIndex));
        filter.setPage(page);
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        if(StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortOrder)) {
            SortParam params = new SortParam(sortField, sortOrder);
            filter.setSortParam(params);
        }

        Map params1 = getSqlQueryParams(request);
        filter.setParams(params1);
        return filter;
    }

    public static SqlQueryFilter createSqlQueryFilter(JSONObject cmdObj) {
        SqlQueryFilter filter = new SqlQueryFilter();
        Page page = new Page();
        int pageIndex = JSONUtil.getInt(cmdObj, "pageIndex", 0).intValue();
        int pageSize = JSONUtil.getInt(cmdObj, "pageSize", 20).intValue();
        page.setPageSize(Integer.valueOf(pageSize));
        page.setPageIndex(Integer.valueOf(pageIndex));
        filter.setPage(page);
        String sortField = JSONUtil.getString(cmdObj, "sortField");
        String sortOrder = JSONUtil.getString(cmdObj, "sortOrder");
        if(StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortOrder)) {
            SortParam params = new SortParam(sortField, sortOrder);
            filter.setSortParam(params);
        }

        Map params1 = getSqlQueryParams(cmdObj);
        filter.setParams(params1);
        return filter;
    }

    public static SqlQueryFilter createSqlQueryFilter(String jsonParams) {
        JSONObject cmdObj = JSONObject.fromObject(jsonParams);
        return createSqlQueryFilter(cmdObj);
    }

    public static Page createPage(HttpServletRequest request) {
        Page page = new Page();
        int pageIndex = RequestUtil.getInt(request, "pageIndex", 0);
        int pageSize = RequestUtil.getInt(request, "pageSize", 20);
        page.setPageSize(Integer.valueOf(pageSize));
        page.setPageIndex(Integer.valueOf(pageIndex));
        return page;
    }

    public static QueryFilter createQueryFilter(JSONObject cmdObj) {
        QueryFilter filter = new QueryFilter();
        Page page = new Page();
        int pageIndex = JSONUtil.getInt(cmdObj, "pageIndex", 0).intValue();
        int pageSize = JSONUtil.getInt(cmdObj, "pageSize", 20).intValue();
        page.setPageSize(Integer.valueOf(pageSize));
        page.setPageIndex(Integer.valueOf(pageIndex));
        filter.setPage(page);
        String sortField = JSONUtil.getString(cmdObj, "sortField");
        String sortOrder = JSONUtil.getString(cmdObj, "sortOrder");
        if(StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortOrder)) {
            SortParam params = new SortParam(sortField, sortOrder);
            filter.getOrderByList().add(params);
        }

        Map params1 = getSqlQueryParams(cmdObj);
        filter.setParams(params1);
        return filter;
    }

 /*   public static QueryFilter createQueryFilter(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter();
        String preAttName = request.getParameter("joinAttName");
        if(StringUtils.isEmpty(preAttName)) {
            preAttName = "";
        } else {
            filter.setSelectJoinAtt(preAttName);
            preAttName = preAttName + ".";
        }

        int pageIndex = RequestUtil.getInt(request, "pageIndex", 0);
        int pageSize = RequestUtil.getInt(request, "pageSize", 20);
        Page page = (Page)filter.getPage();
        page.setPageSize(Integer.valueOf(pageSize));
        page.setPageIndex(Integer.valueOf(pageIndex));
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        if(StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortOrder)) {
            SortParam searchId = new SortParam(preAttName + sortField, sortOrder);
            filter.getOrderByList().add(searchId);
        }

        String searchId1 = request.getParameter("_searchId");
        List paramsList;
        if(StringUtils.isNotEmpty(searchId1)) {
            SysSearchItemManager filters = (SysSearchItemManager)AppBeanUtil.getBean(SysSearchItemManager.class);
            paramsList = filters.getSearchItemTreeNodes(searchId1);
            Iterator var11 = paramsList.iterator();

            while(var11.hasNext()) {
                SearchItemNode node = (SearchItemNode)var11.next();
                IWhereClause iWhereClause = constructClause(node);
                filter.getFieldLogic().getCommands().add(iWhereClause);
            }
        } else {
            String filters1 = request.getParameter("filter");
            paramsList = null;
            if(StringUtils.isNotEmpty(filters1)) {
                paramsList = constructParams(request, preAttName);
            } else {
                paramsList = getQueryParams(request);
            }

            filter.getFieldLogic().setCommands(paramsList);
        }

        return filter;
    }*/

/*    public static List<IWhereClause> constructParams(HttpServletRequest request, String preAttName) {
        String filters = request.getParameter("filter");
        ArrayList paramList = new ArrayList();
        if(StringUtils.isEmpty(filters)) {
            return paramList;
        } else {
            JSONArray array = JSONArray.fromObject(filters);

            for(int i = 0; i < array.size(); ++i) {
                JSONObject obj = array.getJSONObject(i);
                String property = (String)obj.get("name");
                if(property.startsWith("Q_")) {
                    String value = obj.get("value").toString();
                    if(StringUtils.isNotEmpty(value)) {
                        paramList.add(getQueryParam(preAttName, property, new String[]{value}));
                    }
                }
            }

            return paramList;
        }
    }*/

    public static Map<String, Object> getSqlQueryParams(HttpServletRequest request) {
        HashMap qParams = new HashMap();
        Enumeration params = request.getParameterNames();

        while(params.hasMoreElements()) {
            String key = params.nextElement().toString();
            String[] values = request.getParameterValues(key);
            if(key.startsWith("Q_") && values.length > 0 && StringUtils.isNotEmpty(values[0])) {
                String[] keys = key.split("[_]");
                Object val = null;
                if(keys.length == 2) {
                    val = getObjValue("EQ", "S", values[0]);
                } else if(keys.length == 3) {
                    val = getObjValue("EQ", keys[2], values[0]);
                } else if(keys.length == 4) {
                    val = getObjValue(keys[3], keys[2], values[0]);
                }

                if(val != null) {
                    qParams.put(keys[1], val);
                }
            }
        }

        return qParams;
    }

    private static Map<String, Object> getSqlQueryParams(JSONObject jsonObj) {
        HashMap params = new HashMap();
        Iterator keyIt = jsonObj.keys();

        while(keyIt.hasNext()) {
            String key = (String)keyIt.next();
            String val = JSONUtil.getString(jsonObj, key);
            if(!StringUtils.isEmpty(val)) {
                String[] keys = key.split("[_]");
                Object pVal = null;
                if(keys.length == 2) {
                    pVal = getObjValue("EQ", "S", val);
                } else if(keys.length == 3) {
                    pVal = getObjValue("EQ", keys[2], val);
                } else if(keys.length == 4) {
                    pVal = getObjValue(keys[3], keys[2], val);
                }

                if(pVal != null) {
                    params.put(keys[1], pVal);
                }
            }
        }

        return params;
    }

    public static List<IWhereClause> getQueryParams(HttpServletRequest request) {
        ArrayList paramList = new ArrayList();
        Enumeration params = request.getParameterNames();

        while(params.hasMoreElements()) {
            String key = params.nextElement().toString();
            String[] values = request.getParameterValues(key);
            if(values.length > 0 && StringUtils.isNotEmpty(values[0]) && key.startsWith("Q_")) {
                paramList.add(getQueryParam(key, values));
            }
        }

        return paramList;
    }

    private static QueryParam getQueryParam(String key, String[] values) {
        return getQueryParam((String)null, key, values);
    }

    private static QueryParam getQueryParam(String preAttr, String key, String[] values) {
        QueryParam param = new QueryParam();
        if(preAttr == null) {
            preAttr = "";
        }

        Matcher regexMatcher = regex3.matcher(key);
        String name;
        String opType;
        if(regexMatcher.find()) {
            name = regexMatcher.group(1);
            opType = regexMatcher.group(2);
            String opType1 = regexMatcher.group(3);
            param.setFieldName(preAttr + name);
            param.setFieldType(opType);
            param.setOpType(opType1);
            Object value = getObjValue(param.getOpType(), param.getFieldType(), values[0]);
            if(value != null) {
                param.setValue(value);
            }
        } else {
            regexMatcher = regex2.matcher(key);
            if(regexMatcher.find()) {
                name = regexMatcher.group(1);
                opType = regexMatcher.group(2);
                param.setFieldName(name);
                param.setOpType(opType);
                param.setValue(values[0]);
            } else {
                regexMatcher = regex1.matcher(key);
                if(regexMatcher.find()) {
                    name = regexMatcher.group(1);
                    param.setFieldName(name);
                    param.setOpType("EQ");
                    param.setValue(values[0]);
                }
            }
        }

        return param;
    }

    private static Object getObjValue(String opType, String fieldType, String paramValue) {
        Object value = null;

        try {
            if("S".equals(fieldType)) {
                if("LK".equals(opType)) {
                    value = "%" + paramValue + "%";
                } else if("LEK".equals(opType)) {
                    value = paramValue + "%";
                } else if("RIK".equals(opType)) {
                    value = "%" + paramValue;
                } else {
                    value = paramValue;
                }
            } else if("L".equals(fieldType)) {
                value = new Long(paramValue);
            } else if("I".equals(fieldType)) {
                value = new Integer(paramValue);
            } else if("DB".equals(fieldType)) {
                value = new Double(paramValue);
            } else if("BD".equals(fieldType)) {
                value = new BigDecimal(paramValue);
            } else if("FT".equals(fieldType)) {
                value = new Float(paramValue);
            } else if("SN".equals(fieldType)) {
                value = new Short(paramValue);
            } else if("D".equals(fieldType)) {
                paramValue = paramValue.replace("T", " ");
                value = DateUtil.parseDate(paramValue);
                if("LE".equals(opType)) {
                    value = DateUtil.setEndDay((Date)value);
                } else if("GE".equals(opType)) {
                    value = DateUtil.setStartDay((Date)value);
                }
            } else {
                value = paramValue;
            }
        } catch (Exception var5) {
            logger.error(var5.getMessage());
        }

        return value;
    }

/*    public static IWhereClause constructClause(SearchItemNode node) {
        Object clause = null;
        if("Field".equalsIgnoreCase(node.getNodeType())) {
            QueryParam fieldLogic = new QueryParam();
            fieldLogic.setFieldName(node.getFieldName());
            fieldLogic.setFieldType(getDataQueryType(node.getFieldType()));
            fieldLogic.setOpType(getDataOpType(node.getFieldOp()));
            Object val = getObjValue(fieldLogic.getOpType(), fieldLogic.getFieldType(), node.getFieldVal());
            if(val != null) {
                fieldLogic.setValue(val);
            }

            clause = fieldLogic;
        } else if(!"Field".equalsIgnoreCase(node.getNodeType()) && node.getChildren().size() > 0) {
            FieldLogic fieldLogic1 = new FieldLogic(node.getNodeType());
            Iterator val1 = node.getChildren().iterator();

            while(val1.hasNext()) {
                SearchItemNode subNode = (SearchItemNode)val1.next();
                IWhereClause tempClause = constructClause(subNode);
                if(tempClause != null) {
                    fieldLogic1.getCommands().add(tempClause);
                }
            }

            clause = fieldLogic1;
        }

        return (IWhereClause)clause;
    }*/

    public static String getDataQueryType(String javaType) {
        return "java.lang.String".equals(javaType)?"S":("java.util.Date".equals(javaType)?"D":("java.lang.Short".equals(javaType)?"SN":("java.lang.Integer".equals(javaType)?"I":("java.lang.Long".equals(javaType)?"L":("java.math.BigDecimal".equals(javaType)?"BD":("java.lang.Double".equals(javaType)?"DB":("java.lang.Float".equals(javaType)?"F":"S")))))));
    }

    public static String getDataOpType(String opType) {
        return "<".equals(opType)?"LT":(">".equals(opType)?"GT":("<=".equals(opType)?"LE":(">=".equals(opType)?"GE":("=".equals(opType)?"EQ":("LIKE".equals(opType)?"LK":("EQUAL".equals(opType)?"EQ":("LEFT-LIKE".equals(opType)?"LEK":("RIGHT-LIKE".equals(opType)?"RIK":"EQ"))))))));
    }
}

