package com.zyl.bean;

public class Category {
	private int cid;
	private String cname;
	private News news;
	
	public Category(String cname, News news) {
		super();
		this.cname = cname;
		this.news = news;
	}
	public Category() {
		super();
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public int getCid() {
		return cid;
	}
	
	
}
