package com.zyl.dao;

import java.util.List;

import com.zyl.bean.Users;

public interface UsersDao {
	public String addUsers(String username,String password);
	public void deleteUsers(String username);
	public String searchUIDByUName(String username);
	public void updateUsers(String username,String password);
}
