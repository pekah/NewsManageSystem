package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.zyl.bean.News;

public class Spider {
	static Logger logger = Logger.getLogger(HttpClientTest.class);
	
	static String sendGet(String url) {

		String result = "";

		BufferedReader in = null;

		// 核心应用类
		HttpClient httpClient = new DefaultHttpClient();

		// HTTP请求
		HttpUriRequest request = new HttpGet(url);

		try {
			// 发送请求，返回响应
			HttpResponse response = httpClient.execute(request);
			// 从response中取出HttpEntity对象
			HttpEntity entity = response.getEntity();
			// 取出服务器返回的数据流
			InputStream stream = entity.getContent();

			in = new BufferedReader(new InputStreamReader(stream, "utf-8"));

			String line;

			while ((line = in.readLine()) != null) {
				result += line;
			}

		} catch (ClientProtocolException e) {
			// 协议错误
			e.printStackTrace();
			logger.error("协议错误");
		} catch (IOException e) {
			// 网络异常
			e.printStackTrace();
			logger.error("网络异常");
		}

		return result;

	}

	static ArrayList<Zhihu> GetRecommendations(String content) {
		ArrayList<Zhihu> results = new ArrayList<Zhihu>();

		// 用来匹配url，也就是问题的链接
		// Pattern urlPattern =
		// Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
		Pattern urlPattern = Pattern
				.compile("question_link.+?href=\"(.+?)\".+?</h2>");
		Matcher urlMatcher = urlPattern.matcher(content);

		boolean isFind = urlMatcher.find();

		while (isFind) {
			Zhihu zhihuTemp = new Zhihu(urlMatcher.group(1));
			results.add(zhihuTemp);
			isFind = urlMatcher.find();
		}

		return results;
	}

	//获取羊城晚报新闻链接
	static ArrayList<News> GetYCWBNewsLink(String content) {
		ArrayList<News> newsList = new ArrayList<News>();

		//先获得新闻列表的div内容
		String divRegex = "<div class=\"news_list\">(.+?)</div>";
		Pattern divPa = Pattern.compile(divRegex);
		Matcher divMa = divPa.matcher(content);
		
		boolean divIsFind = divMa.find();
		
		while(divIsFind){
			String divContent = divMa.group(1);
			
			//再获得所有的新闻列表url
			Pattern urlPattern = Pattern.compile("<a href=\"(.+?)\".+?</a>");
			Matcher urlMatcher = urlPattern.matcher(divContent);
			boolean isFind = urlMatcher.find();
			while(isFind){
				News news = new News();
				String newsUrl = "http://news.ycwb.com/" + urlMatcher.group(1);
				news.setUrl(newsUrl);
				newsList.add(news);
				isFind = urlMatcher.find();
			}
			
			divIsFind = divMa.find();
		}
		

		return newsList;
	}
	
	//获取新闻链接
	static ArrayList<News> GetNewsLink(String content,String regex) {
		ArrayList<News> newsList = new ArrayList<News>();

		//先获得新闻列表的div内容
		String divRegex = "<div class=\"news_list\">(.+?)</div>";
		Pattern divPa = Pattern.compile(divRegex);
		Matcher divMa = divPa.matcher(content);
		
		boolean divIsFind = divMa.find();
		
		while(divIsFind){
			String divContent = divMa.group(1);
			
			//再获得所有的新闻列表url
			Pattern urlPattern = Pattern.compile("<a href=\"(.+?)\".+?</a>");
			Matcher urlMatcher = urlPattern.matcher(divContent);
			boolean isFind = urlMatcher.find();
			while(isFind){
				News news = new News();
				String newsUrl = "http://news.ycwb.com/" + urlMatcher.group(1);
				news.setUrl(newsUrl);
				newsList.add(news);
				isFind = urlMatcher.find();
			}
			
			divIsFind = divMa.find();
		}
		

		return newsList;
	}
	/**
	 * 新闻格式
	 * 	private int nid;
	 * 	private String link;//数据库还未加入
		private String ntitle;//标题
		private String ncontent;//内容
		private Date ntime;//时间
		private String nauthor;//作者
		private String neditor;//编辑
	 * @throws Exception 
	 */
	//获取新闻内容
	static ArrayList<News> GetNewsContent(ArrayList<News> newsList,String regex) throws Exception {
		
		for(News news : newsList){

			String newsUrl = news.getUrl();
			String content = Spider.sendGet(newsUrl);
			
			//新闻标题
			Pattern titlePa = Pattern.compile("<title>(.+?)</title>");
			Matcher titleMa = titlePa.matcher(content);
			boolean isFindTitle = titleMa.find();
			
			//作者
			Pattern authorPa = Pattern.compile("<author>(.+?)</author>");
			Matcher authorMa = authorPa.matcher(content);		
			boolean isFindAuthor = authorMa.find();
			
			//编辑
			Pattern editorPa = Pattern.compile("source_baidu\">来源:(.+?)</span>");
			Matcher editorMa = editorPa.matcher(content);	
			boolean isFindEditor = editorMa.find();
			
			//发表时间
			Pattern timePa = Pattern.compile("<date>(.+?).0</date>");
			Matcher timeMa = timePa.matcher(content);		
			boolean isFindTime = timeMa.find();
			
			//正文
			Pattern contentPa = Pattern.compile("main_article\">(.+?)</div>");
			Matcher contentMa = contentPa.matcher(content);			
			boolean isFindContent = contentMa.find();
			
			if(isFindTitle &&isFindAuthor&&isFindEditor&&isFindTime){
//				System.out.println(titleMa.group(1));
//				System.out.println(authorMa.group(1));
//				System.out.println(editorMa.group(1));
//				System.out.println(contentMa.group(1));
				
				//System.out.println(timeMa.group(1));
				
				//timeMa.group(1)
			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    java.util.Date parsed = format.parse("2014-11-01 23:55:00");
			    java.sql.Date sql = new java.sql.Date(parsed.getTime());
			    news.setNtime(sql);
			    
			    
				
				news.setNtitle(titleMa.group(1));
				news.setNauthor(authorMa.group(1));
				news.setNeditor(editorMa.group(1));
				
				news.setNcontent(contentMa.group(1));
				
				
			}
			
			System.out.println(news.getNtitle());
			System.out.println(news.getNtime());
			
			
		}
		
		
		
		
		
//		ArrayList<Zhihu> results = new ArrayList<Zhihu>();
//
//		// 用来匹配url，也就是问题的链接
//		// Pattern urlPattern =
//		// Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
//		Pattern urlPattern = Pattern
//				.compile("question_link.+?href=\"(.+?)\".+?</h2>");
//		Matcher urlMatcher = urlPattern.matcher(content);
//
//		boolean isFind = urlMatcher.find();
//
//		while (isFind) {
//			Zhihu zhihuTemp = new Zhihu(urlMatcher.group(1));
//			results.add(zhihuTemp);
//			isFind = urlMatcher.find();
//		}

		return newsList;
	}	

}
