package com.zyl.dao;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.zyl.bean.News;

public interface NewsDao {
	public void addNews(News news);
	public void removeNews(String title);
	//通过栏目名获取新闻
	public List<News> getNewsByCateName(String cname, int skip, int limit);
	//通过栏目名获取最新的N条新闻
	public List<News> getLatestNewsByCateName(String cname, int total);
	//通过栏目名找到对应新闻的总数
	public long getNewsCountByCateName(String cname);
	//通过新闻id查看某条新闻
	public Map getNewsByNID(ObjectId nid);
	//通过关键词模糊搜索新闻
	public Map<String, Object> searchNews(String keyword, int skip, int limit);
	//查看所有新闻
	public Map<String, Object> listAllNews(String keyword, int skip, int limit);
	//通过id删除新闻
	public void removeNewsById(ObjectId id);
}
