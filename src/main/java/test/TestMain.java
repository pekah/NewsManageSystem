package test;

import java.util.regex.Pattern;

import org.apache.log4j.pattern.PatternParser;

public class TestMain {
	public static void main(String[] args) {
		String str = "1993-02-01 11:11:11";
		if(str.endsWith("前")){
			
			String regex = "(.+?)小";
			
			str = str.replace("小时前", "");
			
			System.out.println(str);
		}
	}
}
