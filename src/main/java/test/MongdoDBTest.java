package test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.zyl.util.MongoManager;


public class MongdoDBTest {
	
	public static void main(String[] args) throws Exception {
//		Properties pro = new Properties();
//		FileInputStream fis = new FileInputStream("src/main/java/db.properties");
//		pro.load(fis);
//		fis.close();
//		String host = pro.getProperty("host");
//		int port = Integer.parseInt(pro.getProperty("port").toString());
//		String dbname = pro.getProperty("dbname");
//		
//		DBObject myDoc = coll.findOne();
		DB db = MongoManager.getDB();
		DBCollection coll = db.getCollection("news");
		
		DBCursor cursor = null;

		BasicDBObject sortBy = new BasicDBObject("NTime",-1);
		cursor = coll.find().sort(sortBy).limit(1);
		
		try {
			while(cursor.hasNext()){
				System.out.println(cursor.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}		
		
		
//		System.out.println(myDoc);
//		
//		System.out.println("coll`s count is : " + coll.getCount());
		
		//向games插入100条数据
//		for(int i = 0; i < 100; i++){
//			coll.insert(new BasicDBObject("i",i));
//		}
		
		
		//插入新闻
//		query = new BasicDBObject("NTitle", "知乎的逼格很高吗，跟Quora相比呢\n抛砖引玉，欢迎展开讨论")
//		.append("NContent", "Quora更高").append("NTime", now)
//		.append("NAuthor", "周亿霖").append("NEditor", "郑映琪").append("CId", null).append("RId", "ObjectId('54bf51d03ea3124f7d24f7cd')");
//		
//		coll.insert(query);
		
		//删除新闻
//		query = new BasicDBObject("_id",new ObjectId("54c04ffc2f98d6a61a953910"));
//		
//		coll.remove(query);
		
//		//插入评论
//		query = new BasicDBObject("RContent","不错").append("RTime", now)
//				.append("UId", new ObjectId("54bf4aeb5cb5b48243fb85df")).append("UName", "pekah");
//		
//		coll.insert(query);
		
//		查询games里的所有数据
//		cursor = coll.find();
		
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
		
		//按范围查询
//		query = new BasicDBObject("i", new BasicDBObject("$gt", 20).append("$lte", 30));
//		
//		cursor = coll.find(query);
//		
//		try {
//		    while (cursor.hasNext()) {
//		        System.out.println(cursor.next());
//		    }
//		} finally {
//		    cursor.close();
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
