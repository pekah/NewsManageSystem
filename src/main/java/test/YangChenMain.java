package test;

import java.util.ArrayList;

import com.zyl.bean.News;
import com.zyl.util.Spider;

public class YangChenMain {
	public static void main(String[] args) throws Exception {
		String url = "http://news.ycwb.com/n_bd_gz.htm";
		String content = Spider.sendGet(url, "UTF-8");		
		
		ArrayList<News> newsList = Spider.GetYCWBNewsLink(content);
		
//		for(News news : newsList){
//			System.out.println(news.getUrl());
//		}
		ArrayList<News> newsList2 = Spider.GetYCWBNewsContent(newsList);
		
		for(News news : newsList){
			System.out.println(news.getUrl());
			System.out.println(news.getNauthor());
			System.out.println(news.getNeditor());
			System.out.println(news.getNcontent());
			System.out.println(news.getNtitle());
			System.out.println(news.getNtime());
		}		
		
		
	}
}
