package com.zyl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zyl.bean.Admin;
import com.zyl.bean.Users;
import com.zyl.dao.LoginDao;
import com.zyl.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Autowired
	@Qualifier("loginDao")
	private LoginDao loginDao;
	
	public Users loginForUsersService(String username, String password) {
		Users users = loginDao.loginForUsersDAO(username, password);
		return users;
	}
	public Admin loginForAdminService(String username, String password) {
		Admin admin = loginDao.loginForAdminDAO(username, password);
		return admin;
	}
}
