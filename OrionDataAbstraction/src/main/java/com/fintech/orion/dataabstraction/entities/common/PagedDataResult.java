package com.fintech.orion.dataabstraction.entities.common;

/**
 * Created by ChathurangaRW on 9/13/2016.
 */
public class PagedDataResult {
    private int totalRows;
    private int currentPage;
    private Object data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
