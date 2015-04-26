package com.zyl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.bean.News;
import com.zyl.service.AdminService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;
	
	@RequestMapping("addCate")
	public ModelAndView addCategory(String cateName) throws Exception
	{
		String name = new String(cateName.getBytes("ISO-8859-1"),"gbk");
		ModelAndView mv = new ModelAndView();
		adminService.addCategory(name);
		mv.setViewName("admin_index.jsp");
		return mv;
	}
	
	@RequestMapping("removeCate")
	public ModelAndView removeCategory(String cateName) throws Exception
	{
		String name = new String(cateName.getBytes("ISO-8859-1"),"gbk");
		ModelAndView mv = new ModelAndView();
		adminService.removeCategory(name);
		mv.setViewName("admin_index.jsp");
		return mv;
	}	
	
	@RequestMapping("getCategorys")
	public ModelAndView getCategorys()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("json");
		List result = adminService.getCategorys();
		mv.addObject("data",result);
		return mv;
	}
	
	@RequestMapping("addNewsShow")
	public ModelAndView addNewsShow()
	{
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("admin/news-add.jsp");
		return mv;
	}	
	
	@RequestMapping("addNews")
	public ModelAndView addNews(String title,String author,String editor,String category,String content) throws Exception
	{
		String title1 = new String(title.getBytes("ISO-8859-1"),"gbk");
		String author1 = new String(author.getBytes("ISO-8859-1"),"gbk");
		String editor1 = new String(editor.getBytes("ISO-8859-1"),"gbk");
		String category1 = new String(category.getBytes("ISO-8859-1"),"gbk");
		String content1 = new String(content.getBytes("ISO-8859-1"),"gbk");
		
		News news = new News();
		news.setNtitle(title1);
		news.setNauthor(author1);
		news.setNeditor(editor1);
		news.setNcontent(content1);
		
		ModelAndView mv = new ModelAndView();		
		adminService.addNews(news, category1);
		mv.setViewName("admin_index.jsp");
		return mv;
	}
	
	@RequestMapping("removeNews")
	public ModelAndView removeNews(String newsTitle) throws Exception
	{
		String title = new String(newsTitle.getBytes("ISO-8859-1"),"gbk");
		ModelAndView mv = new ModelAndView();
		adminService.removeNews(title);
		mv.setViewName("admin_index.jsp");
		return mv;
	}	

	@RequestMapping("addUsers")
	public ModelAndView addUsers(String username,String password) throws Exception
	{
		String username1 = new String(username.getBytes("ISO-8859-1"),"gbk");
		ModelAndView mv = new ModelAndView();
		adminService.addUsers(username1, password);
		mv.setViewName("admin_index.jsp");
		return mv;
	}
	
}
