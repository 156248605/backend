package com.elex.oa.dao.mybatis.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
public class PageList<E> extends ArrayList<E> implements Serializable {
    private static final long serialVersionUID = 1412759446332294208L;
    private PageResult pageResult;

    public PageList() {
    }

    public PageList(Collection<? extends E> c) {
        super(c);
    }

    public PageList(Collection<? extends E> c, PageResult p) {
        super(c);
        this.pageResult = p;
    }

    public PageList(PageResult p) {
        this.pageResult = p;
    }

    public PageResult getPageResult() {
        return this.pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }
}
