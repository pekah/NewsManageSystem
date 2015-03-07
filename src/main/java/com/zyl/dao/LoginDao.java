package com.zyl.dao;

import com.zyl.bean.Admin;
import com.zyl.bean.Users;

public interface LoginDao {
	public Users loginForUsersDAO(String username,String password);
	public Admin loginForAdminDAO(String username,String password);
}
