package com.zyl.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.group.InterceptorPayload;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zyl.dao.CategoryDao;
import com.zyl.util.MongoManager;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	private JdbcTemplate template;
	
	private DBCollection coll;
	
	private void obtainColl(){
		DB db = MongoManager.getDB();
		coll = db.getCollection("category");		
	}
	
	private void obtainCollByName(String collName){
		DB db = MongoManager.getDB();
		coll = db.getCollection(collName);		
	}
	
	public void addCategory(String name) {
		
		obtainColl();
		
		BasicDBObject category = new BasicDBObject("CName",name);
		
		coll.save(category);
		
//		template.update("INSERT INTO Category(CName) VALUES(?)",
//				name);
		
	}
	
	public void removeCategory(String name) {
		ObjectId CID = getCategoryIdByName(name);//获取栏目的id
		
		//1,删除栏目的每条新闻的评论.2,删除所有新闻.3,删除栏目
		obtainCollByName("news");
		
		BasicDBObject cateQuery = new BasicDBObject("CId", CID);
		
		DBCursor cursor = coll.find(cateQuery);
		
		try {
			while(cursor.hasNext()){
				//获取每条新闻
				DBObject news = cursor.next();
				//获取该新闻id
				ObjectId newsId = (ObjectId) news.get("_id");
				BasicDBObject reviewQuery = new BasicDBObject("NId",newsId);
				//删除所有为该新闻id的评论
				obtainCollByName("review");
				coll.remove(reviewQuery);
				//删除新闻
				obtainCollByName("news");
				coll.remove(news);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		//删除栏目
		obtainCollByName("category");
		coll.remove(cateQuery);
		
		
		
		
		
//		List allNews = new ArrayList();//该栏目下的所有新闻
//		List newsRows = template.queryForList("SELECT NID FROM News WHERE CID = ?",CID);  		
//		
//		Iterator iter = newsRows.iterator();
//		while(iter.hasNext())//首先迭代删除每一条新闻对应的评论
//		{
//			Map map = (Map)iter.next();
//			template.update("DELETE FROM Review WHERE NID = ? ",
//					map.get("NID"));			
//			
//		}
//
//		//然后删除栏目下的所有新闻
//		template.update("DELETE FROM News WHERE CID = ?",
//				CID);
//		
//		//最后删除栏目
//		template.update("DELETE FROM Category WHERE CID = ? ",
//				CID);
	}
	
	public List getCategorys() {
//		List result = new ArrayList();
//		List rows = template.queryForList("SELECT CName FROM Category");  		
//		Iterator iter = rows.iterator();
//		while(iter.hasNext())
//		{
//			Map map = (Map) iter.next();  
//			result.add(map.get("CName"));
//		}
//		return result;
		
		List<String> result = new ArrayList<String>();
		
		obtainColl();
		
		DBCursor cursor = coll.find();
		
		try {
			while(cursor.hasNext()) {
				DBObject category = cursor.next();
				String cateName = (String) category.get("CName");
				result.add(cateName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		
		return result;
	}
	
	public ObjectId getCategoryIdByName(String name) {
		
		obtainColl();
		
		BasicDBObject query = new BasicDBObject("CName", name);
		
		DBObject category = coll.findOne(query);
		
		ObjectId cid = (ObjectId) category.get("_id");
		
//		int cid = template.queryForInt("SELECT CID FROM Category WHERE CName = ?",name);
		return cid;
	}
	
}
