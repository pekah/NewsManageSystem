package test;

import java.util.ArrayList;

import com.zyl.bean.News;

public class YangChenMain {
	public static void main(String[] args) throws Exception {
		String url = "http://news.ycwb.com/n_bd_gz.htm";
		String content = Spider.sendGet(url);		
		
		ArrayList<News> newsList = Spider.GetYCWBNewsLink(content);
		
//		for(News news : newsList){
//			System.out.println(news.getUrl());
//		}
		Spider.GetNewsContent(newsList, "");
		
		
		
	}
}
