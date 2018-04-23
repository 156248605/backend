package com.elex.oa.ui.model;


public class BaseGridColumn implements IGridColumn {
    private Integer width;
    private String header;
    private String headerAlign = "center";
    private boolean visible = false;

    public BaseGridColumn() {
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeaderAlign() {
        return this.headerAlign;
    }

    public void setHeaderAlign(String headerAlign) {
        this.headerAlign = headerAlign;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
