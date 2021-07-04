package com.springboot.springbootdemo1.entity;

public class QueryInfo {

    private String query;//查询信息 username
    private int pageNum;//当前页数
    private int pageSize;//每页最大数

    public QueryInfo(String query, int pageNum, int pageSize) {
        this.query = query;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public QueryInfo() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QueryInfo{" +
                "query='" + query + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

}
