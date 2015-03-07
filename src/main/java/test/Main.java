package test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String url = "http://www.zhihu.com/explore/recommendations";
		String content = Spider.sendGet(url);
		
		ArrayList<Zhihu> myZhihu = Spider.GetRecommendations(content);

		System.out.println(myZhihu);
	}
}
