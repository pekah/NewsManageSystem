package com.zyl.dao;

import java.util.List;
import java.util.Map;

import com.zyl.bean.Category;

public interface UsersDao {
	public String addUsers(String username,String password);
	public void removeUsers(String username);
	public String searchUIDByUName(String username);
	public void modifyUser(String username,String password);
	//查看所有用户
	public Map<String, Object> listAllUsers(String keyword, int skip, int limit);
	//订阅栏目
	public void addSubscribe(String username, String categoryName);
	//取消订阅
	public void cancelSubscribe(String username, String categoryName);
	//查看订阅
	public List<Category> viewSubscribe(String username);
}
