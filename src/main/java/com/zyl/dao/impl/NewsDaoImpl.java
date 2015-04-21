package com.zyl.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zyl.bean.News;
import com.zyl.dao.NewsDao;
import com.zyl.util.MongoManager;

@Repository("newsDao")
public class NewsDaoImpl implements NewsDao {

	@Autowired
	private JdbcTemplate template;

	private DBCollection coll;

	private void obtainColl() {
		DB db = MongoManager.getDB();
		coll = db.getCollection("news");
	}
	
	private void obtainCollByName(String collName){
		DB db = MongoManager.getDB();
		coll = db.getCollection(collName);		
	}

	public void addNews(News news) {

		obtainColl();
		
		BasicDBObject _news = new BasicDBObject("NTitle", news.getNtitle())
				.append("NContent", news.getNcontent()).append("NTime", news.getNtime())
				.append("NAuthor", news.getNauthor()).append("NEditor", news.getNeditor())
				.append("CID", news.getCategoryId());

		coll.insert(_news);

		// template.update("INSERT INTO News(NTitle,NContent,NTime,NAuthor,NEditor,CID) VALUES(?,?,?,?,?,?)",
		// new Object[]{title,content,time,author,editor,cid});
	}

	public void removeNews(String title) {
		//1,删除评论.2,删除新闻
		obtainColl();
		
		BasicDBObject newsQuery = new BasicDBObject("NTitle",title);
		
		DBObject news = coll.findOne(newsQuery);
		
		ObjectId newsId = (ObjectId) news.get("_id");
		
		//删除评论
		obtainCollByName("review");
		BasicDBObject reviewQuery = new BasicDBObject("NId", newsId);
		coll.remove(reviewQuery);
		
		//删除新闻
		obtainCollByName("news");
		coll.remove(newsQuery);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		// mysql删除语句写法：DELETE FROM XXX WHERE XXX = ？
//		int nid = template.queryForInt("SELECT NID FROM News WHERE NTitle = ?",
//				title);
//		// 先删除所有的评论
//		template.update("DELETE FROM Review WHERE NID = ?", nid);
//		// 再删除新闻
//		template.update("DELETE FROM News WHERE NID = ?", nid);

	}

	public Map<String,Object> getNewsTitlesByCateName(String cname) {
		
		Map<String,Object> result = new HashMap<String,Object>();
		List<String> title = new ArrayList<String>();
		List<String> time = new ArrayList<String>();
		List<String> nid = new ArrayList<String>();
		
		//通过栏目名获取栏目id
		obtainCollByName("category");
		BasicDBObject cateQuery = new BasicDBObject("CName",cname);
		DBObject category = coll.findOne(cateQuery);
		ObjectId cateId = (ObjectId) category.get("_id");
		
		//通过栏目id获取新闻对象
		obtainCollByName("news");
		BasicDBObject newsQuery = new BasicDBObject("CID",cateId);
		DBCursor cursor = coll.find(newsQuery);
		
		try {
			while(cursor.hasNext()){
				DBObject news = cursor.next();
				title.add(news.get("NTitle").toString());
				time.add(news.get("NTime").toString());
				nid.add(news.get("_id").toString());
			}		
			result.put("title", title);
			result.put("time",time);
			result.put("nid", nid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return result;
		
		
		
		
//		Map result = new HashMap();
//		List title = new ArrayList();
//		List time = new ArrayList();
//		List nid = new ArrayList();
//		// 通过视图CateTitleTime查询
//		List rows = template.queryForList(
//				"SELECT NID,NTitle,NTime FROM CateTitleTime WHERE CName = ?",
//				cname);
//		Iterator iter = rows.iterator();
//		while (iter.hasNext()) {
//			Map map = (Map) iter.next();
//			title.add(map.get("NTitle"));
//			Timestamp timestamp = (Timestamp) map.get("NTime");
//			time.add(timestamp.toLocaleString());
//			nid.add(map.get("NID"));
//		}
//		result.put("title", title);
//		result.put("time", time);
//		result.put("nid", nid);
		
	}

	public Map<String, Object> getNewsByNID(ObjectId nid) {
		obtainColl();
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> newsList = new ArrayList<String>();
		List<List<String>> reviewsList = new ArrayList<List<String>>();
		
		BasicDBObject newsQuery = new BasicDBObject("_id",nid);
		DBObject news = coll.findOne(newsQuery);
		
		newsList.add((String)news.get("NTitle"));
		newsList.add((String)news.get("NContent"));
		newsList.add((String)news.get("NAuthor"));
		newsList.add((String)news.get("NEditor"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String fdate = format.format(news.get("NTime"));
		newsList.add(fdate);
		
		BasicDBList ridList = (BasicDBList) news.get("RID");
		
		if(ridList != null){
			//新闻评论数量
			newsList.add(ridList.size() + "");
			
			obtainCollByName("review");
			
			//将所有评论信息存放到reviewsList
			for(Object rid : ridList){
				List<String> reviews = new ArrayList<String>();
				BasicDBObject reviewQuery = new BasicDBObject("_id", rid);
				DBObject review = coll.findOne(reviewQuery);
				reviews.add((String) review.get("RContent"));
				reviews.add((String) review.get("UName"));
				reviews.add((String) review.get("RTime"));
				
				reviewsList.add(reviews);
			}			
		}
		
		result.put("news", newsList);
		result.put("reviewsList", reviewsList);
		
//		Map result = new HashMap();
//		List news = new ArrayList();
//		List reviewsList = new ArrayList();
//		// 查出主键为nid的新闻详细信息
//		List rows1 = template
//				.queryForList(
//						"SELECT NTitle,NContent,NTime,NAuthor,NEditor FROM News WHERE NID = ?",
//						nid);
//		// 查出外键为nid的所有评论,使用了视图NIDReviewUname查询
//		List rows2 = template
//				.queryForList(
//						"SELECT RContent,RTime,UName FROM NIDReviewUname WHERE NID = ? ORDER BY RTime DESC",
//						nid);
//		// 计算外键为nid的评论总数
//		int total = template.queryForInt(
//				"SELECT COUNT(*) FROM Review WHERE NID = ?", nid);
//
//		Iterator iter1 = rows1.iterator();
//		while (iter1.hasNext()) {
//			Map map = (Map) iter1.next();
//			news.add(map.get("NTitle"));
//			news.add(map.get("NContent"));
//			news.add(map.get("NAuthor"));
//			news.add(map.get("NEditor"));
//			Timestamp timestamp = (Timestamp) map.get("NTime");
//			news.add(timestamp.toLocaleString());
//			news.add(total);
//		}
//		Iterator iter2 = rows2.iterator();
//		while (iter2.hasNext()) {
//			List reviews = new ArrayList();
//			Map map = (Map) iter2.next();
//			reviews.add(map.get("RContent"));
//			reviews.add(map.get("UName"));
//			Timestamp timestamp = (Timestamp) map.get("RTime");
//			reviews.add(timestamp.toLocaleString());
//			reviewsList.add(reviews);
//		}
//		result.put("news", news);
//		result.put("reviewsList", reviewsList);

		return result;
	}

	public Map<String,List<String>> searchNews(String keyword) {
		
		obtainColl();
		Map<String,List<String>> result = new HashMap<String,List<String>>();
		List<String> title = new ArrayList<String>();
		List<String> time = new ArrayList<String>();
		List<String> nid = new ArrayList<String>();
		
		Pattern pattern=Pattern.compile("^.*" + keyword + ".*$");//正则表达式查询
		 
		BasicDBObject newsQuery = new BasicDBObject("NTitle", pattern);
		
		DBCursor cursor = coll.find(newsQuery);
		
		try {
			while(cursor.hasNext()){
				DBObject news = cursor.next();
				title.add(news.get("NTitle").toString());
				time.add(news.get("NTime").toString());
				nid.add(news.get("NID").toString());
			}
			result.put("title", title);
			result.put("time", time);
			result.put("nid", nid);		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		
		return result;
//		Map result = new HashMap();
//		List title = new ArrayList();
//		List time = new ArrayList();
//		List nid = new ArrayList();
//		List rows = template.queryForList(
//				"SELECT NID,NTitle,NTime FROM News WHERE NTitle like ?", "%"
//						+ keyword + "%");
//		Iterator iter = rows.iterator();
//		while (iter.hasNext()) {
//			Map map = (Map) iter.next();
//			title.add(map.get("NTitle"));
//			Timestamp timestamp = (Timestamp) map.get("NTime");
//			time.add(timestamp.toLocaleString());
//			nid.add(map.get("NID"));
//		}
//		result.put("title", title);
//		result.put("time", time);
//		result.put("nid", nid);
//		return result;
	}


}
