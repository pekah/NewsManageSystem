package com.zyl.dao;

import java.util.Map;

import org.bson.types.ObjectId;

public interface NewsDao {
	public void addNews(String title,String author,String editor,ObjectId cid,String content,String time);
	public void removeNews(String title);
	//通过栏目名找到对应的新闻所有标题
	public Map getNewsTitlesByCateName(String cname);
	//通过新闻id查看某条新闻
	public Map getNewsByNID(ObjectId nid);
	//通过关键词模糊搜索新闻
	public Map searchNews(String keyword);
}
