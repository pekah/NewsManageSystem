package com.zyl.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.bean.Admin;
import com.zyl.bean.Users;
import com.zyl.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	@Qualifier("loginService")
	private LoginService loginService;
	
	
	
	@RequestMapping("login")
	public ModelAndView login(HttpSession session,String username,String password,String identity) throws Exception
	{
		String username1 = new String(username.getBytes("ISO-8859-1"),"gbk");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.jsp");
		if(identity.equals("users"))//用户登录
		{
			Users u = loginService.loginForUsersService(username1, password);
			if(u.getUname() != null)
			{
				session.setAttribute("name", u.getUname());
				return new ModelAndView("forward:/specifyNewsList.do");
			}
		}
		else//管理员登录
		{
			Admin a = loginService.loginForAdminService(username1, password);
			if(a.getAname() != null)
			{
				session.setAttribute("name", a.getAname());
				mv.setViewName("admin_index.jsp");
			}
		}
		
		return mv;
	}	
	
	
	

}
