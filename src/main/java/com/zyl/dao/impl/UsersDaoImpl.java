package com.zyl.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.zyl.bean.Category;
import com.zyl.bean.News;
import com.zyl.bean.Users;
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
	
	private void obtainCollByName(String collName){
		DB db = MongoManager.getDB();
		coll = db.getCollection(collName);		
	}
	
	public Map<String, Object> listAllUsers(String keyword, int skip, int limit) {
		obtainColl();
		List<Users> usersList = new ArrayList<Users>();
		Map<String, Object> searchResult = new HashMap<String, Object>();
		
		BasicDBObject newsQuery = new BasicDBObject();			
		
		if(!"".equals(keyword)){
			Pattern pattern = Pattern.compile("^.*" + keyword + ".*$");//正则表达式查询
			newsQuery = new BasicDBObject("UName", pattern);
		}
		
		BasicDBObject sortBy = new BasicDBObject("UName", -1);
		
		DBCursor cursor = coll.find(newsQuery);
		//获取查询结果总数
		searchResult.put("count", cursor.size());
		
		cursor = cursor.sort(sortBy).skip(skip).limit(limit);
		
		try {
			while(cursor.hasNext()){
				Users users = new Users();
				DBObject obj = cursor.next();
				users.setUid(obj.get("_id").toString());
				users.setUname(obj.get("UName").toString());
				usersList.add(users);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		
		searchResult.put("usersList", usersList);
		
		return searchResult;
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
	public void removeUsers(String username) {
		obtainColl();
		BasicDBObject usersQuery = new BasicDBObject("UName", username);
		coll.remove(usersQuery);
		
	}
	public void modifyUser(String username, String password) {
		obtainColl();
		BasicDBObject usersQuery = new BasicDBObject("UName", username);
		BasicDBObject usersUpdate = new BasicDBObject("UName", username).append("UPassword", password);
		coll.update(usersQuery, usersUpdate);
		
		
	}
	public String searchUIDByUName(String username) {
		obtainColl();
		
		BasicDBObject userQuery = new BasicDBObject("UName", username);
		DBObject user = coll.findOne(userQuery);
		
		return user.get("_id").toString();
		
//		return template.queryForInt("SELECT UID FROM Users WHERE UName = ?",username);
	}	
	
	public void addSubscribe(String username, String categoryName) {
		obtainCollByName("usersCategory");
		
		try {
			BasicDBObject userCategory = new BasicDBObject("UName",username).append("CName", categoryName);
			coll.save(userCategory);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cancelSubscribe(String username, String categoryName) {
		obtainCollByName("usersCategory");
		
		try {
			BasicDBObject userCategory = new BasicDBObject("UName",username).append("CName", categoryName);
			coll.remove(userCategory);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Category> viewSubscribe(String username) {
		obtainCollByName("usersCategory");
		
		List<Category> categories = new ArrayList<Category>();
		
		BasicDBObject user = new BasicDBObject("UName",username);
		DBCursor cursor = coll.find(user);	
		
		try {
			while(cursor.hasNext()){
				Category cate = new Category();
				DBObject obj = cursor.next();
				cate.setCname(obj.get("CName").toString());
				categories.add(cate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		
		return categories;
	}
}
