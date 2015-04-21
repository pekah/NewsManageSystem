package com.zyl.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.zyl.bean.News;
import com.zyl.dao.CategoryDao;
import com.zyl.dao.NewsDao;
import com.zyl.dao.UsersDao;
import com.zyl.service.AdminService;
import com.zyl.util.TimeUtil;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;
	
	@Autowired
	@Qualifier("usersDao")
	private UsersDao usersDao;
	
	@Autowired
	@Qualifier("newsDao")
	private NewsDao newsDao;
	
	public void addCategory(String name) {
		categoryDao.addCategory(name);
	}
	
	public void removeCategory(String name){
		categoryDao.removeCategory(name);
	}
	
	public List getCategorys() {
		return categoryDao.getCategorys();
	}
	
	//手动添加新闻
	public void addNews(News news, String category){
		ObjectId cid = categoryDao.getCategoryIdByName(category);
		news.setCategoryId(cid);
		try {
			news.setNtime(TimeUtil.getCurrentTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newsDao.addNews(news);
	}

	public void adddNewsByRedis(String title, String author, String editor,
			String category, String content) {
			Gson gson = new Gson();
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("title", title);
			map.put("author", author);
			map.put("editor", editor);
			map.put("content", content);
			
			String news = gson.toJson(map);
			
			
	}
	
	public void removeNews(String title) {
		newsDao.removeNews(title);
	}
	
	public void addUsers(String username, String password) {
		// TODO Auto-generated method stub
		usersDao.addUsers(username, password);
	}
}
