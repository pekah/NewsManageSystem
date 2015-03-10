package test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class Zhihu {
	static Logger logger = Logger.getLogger(Zhihu.class);
	public String question;//问题
	public String questionDescription;//问题描述
	public String zhihuUrl;//网页链接
	public ArrayList<String> answers;//存储所有回答的数组
	
	public Zhihu(String url){
		question = "";
		questionDescription = "";
		zhihuUrl = "";
		answers = new ArrayList<String>();
		
		if(getRealUrl(url)){
			System.out.println("正在抓取" + zhihuUrl);
			//根据url获取该回答的细节
			String content = Spider.sendGet(zhihuUrl,"UTF-8");
			Pattern pattern;
			Matcher matcher;
			//匹配标题
			pattern = Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>");
			matcher = pattern.matcher(content);
			if(matcher.find()){
				question = matcher.group(1);
			}
			//匹配描述
			pattern = Pattern.compile("zh-question-detail.+?<div.+?>(.+?)</div>");
			matcher = pattern.matcher(content);
			if(matcher.find()){
				questionDescription = matcher.group(1);
			}
			//匹配答案
			pattern = Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
			matcher = pattern.matcher(content);
			boolean isFind = matcher.find();
			while(isFind){
				answers.add(matcher.group(1));
				isFind = matcher.find();
			}
			
		}
	}
	
	
	
	boolean getRealUrl(String url){
        // 将http://www.zhihu.com/question/22355264/answer/21102139  
        // 转化成http://www.zhihu.com/question/22355264  
        // 否则不变  
		Pattern pattern = Pattern.compile("question/(.*?)/");
		Matcher matcher = pattern.matcher(url);
		if(matcher.find()){
			zhihuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
		}else{
			return false;
		}
		return true;
	}
	
	
	
	public String toString(){
		return "问题:" + question + "\n问题描述" + questionDescription + "\n链接:" + zhihuUrl + "\n回答:" + answers + "\n";
	}
}
