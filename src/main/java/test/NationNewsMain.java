package test;

import java.util.ArrayList;

import com.zyl.bean.News;
import com.zyl.util.Spider;

public class NationNewsMain {
	public static void main(String[] args) throws Exception {
		String url = "http://www.chinanews.com/world.shtml";
		String content = Spider.sendGet(url,"GBK");
		
		ArrayList<News> newsList = Spider.GetNationalNewsLink(content);
		
		ArrayList<News> newsList2 = Spider.GetNationalNewsContent(newsList);
		
		for(News news : newsList2){
			System.out.println(news.getUrl());
			System.out.println(news.getNauthor());
			System.out.println(news.getNeditor());
			System.out.println(news.getNcontent());
			System.out.println(news.getNtitle());
			System.out.println(news.getNtime());
		}		
		
		
	}
}
