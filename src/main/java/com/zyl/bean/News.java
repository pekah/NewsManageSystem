package com.zyl.bean;

import java.util.Date;

public class News {
	private int nid;
	private String url;
	private String ntitle;
	private String ncontent;
	private Date ntime;
	private String nauthor;
	private String neditor;
	
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
	public int getNid() {
		return nid;
	}
	
	
}
