package com.elex.oa.query;

/**
 *@author hugo.zhao
 *@since 2018/4/20 16:53
*/
import com.elex.oa.util.BeanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.JoinType;
public class QueryFilter {
    private IPage page = new Page();
    private FieldLogic fieldLogic = new FieldLogic();
    private List<SortParam> orderByList = new ArrayList();
    private Map<String, Object> params = new LinkedHashMap();
    private String selectJoinAtt = null;
    private String selectAtts = null;

    public QueryFilter() {
    }

    private Map<String, QueryParam> getQueryParams(FieldLogic logic) {
        HashMap queryParams = new HashMap();

        for(int i = 0; i < this.fieldLogic.getCommands().size(); ++i) {
            IWhereClause param = (IWhereClause)logic.getCommands().get(i);
            if(param instanceof QueryParam) {
                QueryParam p = (QueryParam)param;
                queryParams.put(p.getFieldName(), p);
            } else if(param instanceof FieldLogic) {
                queryParams.putAll(this.getQueryParams((FieldLogic)param));
            }
        }

        return queryParams;
    }

    public Map<String, QueryParam> getQueryParams() {
        return this.getQueryParams(this.fieldLogic);
    }

    public void addSortParam(String fieldName, String orderBy) {
        this.orderByList.add(new SortParam(fieldName, orderBy));
    }

    public void addWhereClause(IWhereClause clause) {
        this.fieldLogic.getCommands().add(clause);
    }

    public void addFieldParam(String fieldName, Object val) {
        this.fieldLogic.getCommands().add(new QueryParam(fieldName, "EQ", val));
    }

    public void addLeftLikeFieldParam(String fieldName, Object val) {
        this.fieldLogic.getCommands().add(new QueryParam(fieldName, "LEK", val));
    }

    public void addLikeFieldParam(String fieldName, Object val) {
        this.fieldLogic.getCommands().add(new QueryParam(fieldName, "LK", val));
    }

    public void addFieldParam(String fieldName, String op, Object val) {
        this.fieldLogic.getCommands().add(new QueryParam(fieldName, op, val));
    }

    public void addFieldParam(String fieldName, JoinType joinType, String op, Object val) {
        this.fieldLogic.getCommands().add(new QueryParam(fieldName, joinType, op, val));
    }

/*    public void addTenantParam(String tanentId) {
        String curDomain = ContextUtil.getTenant().getDomain();
        if(WebAppUtil.getOrgMgrDomain().equals(curDomain)) {
            FieldLogic orLogic = new FieldLogic("OR");
            orLogic.getCommands().add(new QueryParam("tenantId", "EQ", tanentId));
            orLogic.getCommands().add(new QueryParam("tenantId", "EQ", "0"));
            this.fieldLogic.getCommands().add(orLogic);
        } else {
            this.addFieldParam("tenantId", tanentId);
        }

    }*/

    public String getSelectAtts() {
        return this.selectAtts;
    }

    public void setSelectAtts(String selectAtts) {
        this.selectAtts = selectAtts;
    }

    public String getOrderBySql() {
        StringBuffer sb = new StringBuffer();
        if(this.orderByList.size() > 0) {
            Iterator var2 = this.orderByList.iterator();

            while(var2.hasNext()) {
                SortParam sortParam = (SortParam)var2.next();
                sb.append(sortParam.getSql()).append(",");
            }

            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public IPage getPage() {
        return this.page;
    }

    public void setPage(IPage page) {
        this.page = page;
    }

    public FieldLogic getFieldLogic() {
        return this.fieldLogic;
    }

    public void setFieldLogic(FieldLogic fieldLogic) {
        this.fieldLogic = fieldLogic;
    }

    public void setParams() {
        List list = this.fieldLogic.getCommands();
        if(!BeanUtil.isEmpty(list)) {
            Iterator var2 = list.iterator();

            while(var2.hasNext()) {
                IWhereClause tmp = (IWhereClause)var2.next();
                if(tmp instanceof QueryParam) {
                    QueryParam param = (QueryParam)tmp;
                    this.addParam(param.getFieldParam(), param.getValue());
                }
            }

        }
    }

    public List<SortParam> getOrderByList() {
        return this.orderByList;
    }

    public void setOrderByList(List<SortParam> orderByList) {
        this.orderByList = orderByList;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void addParam(String name, Object val) {
        this.params.put(name, val);
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getSelectJoinAtt() {
        return this.selectJoinAtt;
    }

    public void setSelectJoinAtt(String selectJoinAtt) {
        this.selectJoinAtt = selectJoinAtt;
    }
}

