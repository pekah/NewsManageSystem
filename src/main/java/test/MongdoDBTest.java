//package test;
//
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.DBDecoder;
//import com.mongodb.DBEncoder;
//import com.mongodb.DBObject;
//import com.mongodb.ReadPreference;
//import com.mongodb.WriteConcern;
//import com.mongodb.WriteResult;
//import com.zyl.util.MongoManager;
//
//
//public class MongdoDBTest {
//	
//	public static void main(String[] args) throws Exception {
////		Properties pro = new Properties();
////		FileInputStream fis = new FileInputStream("src/main/java/db.properties");
////		pro.load(fis);
////		fis.close();
////		String host = pro.getProperty("host");
////		int port = Integer.parseInt(pro.getProperty("port").toString());
////		String dbname = pro.getProperty("dbname");
//		
////		DBObject myDoc = coll.findOne();
////		
////		System.out.println(myDoc);
////		
////		System.out.println("coll`s count is : " + coll.getCount());
//		
//		//向games插入100条数据
////		for(int i = 0; i < 100; i++){
////			coll.insert(new BasicDBObject("i",i));
////		}
//		
//		DBCursor cursor;
//
//		//插入新闻
////		query = new BasicDBObject("NTitle", "知乎的逼格很高吗，跟Quora相比呢\n抛砖引玉，欢迎展开讨论")
////		.append("NContent", "Quora更高").append("NTime", now)
////		.append("NAuthor", "周亿霖").append("NEditor", "郑映琪").append("CId", null).append("RId", "ObjectId('54bf51d03ea3124f7d24f7cd')");
////		
////		coll.insert(query);
//		
//		//删除新闻
////		query = new BasicDBObject("_id",new ObjectId("54c04ffc2f98d6a61a953910"));
////		
////		coll.remove(query);
//		
////		//插入评论
////		query = new BasicDBObject("RContent","不错").append("RTime", now)
////				.append("UId", new ObjectId("54bf4aeb5cb5b48243fb85df")).append("UName", "pekah");
////		
////		coll.insert(query);
//		
////		查询games里的所有数据
//		cursor = coll.find();
//		
//		try {
//			while(cursor.hasNext()){
//				System.out.println(cursor.next());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			cursor.close();
//		}
//		
//		
//		//按范围查询
////		query = new BasicDBObject("i", new BasicDBObject("$gt", 20).append("$lte", 30));
////		
////		cursor = coll.find(query);
////		
////		try {
////		    while (cursor.hasNext()) {
////		        System.out.println(cursor.next());
////		    }
////		} finally {
////		    cursor.close();
////		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	}
//}
