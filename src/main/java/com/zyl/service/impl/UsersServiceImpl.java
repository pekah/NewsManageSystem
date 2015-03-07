package com.zyl.service.impl;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zyl.dao.CategoryDao;
import com.zyl.dao.NewsDao;
import com.zyl.dao.ReviewDao;
import com.zyl.dao.UsersDao;
import com.zyl.service.UsersService;
import com.zyl.util.GetDate;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
	@Autowired
	@Qualifier("usersDao")
	private UsersDao usersDao;
	
	@Autowired
	@Qualifier("newsDao")
	private NewsDao newsDao;
	
	@Autowired
	@Qualifier("categoryDao")
	private CategoryDao categoryDao;
	
	@Autowired
	@Qualifier("reviewDao")
	private ReviewDao reviewDao;	
	
	public String addUsers(String username, String password) {
		String message = usersDao.addUsers(username, password);
		return message;
	}

	public void deleteUsers(String username) {
		// TODO Auto-generated method stub
		
	}
	public int searchUIDByUName(String username) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void updateUsers(String username, String password) {
		// TODO Auto-generated method stub
		
	}
	public Map getNewsTitlesByCateName(String cateName) {
		return newsDao.getNewsTitlesByCateName(cateName);
	}
	
	public Map getNewsByNID(ObjectId nid) {
		return newsDao.getNewsByNID(nid);
	}
	
	public void addReview(String nid, String uname, String content) {
		String uid = usersDao.searchUIDByUName(uname);
		reviewDao.addReview(nid, uid, content, GetDate.getDate());
	}
	
	public Map searchNews(String keyword) {
		return 	newsDao.searchNews(keyword);
	}
}
