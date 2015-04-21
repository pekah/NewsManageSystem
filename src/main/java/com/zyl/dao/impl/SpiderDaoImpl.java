package com.zyl.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zyl.bean.News;
import com.zyl.dao.SpiderDao;
import com.zyl.util.MongoManager;

@Repository("spiderDao")
public class SpiderDaoImpl implements SpiderDao {

	private DBCollection coll;

	private void obtainColl() {
		DB db = MongoManager.getDB();
		coll = db.getCollection("news");
	}
	
	private void obtainCollByName(String collName){
		DB db = MongoManager.getDB();
		coll = db.getCollection(collName);		
	}
	
	/**
	 * 通过类别获得最新的一条新闻标题
	 */
	public String getLatestNewsTitleByCateId(ObjectId categoryId){
		obtainColl();
		String title = "";
		DBCursor cursor = null;

		BasicDBObject findBy = new BasicDBObject("CID", categoryId);
		BasicDBObject sortBy = new BasicDBObject("NTime",-1);
		
		cursor = coll.find(findBy);
		cursor = cursor.sort(sortBy).limit(1);
		
		if(cursor == null)
			return "";
		
		while(cursor.hasNext()){
			DBObject news = (DBObject)cursor.next();
			title = (String) news.get("NTitle");
		}
		
		return title;
		
	}
	
	
}
