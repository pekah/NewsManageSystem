package com.zyl.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetDate {
	public static String getDate() {
		SimpleDateFormat ft = null;
		Date date = null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(new java.util.Date());
		date = cl.getTime();
		// 格式可以自己根据需要修改
		ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateTime = ft.format(date);
		return dateTime;
	}

	public static void main(String[] args) {
		System.out.println(GetDate.getDate());
	}
}
