package com.elex.oa.ui.model;
import java.util.ArrayList;
import java.util.List;
/**
 *@author hugo.zhao
 *@since 2018/4/20 16:29
*/
public class GroupColumn extends BaseGridColumn implements IGridColumn {
    private List<IGridColumn> columns = new ArrayList();

    public GroupColumn() {
    }

    public List<IGridColumn> getColumns() {
        return this.columns;
    }

    public void setColumns(List<IGridColumn> columns) {
        this.columns = columns;
    }
}
