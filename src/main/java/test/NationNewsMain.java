package test;

import java.util.ArrayList;

import com.zyl.bean.News;

public class NationNewsMain {
	public static void main(String[] args) throws Exception {
		String url = "http://www.chinanews.com/world.shtml";
		String content = Spider.sendGet(url,"GBK");
		
		ArrayList<News> newsList = Spider.GetNationalNewsLink(content);
		
		Spider.GetNationalNewsContent(newsList);
		
	}
}
