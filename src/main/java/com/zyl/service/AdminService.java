package com.zyl.service;

import java.util.List;

public interface AdminService {
	public void addCategory(String name);
	public void removeCategory(String name);
	public List getCategorys();
	public void addNews(String title,String author,String editor,String category,String content);
	public void adddNewsByRedis(String title,String author,String editor,String category,String content);
	public void removeNews(String title);
	public void addUsers(String username,String password);
}
