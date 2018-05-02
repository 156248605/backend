package com.elex.oa.query;

/**
 *@author hugo.zhao
 *@since 2018/4/23 10:31
*/
import java.util.HashMap;
import java.util.Map;

public class SqlQueryFilter {
    private IPage page = new Page();
    private Map<String, Object> params = new HashMap();
    private SortParam sortParam = null;

    public SqlQueryFilter() {
    }

    public IPage getPage() {
        return this.page;
    }

    public void setPage(IPage page) {
        this.page = page;
    }

    public Map<String, Object> getParams() {
        if(this.getSortParam() != null) {
            this.params.put("orderByClause", this.getSortParam().toString());
        }

        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public SortParam getSortParam() {
        return this.sortParam;
    }

    public void setSortParam(SortParam sortParam) {
        this.sortParam = sortParam;
    }

    public void addFieldParam(String fieldName, Object value) {
        this.params.put(fieldName, value);
    }

    public void addSortParam(String fieldName, String ascDesc) {
        this.sortParam = new SortParam(fieldName, ascDesc);
    }
}

