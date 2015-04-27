package com.zyl.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyl.bean.News;
import com.zyl.dao.CategoryDao;
import com.zyl.dao.NewsDao;
import com.zyl.dao.SpiderDao;
import com.zyl.service.SpiderService;
import com.zyl.util.Spider;
import com.zyl.util.TimeUtil;

@Service("spiderService")
public class SpiderServiceImpl implements SpiderService {

	@Autowired
	private CategoryDao cateDao;
	@Autowired
	private NewsDao newsDao;
	@Autowired
	private SpiderDao spiderDao;
	
	public void addYoukuVideo(String url, String categoryName) {
		ObjectId cateId = cateDao.getCategoryIdByName(categoryName);
		String latestNewsTitle = spiderDao.getLatestNewsTitleByCateId(cateId);
		String content = Spider.sendGet(url, "UTF-8");	
		
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
		
		//获得优酷视频（太极拳+广场舞）的上传用户
		String authorRegex = "username\">.+?_log_ct=.+?>(.+?)</a>";
		Pattern authorPa = Pattern.compile(authorRegex);
		Matcher authorMa = authorPa.matcher(content);
		boolean authorIsFind = authorMa.find();		
		
		//获得优酷视频（太极拳+广场舞）的发布时间
		String timeRegex = "发布.+?pub\">(.+?)</span>";
		Pattern timePa = Pattern.compile(timeRegex);
		Matcher timeMa = timePa.matcher(content);
		boolean timeIsFind = timeMa.find();	
		
		News news = null; 
		
		while(titleIsFind && urlIsFind && authorIsFind && timeIsFind){
			news = new News();
			String title = titleMa.group(1);
			//如果待插入的新闻标题和数据库已存在的最新新闻标题一致，跳出循环
			if(title.equals(latestNewsTitle))
				break;
			
			String _url = urlMa.group(1);
			String author = authorMa.group(1);
			String time = timeMa.group(1);
			
			news.setNtitle(title);
			news.setUrl(_url);
			news.setNcontent(_url);
			news.setNauthor(author);
			news.setNeditor(author);//编辑和作者是同一个人
			news.setNtime(TimeUtil.getFormatTime(time));
			news.setCategoryId(cateId);
			
			//将新闻保存到数据库
			newsDao.addNews(news);
			
			titleIsFind = titleMa.find();
			urlIsFind = urlMa.find();
			authorIsFind = authorMa.find();
			timeIsFind = timeMa.find();
			
		}
			
	}

	public void addRumour(String url, String categoryName) {
		ObjectId cateId = cateDao.getCategoryIdByName(categoryName);
		String latestNewsTitle = spiderDao.getLatestNewsTitleByCateId(cateId);
		String content = Spider.sendGet(url, "UTF-8");	
		
		//获取链接
		String urlRegex = "rumor-title\"><a href=\"(.+?)\"";
		Pattern urlPa = Pattern.compile(urlRegex);
		Matcher urlMa = urlPa.matcher(content);
		boolean isFind = urlMa.find();
		
		while(isFind){
			News news = new News();
			String _url = urlMa.group(1);
			_url = "http://www.liuyanbaike.com" + _url;
			news.setUrl(_url);
			
			//通过获取到的链接访问网页，再获取更多的信息
			String _content = Spider.sendGet(_url,"UTF-8");
			
			//新闻标题
			//Pattern titlePa = Pattern.compile("rumor-sum\">.+?>(.+?)<.+?>(.+?)<");
			Pattern titlePa = Pattern.compile("rumor-sum\">.+?>(.+?)<.+?rumor-title\">(.+?)<");
			Matcher titleMa = titlePa.matcher(_content);
			boolean isFindTitle = titleMa.find();
			
			//编辑
			Pattern editorPa = Pattern.compile("side-editor\">.+?_blank\">(.+?)<");
			Matcher editorMa = editorPa.matcher(_content);	
			boolean isFindEditor = editorMa.find();
			
			//发表时间
			Pattern timePa = Pattern.compile("最后更新：(.+?)<");
			Matcher timeMa = timePa.matcher(_content);		
			boolean isFindTime = timeMa.find();
			
			//概述
			Pattern descPa = Pattern.compile("rumor-desc\">.+?>(.+?)</span>.+?arrow-right\"></span>(.+?)</div>.+?真相.+?arrow-right\"></span>(.+?)</p>");
			Matcher descMa = descPa.matcher(_content);			
			boolean isFindDesc = descMa.find();	
			
			
			//正文
			Pattern contentPa = Pattern.compile("rumor-content\">(.+?)<div class=\"rumor-reply\">");
			Matcher contentMa = contentPa.matcher(_content);			
			boolean isFindContent = contentMa.find();
				
			if(isFindTitle){
				String title = titleMa.group(1).trim() + "  " + titleMa.group(2).trim();
				
				//如果待插入标题在数据库中已存在，则跳出循环
				if(latestNewsTitle.equals(title)){
					break;
				}
				
				news.setCategoryId(cateId);
				news.setNauthor("果壳网");//作者统一用果壳网
				news.setNtitle(title);
				
				if(isFindEditor){
					news.setNeditor(editorMa.group(1).trim());
				}else{
					news.setNeditor("果壳网");
				}
				
				String time = timeMa.group(1).trim();
				news.setNtime(TimeUtil.getFormatTime(time));
				//概述+正文
				String content2 = descMa.group(1) + "  " + descMa.group(2) + "   <p/>真相     " + descMa.group(3) + "<p/>" + contentMa.group(1);
				news.setNcontent(content2);		
				
				//将新闻保存到数据库
				newsDao.addNews(news);
			}			
			
			isFind = urlMa.find();
		}
				
	}
	
	public void addYCWB(String url, String categoryName) {
		ObjectId cateId = cateDao.getCategoryIdByName(categoryName);
		String latestNewsTitle = spiderDao.getLatestNewsTitleByCateId(cateId);
		String content = Spider.sendGet(url, "UTF-8");	

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
				
				//通过访问url的网页内容来抓取正文内容
				String _content = Spider.sendGet(newsUrl,"UTF-8");
				
				//新闻标题
				Pattern titlePa = Pattern.compile("<title>(.+?)</title>");
				Matcher titleMa = titlePa.matcher(_content);
				boolean isFindTitle = titleMa.find();
				
				//作者
				Pattern authorPa = Pattern.compile("<author>(.+?)</author>");
				Matcher authorMa = authorPa.matcher(_content);	
				boolean isFindAuthor = authorMa.find();
				
				//编辑
				Pattern editorPa = Pattern.compile("source_baidu\">来源:(.+?)</span>");
				Matcher editorMa = editorPa.matcher(_content);	
				boolean isFindEditor = editorMa.find();
				
				//发表时间
				Pattern timePa = Pattern.compile("<date>(.+?).0</date>");
				Matcher timeMa = timePa.matcher(_content);		
				boolean isFindTime = timeMa.find();
				
				//正文
				Pattern contentPa = Pattern.compile("main_article\">(.+?)</div>");
				Matcher contentMa = contentPa.matcher(_content);			
				boolean isFindContent = contentMa.find();
				
				if(isFindTitle){
					String title = titleMa.group(1).trim();
					
					//如果待插入的标题和数据库的已存在标题一致，则跳出循环
					if(latestNewsTitle.equals(title)){
						break;
					}
					
					news.setNtitle(title);
					news.setCategoryId(cateId);
				    news.setNtime(TimeUtil.getFormatTime(timeMa.group(1)));
					
					if(isFindAuthor){
						news.setNauthor(authorMa.group(1).trim());
					} else {
						news.setNauthor("羊城晚报");
					}
					
					news.setNeditor(editorMa.group(1).trim());
					news.setNcontent(contentMa.group(1).trim());
					
					//将新闻保存到数据库
					newsDao.addNews(news);
				}				
				
				
				isFind = urlMatcher.find();
			}
			
			divIsFind = divMa.find();
		}		
		
	}

	public void addNation(String url, String categoryName) {
		ObjectId cateId = cateDao.getCategoryIdByName(categoryName);
		String latestNewsTitle = spiderDao.getLatestNewsTitleByCateId(cateId);
		String content = Spider.sendGet(url, "UTF-8");	
	
		//获得国际新闻的url
		String urlRegex = "dd_bt\"><a href=\"(.+?)\">";
		Pattern urlPa = Pattern.compile(urlRegex);
		Matcher urlMa = urlPa.matcher(content);
		boolean isFind = urlMa.find();
		
		while(isFind){
			News news = new News();
			String _url = urlMa.group(1);
			if(!_url.startsWith("http")){
				_url = "http://www.chinanews.com" + _url;
				news.setUrl(_url);
				
				//通过url获取国际新闻内容
				String newsUrl = news.getUrl();
				String _content = Spider.sendGet(newsUrl,"GBK");
				
				//新闻标题
				Pattern titlePa = Pattern.compile("ad_title\">.+?<h1.+?>(.+?)</h1>");
				Matcher titleMa = titlePa.matcher(_content);
				boolean isFindTitle = titleMa.find();
				
				//作者
				Pattern authorPa = Pattern.compile("source_baidu\">.+?_blank\">(.+?)</a>");
				Matcher authorMa = authorPa.matcher(_content);	
				boolean isFindAuthor = authorMa.find();
				
				//编辑
				Pattern editorPa = Pattern.compile("【编辑:(.+?)】");
				Matcher editorMa = editorPa.matcher(_content);	
				boolean isFindEditor = editorMa.find();
				
				//发表时间
				Pattern timePa = Pattern.compile("pubtime_baidu\">(.+?)</span>");
				Matcher timeMa = timePa.matcher(_content);		
				boolean isFindTime = timeMa.find();
				
				//正文
				Pattern contentPa = Pattern.compile("正文start-->.+?>(.+?)<!--正文");
				Matcher contentMa = contentPa.matcher(_content);			
				boolean isFindContent = contentMa.find();
			
				if(isFindTitle){
					String title = titleMa.group(1).trim();
					if(latestNewsTitle.equals(title)){
						break;
					}
					news.setNtitle(title);
					news.setCategoryId(cateId);
					news.setNauthor(authorMa.group(1).trim());
					if(isFindEditor){
						news.setNeditor(editorMa.group(1).trim());
					}else{
						news.setNeditor("国际新闻");
					}
					
				    news.setNtime(TimeUtil.getFormatTime(timeMa.group(1)));
					news.setNcontent(contentMa.group(1).trim());
					
					//保存到数据库,待测试功能
					newsDao.addNews(news);
					
				}	
			}
			isFind = urlMa.find();
		}
		
	}
	
	
	public void addHistory(String url, String categoryName) {
		// TODO Auto-generated method stub
		
	}
	
	/*public void addZhihu(String url, String categoryName) {
		ObjectId cateId = cateDao.getCategoryIdByName(categoryName);
		String latestNewsTitle = spiderDao.getLatestNewsTitleByCateId(cateId);
		String content = Spider.sendGet(url, "UTF-8");	
		
		Pattern urlPattern = Pattern
				.compile("question_link.+?href=\"(.+?)\".+?</h2>");
		Matcher urlMatcher = urlPattern.matcher(content);
		
		boolean isFind = urlMatcher.find();

		while (isFind) {
			Pattern pattern;
			Matcher matcher;		
			
			News news = new News();
			
			//内容由问题描述+回答组成
			String finalContent = "";
			String _Url = urlMatcher.group(1);
			news.setUrl(_Url);
			
			//通过url获取知乎推荐内容
			String newsUrl = news.getUrl();
			
			pattern = Pattern.compile("question/(.*?)/");
			matcher = pattern.matcher(newsUrl);
			if(matcher.find()){
				newsUrl = "http://www.zhihu.com/question/" + matcher.group(1);
			}	
			
			String _content = Spider.sendGet(newsUrl,"UTF-8");

			//匹配标题
			pattern = Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>");
			matcher = pattern.matcher(_content);
			if(matcher.find()){
				String title = matcher.group(1);
				//如果待插入的新闻标题已存在，则跳出循环
				if(latestNewsTitle.equals(title)){
					break;
				}
				
				news.setNtitle(title);
			}
			
			//匹配描述
			pattern = Pattern.compile("zh-question-detail.+?<div.+?>(.+?)</div>");
			matcher = pattern.matcher(_content);
			if(matcher.find()){
				String description = matcher.group(1);
				finalContent = finalContent + description + "<br/>";
			}
			
			//匹配作者和编辑，同一个人
			pattern = Pattern.compile("href=\"/people.+?href=\"/people.+?>(.+?)</a>，");
			matcher = pattern.matcher(_content);
			if(matcher.find()){
				String author = matcher.group(1);
				news.setNauthor(author);
				news.setNeditor(author);
			}
			
			//匹配答案
			pattern = Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
			matcher = pattern.matcher(_content);
			
			boolean _isFind = matcher.find();
			if(_isFind){
				String answer = matcher.group(1);
				finalContent = finalContent + answer + "<br/>";
			}	
			
			news.setNcontent(finalContent);
			
			news.setCategoryId(cateId);
			
			//保存到数据库,待测试功能
			newsDao.addNews(news);
			
			isFind = urlMatcher.find();
		}	
				
	}*/
}
