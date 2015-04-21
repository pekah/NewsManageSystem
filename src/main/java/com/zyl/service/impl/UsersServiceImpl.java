package com.zyl.service.impl;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zyl.bean.News;
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
	/**
	 * ================================== 分页获取操作
	 */
	
	public Page<News> getNewsTitlesByCateId(String cname, Integer pageNumber, Integer pageSize) {
		int skip = pageSize * (pageNumber - 1);
		
		List<News> newsList = newsDao.getNewsTitlesByCateName(cname, skip, pageSize);
		long count = newsDao.getNewsCountByCateName(cname);
		
		Page<News> newsPage = new Page<News>();
		newsPage.setPageNumber(pageNumber);
		newsPage.setPageSize(pageSize);
		newsPage.setTotalPage(count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1)); 
		newsPage.setTotalRow(count);
		newsPage.setPageList(newsList);
		
		return newsPage;
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
