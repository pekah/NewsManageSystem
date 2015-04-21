package com.zyl.service;

import java.util.Map;

import org.bson.types.ObjectId;

import com.zyl.bean.News;
import com.zyl.controller.Page;

public interface UsersService {
	//添加用户
	public String addUsers(String username,String password);
	//删除用户
	public void deleteUsers(String username);
	//查询用户
	public int searchUIDByUName(String username);
	//更新用户信息
	public void updateUsers(String username,String password);
	//通过栏目名找到对应的新闻分页获取标题
	public Page<News> getNewsTitlesByCateId(String cname, Integer pageNumber, Integer pageSize);
	//通过新闻id查看某条新闻
	public Map getNewsByNID(ObjectId nid);
	//添加评论
	public void addReview(String nid,String uname,String content);
	//搜索新闻
	public Page<News> searchNews(String keyword, Integer pageNumber, Integer pageSize);
}
