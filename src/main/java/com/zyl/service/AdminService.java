package com.zyl.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.zyl.bean.Category;
import com.zyl.bean.News;

public interface AdminService {
	public void addCategory(String name);
	public ObjectId getCIdByCName(String cname);
	public void removeCategory(String name);
	public List<Category> getCategorys();
	public void addNews(News news, String category);
	public void adddNewsByRedis(String title,String author,String editor,String category,String content);
	public void removeNews(String title);
	public void addUsers(String username,String password);
}
