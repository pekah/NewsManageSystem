package com.zyl.bean;

import java.util.Date;

import org.bson.types.ObjectId;

public class News {
	private String nid;
	private String url;
	private String ntitle;
	private String ncontent;
	private Date ntime;
	private String nauthor;
	private String neditor;
	private ObjectId categoryId;
	
	public News() {
		super();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public Date getNtime() {
		return ntime;
	}
	public void setNtime(Date ntime) {
		this.ntime = ntime;
	}
	public String getNauthor() {
		return nauthor;
	}
	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}
	public String getNeditor() {
		return neditor;
	}
	public void setNeditor(String neditor) {
		this.neditor = neditor;
	}
	public ObjectId getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(ObjectId categoryId) {
		this.categoryId = categoryId;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	
	
	
	
}
