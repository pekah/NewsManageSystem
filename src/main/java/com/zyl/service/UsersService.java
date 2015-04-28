package com.zyl.service;

import java.util.Map;

import org.bson.types.ObjectId;

import com.zyl.bean.News;
import com.zyl.bean.Users;
import com.zyl.controller.Page;

public interface UsersService {
	//添加用户
	public String addUsers(String username,String password);
	//通过栏目名找到对应的新闻分页获取标题
	public Page<News> getNewsByCateId(String cname, Integer pageNumber, Integer pageSize);
	//通过新闻id查看某条新闻
	public Map getNewsByNID(ObjectId nid);
	//查看所有新闻
	public Page<News> listAllNews(String keyword, Integer pageNumber, Integer pageSize);
	//查看所有用户
	public Page<Users> listAllUsers(String keyword, Integer pageNumber, Integer pageSize);
	//添加评论
	public void addReview(String nid,String uname,String content);
	//搜索新闻
	public Page<News> searchNews(String keyword, Integer pageNumber, Integer pageSize);
}
