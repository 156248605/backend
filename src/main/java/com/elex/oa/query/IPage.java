package com.elex.oa.query;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
public interface IPage {
    Integer DEFAULT_PAGE_SIZE = Integer.valueOf(20);
    String SKIP_COUNT = "_skipCount";

    Integer getPageSize();

    Integer getPageIndex();

    Integer getStartIndex();

    Integer getTotalItems();

    boolean isSkipCountTotal();
}