package com.elex.oa.dao.mybatis.domain;

import com.elex.oa.query.IPage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.elex.oa.query.SortParam;
import org.apache.ibatis.session.RowBounds;
public class DefaultPage extends RowBounds implements IPage, Serializable {
    public static final int NO_PAGE = 1;
    protected int pageNo;
    protected int pageSize;
    protected List<SortParam> orders;
    protected boolean containsTotalCount;
    protected Boolean asyncTotalCount;

    public DefaultPage() {
        this.pageNo = 1;
        this.pageSize = DEFAULT_PAGE_SIZE.intValue();
        this.orders = new ArrayList();
        this.containsTotalCount = true;
    }

    public DefaultPage(RowBounds rowBounds) {
        this.pageNo = 1;
        this.pageSize = DEFAULT_PAGE_SIZE.intValue();
        this.orders = new ArrayList();
        this.containsTotalCount = true;
        if(rowBounds instanceof DefaultPage) {
            DefaultPage pageBounds = (DefaultPage)rowBounds;
            this.pageNo = pageBounds.pageNo;
            this.pageSize = pageBounds.pageSize;
            this.orders = pageBounds.orders;
            this.containsTotalCount = pageBounds.containsTotalCount;
            this.asyncTotalCount = pageBounds.asyncTotalCount;
        } else {
            this.pageNo = rowBounds.getOffset() / rowBounds.getLimit() + 1;
            this.pageSize = rowBounds.getLimit();
        }

    }

    public DefaultPage(int limit) {
        this.pageNo = 1;
        this.pageSize = DEFAULT_PAGE_SIZE.intValue();
        this.orders = new ArrayList();
        this.containsTotalCount = true;
        this.pageSize = limit;
        this.containsTotalCount = false;
    }

    public DefaultPage(int page, int limit) {
        this(page, limit, new ArrayList(), true);
    }

    public DefaultPage(List<SortParam> orders) {
        this(1, 2147483647, orders, false);
    }

    public DefaultPage(SortParam... order) {
        this(1, 2147483647, (SortParam[])order);
        this.containsTotalCount = false;
    }

    public DefaultPage(int page, int limit, SortParam... order) {
        this(page, limit, Arrays.asList(order), true);
    }

    public DefaultPage(int page, int limit, List<SortParam> orders) {
        this(page, limit, orders, true);
    }

    public DefaultPage(int page, int limit, List<SortParam> orders, boolean containsTotalCount) {
        this.pageNo = 1;
        this.pageSize = DEFAULT_PAGE_SIZE.intValue();
        this.orders = new ArrayList();
        this.containsTotalCount = true;
        this.pageNo = page;
        this.pageSize = limit;
        this.orders = orders;
        this.containsTotalCount = containsTotalCount;
    }

    public int getPage() {
        return this.pageNo;
    }

    public void setPage(int page) {
        this.pageNo = page;
    }

    public int getLimit() {
        return this.pageSize;
    }

    public void setLimit(int limit) {
        this.pageSize = limit;
    }

    public boolean isContainsTotalCount() {
        return this.containsTotalCount;
    }

    public void setContainsTotalCount(boolean containsTotalCount) {
        this.containsTotalCount = containsTotalCount;
    }

    public List<SortParam> getOrders() {
        List list = this.orders;
        return list;
    }

    public void setOrders(List<SortParam> orders) {
        this.orders = orders;
    }

    public Boolean getAsyncTotalCount() {
        return this.asyncTotalCount;
    }

    public void setAsyncTotalCount(Boolean asyncTotalCount) {
        this.asyncTotalCount = asyncTotalCount;
    }

    public int getOffset() {
        return this.pageNo >= 1?(this.pageNo - 1) * this.pageSize:0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PageBounds{");
        sb.append("page=").append(this.pageNo);
        sb.append(", limit=").append(this.pageSize);
        sb.append(", orders=").append(this.orders);
        sb.append(", containsTotalCount=").append(this.containsTotalCount);
        sb.append(", asyncTotalCount=").append(this.asyncTotalCount);
        sb.append('}');
        return sb.toString();
    }

    public Integer getPageIndex() {
        return Integer.valueOf(this.pageNo);
    }

    public Integer getPageSize() {
        return Integer.valueOf(this.getLimit());
    }

    public Integer getStartIndex() {
        return Integer.valueOf(this.getOffset());
    }

    public Integer getTotalItems() {
        return null;
    }

    public boolean isSkipCountTotal() {
        return false;
    }
}