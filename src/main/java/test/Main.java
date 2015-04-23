package test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zyl.bean.News;
import test.Spider;

public class Main {
	

	static ArrayList<String> RegexString(String targetStr, String patternStr){
		
		ArrayList<String> results = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		
		boolean isFind = matcher.find();
		
		while(isFind){
			results.add(matcher.group(1));
		}
		return results;
	}
	
	public static void main(String[] args) {
		String url = "http://view.news.qq.com/l/history_new/history_article/list201206115621.htm";
		String content = Spider.sendGet(url,"GBK");
		
		ArrayList<News> news = Spider.GetHistoryNews(content);

		System.out.println(news);
		
	}
}
