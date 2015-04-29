package com.zyl.util;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Repository("newsUtil")
public class NewsUtil {
	
	private DBCollection coll;

	private void obtainColl() {
		DB db = MongoManager.getDB();
		coll = db.getCollection("news");
	}
	
	public boolean newsIsExist(ObjectId cid, String title){
		
		obtainColl();
		
		DBCursor cursor = null;

		BasicDBObject findBy = new BasicDBObject("CID", cid).append("NTitle", title);
		
		cursor = coll.find(findBy);
		
		if(cursor.hasNext()){
			return true;
		}
		
		return false;
			
	}
}
