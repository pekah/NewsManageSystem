package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.pattern.PatternParser;

public class TestMain {
	public static void main(String[] args) {
		String patternStr = "/id_(.+?).html";
		String targetStr = "http://v.youku.com/v_show/id_XOTM2MzM1MDEy.html?from=s1.8-1-1.2";
		
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		
		boolean isFind = matcher.find();
		
		if(isFind){
			String result = matcher.group(1);
			
			result = "<embed src=\"http://player.youku.com/player.php/sid/" + result + "/v.swf\" allowFullScreen=\"true\" quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>";
			
			System.out.println(result);
		}
		
	}
}
