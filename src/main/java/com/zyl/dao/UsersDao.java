package com.zyl.dao;

import java.util.Map;

public interface UsersDao {
	public String addUsers(String username,String password);
	public void removeUsers(String username);
	public String searchUIDByUName(String username);
	public void updateUsers(String username,String password);
	//查看所有用户
	public Map<String, Object> listAllUsers(String keyword, int skip, int limit);
}
