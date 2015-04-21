package com.zyl.bean;

import org.bson.types.ObjectId;

public class Category {
	private ObjectId cid;
	private String cname;
	
	public ObjectId getCid() {
		return cid;
	}
	public void setCid(ObjectId cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
	
}
