package com.elex.oa.ui.model;

/**
 *@author hugo.zhao
 *@since 2018/4/20 16:26
*/
public interface IGridColumn extends IColumn {
    Integer getWidth();

    String getHeaderAlign();

    String getHeader();

    boolean isVisible();
}
