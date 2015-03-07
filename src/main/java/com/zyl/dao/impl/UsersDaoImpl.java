package com.zyl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.zyl.dao.UsersDao;
import com.zyl.util.MongoManager;

@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {
	@Autowired
	private JdbcTemplate template;
	
	private DBCollection coll;
	
	private void obtainColl(){
		DB db = MongoManager.getDB();
		coll = db.getCollection("users");		
	}
	
	public String addUsers(String username, String password) {
		String message = "注册成功";
		try {
			obtainColl();
			
			BasicDBObject user = new BasicDBObject("UName",username).append("UPassword", password);
			
			coll.save(user);
			
		} catch (Exception e) {
			message = "注册失败，用户名已被使用";
		}
		
		return message;
		
	}
	public void deleteUsers(String username) {
		obtainColl();
		
		BasicDBObject usersQuery = new BasicDBObject("UName", username);
		
	}
	public void updateUsers(String username, String password) {
		// TODO Auto-generated method stub
		
	}
	public String searchUIDByUName(String username) {
		obtainColl();
		
		BasicDBObject userQuery = new BasicDBObject("UName", username);
		DBObject user = coll.findOne(userQuery);
		
		return user.get("_id").toString();
		
//		return template.queryForInt("SELECT UID FROM Users WHERE UName = ?",username);
	}	
}
