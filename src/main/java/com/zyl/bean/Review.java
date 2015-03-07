package com.zyl.bean;

import java.sql.Date;

public class Review {
	private int rid;
	private String rcontent;
	private Date rtime;
	private News news;
	private Users users;
	
	public Review(String rcontent, Date rtime, News news, Users users) {
		super();
		this.rcontent = rcontent;
		this.rtime = rtime;
		this.news = news;
		this.users = users;
	}
	public Review() {
		super();
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public Date getRtime() {
		return rtime;
	}
	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public int getRid() {
		return rid;
	}
	
	
	
}
