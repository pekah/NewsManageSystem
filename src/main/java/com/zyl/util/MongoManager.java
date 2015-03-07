package com.zyl.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoOptions;

public class MongoManager {
	
	private static MongoClient mongo = null;
	
	private static String host;
	private static int port;
	private static String dbname;
	private static int poolSize;
	private static int blockSize;
	
	private MongoManager(){
		
	}
	
	public static DB getDB(){
		if(mongo == null){
			init();
		}
		return mongo.getDB(dbname);
	}
	
	private static void init(){
		initProperties();
		
		try {
			mongo = new MongoClient(host, port);
			MongoOptions opt = mongo.getMongoOptions();
			opt.connectionsPerHost = poolSize;
			opt.threadsAllowedToBlockForConnectionMultiplier = blockSize;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void initProperties(){
		//从db.properties配置文件获取ip,port,poolSize,dbname等配置信息
		Properties pro = new Properties();
		InputStream is; 
		
		try {
			is = MongoManager.class.getResourceAsStream("/db.properties");
			pro.load(is);
			is.close();
			//fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		host = pro.getProperty("host");
		port = new Integer(pro.getProperty("port").toString());
		dbname = pro.getProperty("dbname");
		poolSize = new Integer(pro.getProperty("poolSize").toString());
		blockSize = new Integer(pro.getProperty("blockSize").toString());
	}
}
