package com.zyl.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.bean.Category;
import com.zyl.bean.News;
import com.zyl.bean.Users;
import com.zyl.service.AdminService;
import com.zyl.service.UsersService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;
	
	@Autowired
	@Qualifier("usersService")
	private UsersService userSerivce;
	
//	@RequestMapping("addCate")
//	public ModelAndView addCategory(String cateName) throws Exception
//	{
//		String name = new String(cateName.getBytes("ISO-8859-1"),"gbk");
//		ModelAndView mv = new ModelAndView();
//		adminService.addCategory(name);
//		mv.setViewName("admin_index.jsp");
//		return mv;
//	}
	
	@RequestMapping("removeCate")
	public ModelAndView removeCategory(String cateName) throws Exception
	{
		String name = new String(cateName.getBytes("ISO-8859-1"),"gbk");
		ModelAndView mv = new ModelAndView();
		adminService.removeCategory(name);
		mv.setViewName("admin_index.jsp");
		return mv;
	}	
	
	@RequestMapping("news-spider-index")
	public ModelAndView forwardSpider(HttpSession session, String cname, Integer pageNumber){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/spider-index.jsp");
		return mv;
	}	
	
	@RequestMapping("news-listAllNews")
	public ModelAndView listAllNews(String keyword, Integer pageNumber) throws UnsupportedEncodingException{
		if(pageNumber == null){
			pageNumber = 1;
		}
		
		if (keyword == null || keyword.length() <= 0) {
		    keyword = "";
		}
		
		String kw = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
		
		ModelAndView mv = new ModelAndView();
		
		Page<News> newsPage = userSerivce.listAllNews(kw, pageNumber, Constants.pageSize);
		
		mv.addObject("newsPage", newsPage);
		mv.addObject("keyword", kw);
		mv.addObject("pageNumber", pageNumber);
		
		mv.setViewName("admin_index2.jsp");
		
		return mv;
	}
	
	@RequestMapping("news-listAllCategory")
	public ModelAndView listAllCategory(HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		String uname = (String) session.getAttribute("name"); 
		
		List<Category> catrgories = userSerivce.viewSubscribe(uname);
		
		//如果当前用户未订阅新闻，则显示所有的新闻
		if(catrgories == null){
			catrgories = adminService.getCategorys();
		}
		
		mv.addObject("catrgories",catrgories);
		mv.setViewName("admin/category-list.jsp");
		
		return mv;
		
//		List<Category> catrgories = adminService.getCategorys();
//		
//		mv.addObject("catrgories",catrgories);
//		mv.setViewName("admin/category-list.jsp");
//		
//		return mv;
	}	
	
	@RequestMapping("news-listAllCategoryAdmin")
	public ModelAndView listAllCategoryAdmin(){
		ModelAndView mv = new ModelAndView();
		
		List<Category> catrgories = adminService.getCategorys();
		
		mv.addObject("catrgories",catrgories);
		mv.setViewName("admin/category-list.jsp");
		
		return mv;
	}	
	
	
	@RequestMapping("getCategorys")
	public ModelAndView getCategorys()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("json");
		List<Category> result = adminService.getCategorys();
		mv.addObject("data",result);
		return mv;
	}
	
	@RequestMapping("category-add-show")
	public ModelAndView addCategoryShow(String cname)
	{
		ModelAndView mv = new ModelAndView();	
		mv.addObject("cname", cname);
		mv.setViewName("admin/category-add-show.jsp");
		return mv;
	}	
	
	@RequestMapping("category-add-operate")
	public ModelAndView addCategory(String cname) throws Exception
	{
		ModelAndView mv = new ModelAndView();		
		adminService.addCategory(cname);
		mv.addObject("status","success");
		mv.setViewName("json");
		return mv;
	}
		

	
	@RequestMapping("news-add-show")
	public ModelAndView addNewsShow()
	{
		ModelAndView mv = new ModelAndView();		
		List<Category> catrgories = adminService.getCategorys();
		mv.addObject("result",catrgories);
		mv.setViewName("admin/news-add-show.jsp");
		return mv;
	}	
	
	@RequestMapping("news-add-operate")
	public ModelAndView addNews(String title,String author,String editor,String category,String content) throws Exception
	{
		News news = new News();
		news.setNtitle(title);
		news.setNauthor(author);
		news.setNeditor(editor);
		news.setNcontent(content);
		
		ModelAndView mv = new ModelAndView();		
		adminService.addNews(news, category);
		mv.addObject("status","success");
		mv.setViewName("json");
		return mv;
	}
	
	@RequestMapping("category-del-show")
	public ModelAndView delCategoryShow(String cname)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("cname", cname);
		mv.setViewName("admin/category-del-show.jsp");
		return mv;
	}		
	
	@RequestMapping("news-del-show")
	public ModelAndView delNewsShow(String nid)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("nid", nid);
		mv.setViewName("admin/news-del-show.jsp");
		return mv;
	}	
	
	@RequestMapping("category-del-operate")
	public ModelAndView delCategory(String cname)
	{
		ModelAndView mv = new ModelAndView();

		adminService.removeCategory(cname);
		
		mv.setViewName("json");
		mv.addObject("status","success");
		
		return mv;
	}	
	
	@RequestMapping("news-del-operate")
	public ModelAndView delNews(String nid)
	{
		ModelAndView mv = new ModelAndView();

		ObjectId id = new ObjectId(nid);
		
		adminService.removeNewsById(id);
		
		mv.setViewName("json");
		mv.addObject("status","success");
		
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

	@RequestMapping("users-add-show")
	public ModelAndView addUsersShow(String username,String password) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/users-add-show.jsp");
		return mv;
	}
	
	@RequestMapping("users-del-show")
	public ModelAndView delUsersShow(String uname)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("uname", uname);
		mv.setViewName("admin/users-del-show.jsp");
		return mv;
	}	
	
	@RequestMapping("users-modify-show")
	public ModelAndView modifyUsersShow(String uname)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("uname", uname);
		mv.setViewName("admin/users-modify-show.jsp");
		return mv;
	}	

	@RequestMapping("news-modify-show")
	public ModelAndView modifyNewsShow(String nid)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("nid", nid);
		Map<String, Object> map = userSerivce.getNewsByNID(new ObjectId(nid));
		ArrayList<String> news = (ArrayList<String>) map.get("news");
		mv.addObject("news", news);
		mv.setViewName("admin/news-modify-show.jsp");
		return mv;
	}	
	
	@RequestMapping("users-modify-operate")
	public ModelAndView modifyUsers(String uname, String password)
	{
		ModelAndView mv = new ModelAndView();
		adminService.modifyUser(uname, password);
		mv.setViewName("json");
		return mv;
	}	
	
	@RequestMapping("users-del-operate")
	public ModelAndView delUsers(String uname)
	{
		ModelAndView mv = new ModelAndView();
		adminService.removeUsers(uname);
		mv.setViewName("json");
		mv.addObject("status","success");
		
		return mv;
	}	
	
	@RequestMapping("users-add-operate")
	public ModelAndView addUsers(String username, String password) throws Exception
	{
		ModelAndView mv = new ModelAndView();		
		adminService.addUsers(username, password);
		mv.addObject("status","success");
		mv.setViewName("json");
		return mv;
	}
	
	@RequestMapping("news-listAllUsers")
	public ModelAndView listAllUsers(String keyword, Integer pageNumber) throws UnsupportedEncodingException{
		if(pageNumber == null){
			pageNumber = 1;
		}
		
		if (keyword == null || keyword.length() <= 0) {
		    keyword = "";
		}
		
		String kw = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
		
		ModelAndView mv = new ModelAndView();
		
		Page<Users> usersPage = userSerivce.listAllUsers(kw, pageNumber, Constants.pageSize);
		
		mv.addObject("usersPage", usersPage);
		mv.addObject("keyword", kw);
		mv.addObject("pageNumber", pageNumber);
		
		mv.setViewName("admin/users-list.jsp");
		
		return mv;
	}

}
