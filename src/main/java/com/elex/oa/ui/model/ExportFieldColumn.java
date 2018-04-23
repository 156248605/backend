package com.elex.oa.ui.model;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/20 16:46
*/
public class ExportFieldColumn extends BaseGridColumn implements IGridColumn {
    private String field;
    private boolean allowSort = false;
    private int colspan;
    private List<ExportFieldColumn> childColumn;

    public ExportFieldColumn() {
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

    public List<ExportFieldColumn> getChildColumn() {
        return this.childColumn;
    }

    public void setChildColumn(List<ExportFieldColumn> childColumn) {
        this.childColumn = childColumn;
    }

    public int getColspan() {
        return this.colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }
}
