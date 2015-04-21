package com.zyl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class TimeUtil {
	
	public static Date getCurrentTime() throws ParseException{
		 DateTime dt = new DateTime();
		 return dt.toDate();
	}
	public static Date getFormatTime(String time){
		java.util.Date date = null;
		
		if(time.endsWith("小时前")){
			//获得小时数
			String hour = time.replace("小时前", "");
			DateTime dt = new DateTime();
			//当前小时数
			int currentHour = dt.getHourOfDay();
			//x小时前
			int agoHour = Integer.parseInt(hour);
			//当前小时-x小时得到的最终小时
			int finalHour;
			//如果x小时前中的x大于当前时间，则当前时间减去一天，加上24小时
			if(agoHour > currentHour){
				dt = dt.dayOfYear().setCopy(dt.getDayOfYear() - 1);
				finalHour = currentHour + 24 - agoHour;
			}else{
				finalHour = currentHour - agoHour;
			}
			dt = dt.hourOfDay().setCopy(finalHour);
			date = dt.toDate();
			
		}else if(time.endsWith("分钟前")){
			//获得分钟数
			String minute = time.replace("分钟前", "");
			DateTime dt = new DateTime();
			dt = dt.minuteOfDay().setCopy(dt.getMinuteOfDay() - Integer.parseInt(minute));
			date = dt.toDate();
		}else if(time.startsWith("今天")){
			//获得小时数
			String hour = time.replace("今天", "");
			String[] hourminute = hour.split(":");
			//设置当天的小时数
			DateTime dt = new DateTime();
			dt = dt.hourOfDay().setCopy(hourminute[0]);
			dt = dt.minuteOfHour().setCopy(hourminute[1]);
			date = dt.toDate();
		}else if(time.startsWith("昨天")){
			//获得小时数
			String hour = time.replace("昨天", "");
			String[] hourminute = hour.split(":");
			//设置小时数并减去一天
			DateTime dt = new DateTime();
			dt = dt.hourOfDay().setCopy(hourminute[0]);
			dt = dt.minuteOfHour().setCopy(hourminute[1]);
			dt = dt.dayOfYear().setCopy(dt.getDayOfYear() - 1);
			date = dt.toDate();
		}else if(time.endsWith("天前")){
			//获得天数
			String day = time.replace("天前", "");
			DateTime dt = new DateTime();
			dt = dt.dayOfYear().setCopy(dt.getDayOfYear() - Integer.parseInt(day));
			date = dt.toDate();
		}else  {
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    try {
				date = format.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return date;
	}
}
