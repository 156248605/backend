package com.elex.oa.ui.model;

/**
 *@author hugo.zhao
 *@since 2018/4/20 16:31
*/
public class FieldColumn extends BaseGridColumn implements IGridColumn {
    private String field;
    private boolean allowSort = false;

    public FieldColumn() {
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isAllowSort() {
        return this.allowSort;
    }

    public void setAllowSort(boolean allowSort) {
        this.allowSort = allowSort;
    }
}
