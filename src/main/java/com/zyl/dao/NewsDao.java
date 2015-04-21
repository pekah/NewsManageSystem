package com.zyl.dao;

import java.util.Map;

import org.bson.types.ObjectId;

import com.zyl.bean.News;

public interface NewsDao {
	public void addNews(News news);
	public void removeNews(String title);
	//通过栏目名找到对应的新闻所有标题
	public Map getNewsTitlesByCateName(String cname);
	//通过新闻id查看某条新闻
	public Map getNewsByNID(ObjectId nid);
	//通过关键词模糊搜索新闻
	public Map searchNews(String keyword);
}
