package test;

import java.util.ArrayList;

import com.zyl.bean.News;

public class YoukuMain {
	public static void main(String[] args) {
		String taijiquanUrl = "http://www.soku.com/search_video/q_%E5%A4%AA%E6%9E%81%E6%8B%B3_orderby_2";
		String squareDanceUrl = "http://www.soku.com/search_video/q_%E5%B9%BF%E5%9C%BA%E8%88%9E_orderby_2";
		String content = Spider.sendGet(squareDanceUrl, "UTF-8");	
		ArrayList<News> newsList = Spider.GetYoukuVideo(content);
		
		for(News news : newsList){
			System.out.println(news.getUrl());
			System.out.println(news.getNtitle());
		}
		
		
		 
	}
}
