package com.zyl.bean;

public class Admin {
	private String aid;
	private String aname;
	private String apassword;
	
	
	public Admin() {
	}
	
	public Admin(String aname, String apassword) {
		this.aname = aname;
		this.apassword = apassword;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}
	
	
	
}
