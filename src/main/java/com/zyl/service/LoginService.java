package com.zyl.service;

import com.zyl.bean.Admin;
import com.zyl.bean.Users;

public interface LoginService {
	public Users loginForUsersService(String username,String password);
	public Admin loginForAdminService(String username,String password);
}
