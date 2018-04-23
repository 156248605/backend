package com.elex.oa.entity.eqpt;

public class Page {
    private int currentPage;
    private int rows;

    public Page() {
    }


    public Page(int currentPage, int rows) {
        this.currentPage = currentPage;
        this.rows = rows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
