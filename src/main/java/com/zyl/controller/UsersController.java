package com.zyl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	@Qualifier("usersService")
	private UsersService usersService;
	
	@RequestMapping("register")
	public ModelAndView register(HttpSession session,String username,String password) throws Exception
	{
		String username1 = new String(username.getBytes("ISO-8859-1"),"gbk");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users_index.jsp");
		String message = usersService.addUsers(username1, password);
		session.setAttribute("name", username1);
		return mv;
	}	
	
	@RequestMapping("specifyNewsList")
	public ModelAndView specifyNewsList(@RequestBody String cateName)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("json");
		Map result = usersService.getNewsTitlesByCateName(cateName);
		mv.addObject("data",result);
		return mv;
	}

	@RequestMapping("viewNews")
	public ModelAndView viewNews(HttpServletRequest req,String nid)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewNews.jsp");
		Map result = usersService.getNewsByNID(new ObjectId(nid));
		mv.addObject("data",result);
//		req.setAttribute("total", ((List)result.get("news")).get(5));//获取评论总数//获取评论总数，待优化，不应该重新读取新闻
		req.setAttribute("total", 0);//获取评论总数
		req.setAttribute("nid", nid);
		return mv;
	}
	
	@RequestMapping("addReview")
	public ModelAndView addReview(HttpServletRequest req,HttpSession session,String nid,String content) throws Exception
	{
		String reviewContent = new String(content.getBytes("ISO-8859-1"),"gbk");
		String uname = (String) session.getAttribute("name");
		usersService.addReview(nid, uname, reviewContent);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewNews.jsp");
		Map result = usersService.getNewsByNID(new ObjectId(nid));
		mv.addObject("data",result);
//		req.setAttribute("total", ((List)result.get("news")).get(5));//获取评论总数，待优化，不应该重新读取新闻
		req.setAttribute("total", 0);//获取评论总数
		req.setAttribute("nid", nid);
		return mv;
	}
	
	@RequestMapping("searchNews")
	public ModelAndView searchNews(@RequestBody String keyword)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("json");
		Map result = usersService.searchNews(keyword);
		mv.addObject("data",result);;
		return mv;
	}
}
