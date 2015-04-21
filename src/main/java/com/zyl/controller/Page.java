package com.zyl.controller;

import java.util.List;

/**
 * 分页包装对象
 * 
 * @param <T>
 */
public class Page<T> {
    /**
     * 页号
     */
    private Integer pageNumber;
    /**
     * 每页数据数
     */
    private Integer pageSize;
    /**
     * 数据集合
     */
    private List<T> pageList;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 总行数
     */
    private long totalRow;

    public Page(Integer pageNumber, Integer pageSize, List<T> pageList) {
	this.pageNumber = pageNumber;
	this.pageSize = pageSize;
	this.pageList = pageList;
    }

    public Page() {
    }

    public Integer getPageNumber() {
	return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
	this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
	return pageSize;
    }

    public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
    }

    public List<T> getPageList() {
	return pageList;
    }

    public void setPageList(List<T> pageList) {
	this.pageList = pageList;
    }

    public long getTotalPage() {
	return totalPage;
    }

    public void setTotalPage(long totalPage) {
	this.totalPage = totalPage;
    }

    public long getTotalRow() {
	return totalRow;
    }

    public void setTotalRow(long totalRow) {
	this.totalRow = totalRow;
    }

}
