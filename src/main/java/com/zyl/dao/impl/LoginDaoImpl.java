package com.zyl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.zyl.bean.Admin;
import com.zyl.bean.Users;
import com.zyl.dao.LoginDao;
import com.zyl.util.MongoManager;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{
	@Autowired
	private JdbcTemplate template;
	
	private DBCollection coll;
	
	private void obtainColl(String collName){
		DB db = MongoManager.getDB();
		coll = db.getCollection(collName);		
	}
	
	
	
	public Users loginForUsersDAO(String username, String password) {
		
		obtainColl("users");
		
		Users users = new Users();
		
		BasicDBObject usersQuery = new BasicDBObject("UName", username).append("UPassword", password);
		
		DBObject usersObeject = coll.findOne(usersQuery);
		
		if(null != usersObeject && !"".equals(usersObeject)){
			ObjectId uid = (ObjectId)usersObeject.get("_id");
			users.setUid(uid.toString());
			users.setUname(username);
			users.setUpassword(password);
		}
		
		return users;
		
//		final Users users = new Users();
//		template.query("SELECT * FROM Users WHERE UName = ? AND UPassword = ?",
//				new Object[]{username,password},
//				new RowCallbackHandler(){
//				public void processRow(ResultSet rs) throws SQLException {
//					users.setUid(rs.getInt("UID"));
//					users.setUname(rs.getString("UName"));
//					users.setUpassword(rs.getString("UPassword"));
//				}
//		});
//		return users;
	}
	public Admin loginForAdminDAO(String username, String password) {
		
		obtainColl("admins");
		
		Admin admins = new Admin();
		
		BasicDBObject adminsQuery = new BasicDBObject("AName", username).append("APassword", password);
		
		DBObject adminsObeject = coll.findOne(adminsQuery);
		
		if(null != adminsObeject && !"".equals(adminsObeject)){
			ObjectId aid = (ObjectId)adminsObeject.get("_id");
			admins.setAid(aid.toString());
			admins.setAname(username);
			admins.setApassword(password);
		}
		
		return admins;
		
//		final Admin admin = new Admin();
//		template.query("SELECT * FROM Admin WHERE AName = ? AND APassword = ?",
//				new Object[]{username,password},
//				new RowCallbackHandler(){
//				public void processRow(ResultSet rs) throws SQLException {
//					admin.setAid(rs.getInt("AID"));
//					admin.setAname(rs.getString("AName"));
//					admin.setApassword(rs.getString("APassword"));
//				}
//		});
//		return admin;
	}
}
