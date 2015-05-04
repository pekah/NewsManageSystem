package com.zyl.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zyl.service.SpiderService;

public class QuartzSpider {
	private static final Logger LOG = LoggerFactory.getLogger(QuartzSpider.class);
	
	@Autowired
	private SpiderService spiderSerive;
	
	private static String TAIJIQUAN = "http://www.soku.com/search_video/q_%E5%A4%AA%E6%9E%81%E6%8B%B3_orderby_2";
	private static String SQUAREDANCE = "http://www.soku.com/search_video/q_%E5%B9%BF%E5%9C%BA%E8%88%9E_orderby_2";
	private static String RUMOUR = "http://www.liuyanbaike.com/category/";
	private static String YCWB = "http://news.ycwb.com/n_bd_gz.htm";
	private static String NATION = "http://www.chinanews.com/world.shtml";
	private static String HISTORY = "http://view.news.qq.com/l/history_new/history_article/list201206115621.htm";
	
	public void work() {
//		spiderSerive.addYoukuVideo(TAIJIQUAN, "太极拳");
//		spiderSerive.addYoukuVideo(SQUAREDANCE, "广场舞");
//		spiderSerive.addHistory(HISTORY, "历史新闻");
//		spiderSerive.addNation(NATION, "国际热点");
//		spiderSerive.addRumour(RUMOUR, "流言百科");
//		spiderSerive.addYCWB(YCWB, "羊城晚报");
		//System.out.println("Quartz的任务调度！！！");
	}
}
