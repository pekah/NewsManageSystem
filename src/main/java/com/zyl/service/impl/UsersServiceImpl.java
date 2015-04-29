package com.zyl.service.impl;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zyl.bean.News;
import com.zyl.bean.Users;
import com.zyl.controller.Page;
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
	

	/**
	 * ================================== 分页获取操作
	 */
	
	public Page<News> getNewsByCateId(String cname, Integer pageNumber, Integer pageSize) {
		int skip = pageSize * (pageNumber - 1);
		
		List<News> newsList = newsDao.getNewsByCateName(cname, skip, pageSize);
		long count = newsDao.getNewsCountByCateName(cname);
		
		Page<News> newsPage = new Page<News>();
		newsPage.setPageNumber(pageNumber);
		newsPage.setPageSize(pageSize);
		newsPage.setTotalPage(count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1)); 
		newsPage.setTotalRow(count);
		newsPage.setPageList(newsList);
		
		return newsPage;
	}
	
	public List<News> getLatestNewsByCateName(String cname, int total) {
		// TODO Auto-generated method stub
		return newsDao.getLatestNewsByCateName(cname, total);
	}
	
	public Page<News> searchNews(String keyword, Integer pageNumber, Integer pageSize) {
		int skip = pageSize * (pageNumber - 1);
		
		Map<String, Object> searchResult = newsDao.searchNews(keyword, skip, pageSize);
		List<News> newsList = (List<News>) searchResult.get("newsList");
		long count = Long.parseLong(searchResult.get("count").toString());
		
		Page<News> newsPage = new Page<News>();
		newsPage.setPageNumber(pageNumber);
		newsPage.setPageSize(pageSize);
		newsPage.setTotalPage(count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1)); 
		newsPage.setTotalRow(count);
		newsPage.setPageList(newsList);
		
		return newsPage;		
	}
	
	public Page<News> listAllNews(String keyword, Integer pageNumber, Integer pageSize) {
		int skip = pageSize * (pageNumber - 1);
		
		Map<String, Object> searchResult = newsDao.listAllNews(keyword, skip, pageSize);
		@SuppressWarnings("unchecked")
		List<News> newsList = (List<News>) searchResult.get("newsList");
		long count = Long.parseLong(searchResult.get("count").toString());
		
		Page<News> newsPage = new Page<News>();
		newsPage.setPageNumber(pageNumber);
		newsPage.setPageSize(pageSize);
		newsPage.setTotalPage(count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1)); 
		newsPage.setTotalRow(count);
		newsPage.setPageList(newsList);
		
		return newsPage;		
	}
	
	public Page<Users> listAllUsers(String keyword, Integer pageNumber, Integer pageSize) {
		int skip = pageSize * (pageNumber - 1);
		
		Map<String, Object> searchResult = usersDao.listAllUsers(keyword, skip, pageSize);
		@SuppressWarnings("unchecked")
		List<Users> usersList = (List<Users>) searchResult.get("usersList");
		long count = Long.parseLong(searchResult.get("count").toString());
		
		Page<Users> usersPage = new Page<Users>();
		usersPage.setPageNumber(pageNumber);
		usersPage.setPageSize(pageSize);
		usersPage.setTotalPage(count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1)); 
		usersPage.setTotalRow(count);
		usersPage.setPageList(usersList);
		
		return usersPage;	
	}

	
	public Map getNewsByNID(ObjectId nid) {
		return newsDao.getNewsByNID(nid);
	}
	
	public void addReview(String nid, String uname, String content) {
		String uid = usersDao.searchUIDByUName(uname);
		reviewDao.addReview(nid, uid, content, GetDate.getDate());
	}
	

	
}
