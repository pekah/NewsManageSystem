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
import org.joda.time.DateTime;

import com.zyl.bean.News;

public class Spider {
	static Logger logger = Logger.getLogger(HttpClientTest.class);
	
	static String sendGet(String url,String encoding) {

		String result = "";

		BufferedReader in = null;

		// 核心应用类
		HttpClient httpClient = new DefaultHttpClient();

		// HTTP请求
		HttpUriRequest request = new HttpGet(url);
		
		request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) "
				+ "AppleWebKit/535.11 (KHTML, like Gecko) "
				+ "Chrome/17.0.963.83 " + "Safari/535.11");

		try {
			// 发送请求，返回响应
			HttpResponse response = httpClient.execute(request);
			// 从response中取出HttpEntity对象
			HttpEntity entity = response.getEntity();
			// 取出服务器返回的数据流
			InputStream stream = entity.getContent();

			in = new BufferedReader(new InputStreamReader(stream, encoding));

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
	
	
	//获取流言百科的链接
	static ArrayList<News> GetRumourLink(String content){
		ArrayList<News> newsList = new ArrayList<News>();
		
		String urlRegex = "rumor-title\"><a href=\"(.+?)\"";
		Pattern urlPa = Pattern.compile(urlRegex);
		Matcher urlMa = urlPa.matcher(content);
		boolean isFind = urlMa.find();
		
		while(isFind){
			News news = new News();
			String url = urlMa.group(1);
			url = "http://www.liuyanbaike.com" + url;
			news.setUrl(url);
			newsList.add(news);
			//System.out.println(url);
			isFind = urlMa.find();
		}
		
		return newsList;
	}
	
	//获取流言百科的内容
	static ArrayList<News> GetRumourContent(ArrayList<News> newsList) throws ParseException{
		for(News news : newsList){
			
			String newsUrl = news.getUrl();
			String content = Spider.sendGet(newsUrl,"UTF-8");
			
			//新闻标题
			//Pattern titlePa = Pattern.compile("rumor-sum\">.+?>(.+?)<.+?>(.+?)<");
			Pattern titlePa = Pattern.compile("rumor-sum\">.+?>(.+?)<.+?rumor-title\">(.+?)<");
			Matcher titleMa = titlePa.matcher(content);
			boolean isFindTitle = titleMa.find();
			
			//编辑
			Pattern editorPa = Pattern.compile("side-editor\">.+?_blank\">(.+?)<");
			Matcher editorMa = editorPa.matcher(content);	
			boolean isFindEditor = editorMa.find();
			
			//发表时间
			Pattern timePa = Pattern.compile("最后更新：(.+?)<");
			Matcher timeMa = timePa.matcher(content);		
			boolean isFindTime = timeMa.find();
			
			//概述
			Pattern descPa = Pattern.compile("rumor-desc\">.+?>(.+?)</span>.+?arrow-right\"></span>(.+?)</div>.+?真相.+?arrow-right\"></span>(.+?)</p>");
			Matcher descMa = descPa.matcher(content);			
			boolean isFindDesc = descMa.find();	
			
			
			//正文
			Pattern contentPa = Pattern.compile("rumor-content\">(.+?)<div class=\"rumor-reply\">");
			Matcher contentMa = contentPa.matcher(content);			
			boolean isFindContent = contentMa.find();
			
			if(isFindTitle){
				//System.out.println(titleMa.group(1) + "  " + titleMa.group(2));
				news.setNauthor("果壳网");//作者统一用果壳网
				news.setNtitle(titleMa.group(1).trim() + "  " + titleMa.group(2).trim());
				if(isFindEditor){
					news.setNeditor(editorMa.group(1).trim());
					//System.out.println(editorMa.group(1).trim());
				}else{
					news.setNeditor("果壳网");
				}
				
				//System.out.println(timeMa.group(1));
				//当时间是xx小时前，计算出真实时间，否则，按原时间保存
				String time = timeMa.group(1).trim();
				
				System.out.println(time);
				
				if(time.endsWith("小时前")){
					//获得小时数
					String hour = time.replace("小时前", "");
					//使用当前时间减去小时数获得真实发布时间
					DateTime dt = new DateTime();
					dt = dt.hourOfDay().setCopy(dt.getHourOfDay() - Integer.parseInt(hour));
					java.util.Date date = dt.toDate();
					news.setNtime(date);
					
				}else if(time.endsWith("分钟前")){
					//获得分钟数
					String minute = time.replace("分钟前", "");
					//使用当前时间减去分钟数获得真实发布时间
					DateTime dt = new DateTime();
					dt = dt.minuteOfDay().setCopy(dt.getMinuteOfDay() - Integer.parseInt(minute));
					java.util.Date date = dt.toDate();
					news.setNtime(date);					
				}else if(time.startsWith("今天")){
					//获得小时数
					String hour = time.replace("今天", "");
					String[] hourminute = hour.split(":");
					//设置当天的小时数
					DateTime dt = new DateTime();
					dt = dt.hourOfDay().setCopy(hourminute[0]);
					dt = dt.minuteOfHour().setCopy(hourminute[1]);
					java.util.Date date = dt.toDate();
					news.setNtime(date);					
				}else if(time.startsWith("昨天")){
					//获得小时数
					String hour = time.replace("昨天", "");
					String[] hourminute = hour.split(":");
					//设置小时数并减去一天
					DateTime dt = new DateTime();
					dt = dt.hourOfDay().setCopy(hourminute[0]);
					dt = dt.minuteOfHour().setCopy(hourminute[1]);
					dt = dt.dayOfYear().setCopy(dt.getDayOfYear() - 1);
					java.util.Date date = dt.toDate();
					news.setNtime(date);					
				}else{
				    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    java.util.Date formatTime = format.parse(time);
					news.setNtime(formatTime);
				}
				
				//概述+正文
				String content2 = descMa.group(1) + "  " + descMa.group(2) + "   <p/>真相     " + descMa.group(3) + "<p/>" + contentMa.group(1);
				
				news.setNcontent(content2);		
				
				
				System.out.println(news.getNtime());
				System.out.println(news.getNcontent());
				//正文
//				System.out.println(contentMa.group(1));
			}
		}
		
		return null;
	}
	
	//获取优酷视频（太极拳+广场舞）的标题和链接
	static ArrayList<News> GetYoukuVideo(String content){
		ArrayList<News> newsList = new ArrayList<News>();
		
		//获得优酷视频（太极拳+广场舞）的标题
		String titleRegex = "v-link\">.+?title=\"(.+?)\"";
		Pattern titlePa = Pattern.compile(titleRegex);
		Matcher titleMa = titlePa.matcher(content);
		boolean titleIsFind = titleMa.find();
		
		
		
		//获得优酷视频（太极拳+广场舞）的链接
		String urlRegex = "v-link\">.+?href=\"(.+?)\"";
		Pattern urlPa = Pattern.compile(urlRegex);
		Matcher urlMa = urlPa.matcher(content);
		boolean urlIsFind = urlMa.find();	
		
		while(titleIsFind){
			News news = new News();
			String title = titleMa.group(1);
			String url = urlMa.group(1);
			
			news.setNtitle(title);
			news.setUrl(url);
			
			newsList.add(news);
			
			titleIsFind = titleMa.find();
		}
		
		return newsList;
		
	}
	
	//获取国际新闻链接
	static ArrayList<News> GetNationalNewsLink(String content) {
		ArrayList<News> newsList = new ArrayList<News>();

		//获得国际新闻的url
		String urlRegex = "dd_bt\"><a href=\"(.+?)\">";
		Pattern urlPa = Pattern.compile(urlRegex);
		Matcher urlMa = urlPa.matcher(content);
		boolean isFind = urlMa.find();
		
		while(isFind){
			News news = new News();
			String url = urlMa.group(1);
			if(!url.startsWith("http")){
				url = "http://www.chinanews.com" + url;
				news.setUrl(url);
				newsList.add(news);
				//System.out.println(url);
			}
			isFind = urlMa.find();
		}

		return newsList;
	}
	
	//获取国际新闻内容
	static ArrayList<News> GetNationalNewsContent(ArrayList<News> newsList) throws Exception {
		
		for(News news : newsList){
			
			String newsUrl = news.getUrl();
			String content = Spider.sendGet(newsUrl,"GBK");
			
			//新闻标题
			Pattern titlePa = Pattern.compile("ad_title\">.+?<h1.+?>(.+?)</h1>");
			Matcher titleMa = titlePa.matcher(content);
			boolean isFindTitle = titleMa.find();
			
			//作者
			Pattern authorPa = Pattern.compile("source_baidu\">.+?_blank\">(.+?)</a>");
			Matcher authorMa = authorPa.matcher(content);	
			boolean isFindAuthor = authorMa.find();
			
			//编辑
			Pattern editorPa = Pattern.compile("【编辑:(.+?)】");
			Matcher editorMa = editorPa.matcher(content);	
			boolean isFindEditor = editorMa.find();
			
			//发表时间
			Pattern timePa = Pattern.compile("pubtime_baidu\">(.+?)</span>");
			Matcher timeMa = timePa.matcher(content);		
			boolean isFindTime = timeMa.find();
			
			//正文
			Pattern contentPa = Pattern.compile("正文start-->.+?>(.+?)<!--正文");
			Matcher contentMa = contentPa.matcher(content);			
			boolean isFindContent = contentMa.find();
			
			
			if(isFindTitle){
//				System.out.println(titleMa.group(1).trim());
//				
//				System.out.println(authorMa.group(1).trim());
//				if(isFindEditor){
//					System.out.println(editorMa.group(1).trim());
//				}else{
//					
//				}
//				
//				System.out.println(timeMa.group(1).trim());
//				System.out.println(contentMa.group(1).trim());
				
				
				news.setNtitle(titleMa.group(1).trim());
				news.setNauthor(authorMa.group(1).trim());
				if(isFindEditor){
					news.setNeditor(editorMa.group(1).trim());
				}else{
					news.setNeditor("国际新闻");
				}
				
			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    java.util.Date time = format.parse(timeMa.group(1));
			    news.setNtime(time);
			    
				news.setNcontent(contentMa.group(1).trim());
				
			}
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
	//获取羊城晚报新闻内容
	static ArrayList<News> GetYCWBNewsContent(ArrayList<News> newsList) throws Exception {
		
		for(News news : newsList){

			String newsUrl = news.getUrl();
			String content = Spider.sendGet(newsUrl,"UTF-8");
			
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
			
			if(isFindTitle){
			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    java.util.Date time = format.parse(timeMa.group(1));
			    news.setNtime(time);
			    
				news.setNtitle(titleMa.group(1).trim());
				if(isFindAuthor){
					news.setNauthor(authorMa.group(1).trim());
				} else {
					news.setNauthor("羊城晚报");
				}
				
				news.setNeditor(editorMa.group(1).trim());
				news.setNcontent(contentMa.group(1).trim());
				
			}
		}
		
		return newsList;
	}


}
