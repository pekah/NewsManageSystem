package test;

import java.util.ArrayList;

import com.zyl.bean.News;

public class RumourMain {
	public static void main(String[] args) {
		String url = "http://www.liuyanbaike.com/category/";
		
		String content = Spider.sendGet(url, "UTF-8");
		
		ArrayList<News> newsList = Spider.GetRumourLink(content);
		
		Spider.GetRumourContent(newsList);
		
	}
}