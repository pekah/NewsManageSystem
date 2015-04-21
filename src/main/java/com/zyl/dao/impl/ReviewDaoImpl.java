package com.zyl.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.zyl.dao.ReviewDao;
import com.zyl.util.MongoManager;

@Repository("reviewDao")
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	private JdbcTemplate template;

	private DBCollection coll;

	private void obtainColl() {
		DB db = MongoManager.getDB();
		coll = db.getCollection("review");
	}

	private void obtainCollByName(String collName) {
		DB db = MongoManager.getDB();
		coll = db.getCollection(collName);
	}

	// 1,插入评论.2,向新闻插入评论id
	public void addReview(String nid, String uid, String content, String time) {
		// 通过用户id获取用户名
		obtainCollByName("users");
		BasicDBObject usersQuery = new BasicDBObject("_id", new ObjectId(uid));
		DBObject users = coll.findOne(usersQuery);
		String uname = (String) users.get("UName");

		// 构造评论
		obtainColl();
		BasicDBObject reviewQuery = new BasicDBObject("NID", nid)
				.append("UID", uid).append("UName", uname)
				.append("RContent", content).append("RTime", time);
		coll.insert(reviewQuery);
		// 获得评论id
		ObjectId reviewId = (ObjectId) reviewQuery.get("_id");

		obtainCollByName("news");

		ObjectId newsId = new ObjectId(nid);
		BasicDBObject newsQuery = new BasicDBObject("_id", newsId);

		// 要更新的内容
		BasicDBObject updateItem = new BasicDBObject("RID", reviewId);
		BasicDBObject updateQuery = new BasicDBObject("$push", updateItem);

		// 第一个参数是查询条件，第二个参数是更新内容
		coll.update(newsQuery, updateQuery);

		// template.update(
		// "INSERT INTO Review(RContent,RTime,NID,UID) VALUES(?,?,?,?)",
		// new Object[] { content, time, nid, uid });
	}

}
