package com.elex.oa.query;

/**
 *@author hugo.zhao
 *@since 2018/4/20 16:55
*/
public class Page implements IPage {
    public static final int DEFAULT_PAGE_SIZE = 20;
    private Integer pageSize = Integer.valueOf(20);
    private Integer totalItems = Integer.valueOf(0);
    private Integer pageIndex = Integer.valueOf(0);
    private boolean skipCountTotal = false;

    public Page() {
    }

    public Page(boolean skipCountTotal) {
        this.skipCountTotal = skipCountTotal;
    }

    public Page(Integer pageIndex, Integer pageSize) {
        this.pageIndex = Integer.valueOf(pageIndex.intValue());
        this.pageSize = pageSize;
    }

    public Page(Integer pageIndex, Integer pageSize, boolean skipCountTotal) {
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getTotalItems() {
        return this.totalItems;
    }

    public Integer getStartIndex() {
        return Integer.valueOf(this.pageIndex.intValue() * this.pageSize.intValue());
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getTotalPages() {
        int page = this.totalItems.intValue() / this.pageSize.intValue();
        return Integer.valueOf(this.totalItems.intValue() % this.pageSize.intValue() == 0?page:page + 1);
    }

    public boolean isSkipCountTotal() {
        return this.skipCountTotal;
    }

    public void setSkipCountTotal(boolean skipCountTotal) {
        this.skipCountTotal = skipCountTotal;
    }
}
