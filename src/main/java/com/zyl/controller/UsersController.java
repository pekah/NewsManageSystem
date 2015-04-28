package com.zyl.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.bean.Category;
import com.zyl.bean.News;
import com.zyl.service.AdminService;
import com.zyl.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	@Qualifier("usersService")
	private UsersService usersService;
	
	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;
	
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
	public ModelAndView specifyNewsList(String cname, Integer pageNumber)
	{
		if(cname == null || "".equals(cname)){
			cname = "羊城晚报";
		}else{
			try {
				cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		
		if(pageNumber == null){
			pageNumber = 1;
		}

		ModelAndView mv = new ModelAndView();
		
		mv.addObject("currentCName", cname);
		
		//获取所有栏目
		List<Category> category = adminService.getCategorys();
		mv.addObject("category",category);

		Page<News> newsPage = usersService.getNewsByCateId(cname, pageNumber, Constants.pageSize);
		
		mv.addObject("newsPage",newsPage);
		mv.addObject("pageNumber", pageNumber);
		
		mv.setViewName("users_index.jsp");
		
		return mv;
	}
	
	@RequestMapping("searchNews")
	public ModelAndView searchNews(String keyword, Integer pageNumber)
	{
		if (keyword == null || keyword.length() <= 0) {
		    keyword = "";
		}else{
			try {
				keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(pageNumber == null){
			pageNumber = 1;
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("currentCName", "搜索结果");
		
		//获取所有栏目
		List<Category> category = adminService.getCategorys();
		mv.addObject("category",category);
		
		Page<News> newsPage = usersService.searchNews(keyword, pageNumber, Constants.pageSize);
		
		mv.addObject("newsPage",newsPage);
		mv.addObject("pageNumber", pageNumber);
		mv.addObject("keyword", keyword);
		
		mv.setViewName("users_index.jsp");
		
		return mv;
	}

	@RequestMapping("viewNews")
	public ModelAndView viewNews(HttpServletRequest req,String nid)
	{
		ModelAndView mv = new ModelAndView();
		
		//获取所有栏目
		List<Category> category = adminService.getCategorys();
		mv.addObject("category",category);
		
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
	

}
