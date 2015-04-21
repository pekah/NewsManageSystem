package com.zyl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.service.*;
@Controller
@RequestMapping("spider")
public class SpiderController {
	//抓取的页数
	private static final int FATCHPAGE = 2;
	@Autowired
	private SpiderService spiderSerive;
	
	//抓取太极拳视频
	@RequestMapping("news-taijiquan")
	public ModelAndView addTaijiquan(){
		String taijiquanUrl = "http://www.soku.com/search_video/q_%E5%A4%AA%E6%9E%81%E6%8B%B3_orderby_2";
		
		for(int i = 1; i <= FATCHPAGE; i++){
			System.out.println("第" + i + "页");
			taijiquanUrl = taijiquanUrl + "_page_" + i;
			spiderSerive.addYoukuVideo(taijiquanUrl,"太极拳");
		}
		
		return null;
	}
	
	//抓取广场舞视频
	@RequestMapping("news-squareDance")
	public ModelAndView addSquareDance(){
		String squareDanceUrl = "http://www.soku.com/search_video/q_%E5%B9%BF%E5%9C%BA%E8%88%9E_orderby_2";
		
		for(int i = 1; i <= FATCHPAGE; i++){
			System.out.println("第" + i + "页");
			squareDanceUrl = squareDanceUrl + "_page_" + i;
			spiderSerive.addYoukuVideo(squareDanceUrl,"广场舞");
		}
		
		return null;
	}	
	
	//抓取流言百科的新闻
	@RequestMapping("news-rumour")
	public ModelAndView addRumour(){
		String url = "http://www.liuyanbaike.com/category/";
		
		for(int i = 1; i <= FATCHPAGE; i++){
			System.out.println("第" + i + "页");
			url = url + "?page=" + i;
			
			spiderSerive.addRumour(url,"流言百科");
		}
		
		return null;
	}		
	
	//抓取羊城晚报的新闻
	@RequestMapping("news-ycwb")
	public ModelAndView addYCWB(){
		String url = "http://news.ycwb.com/n_bd_gz";
		
		for(int i = 1; i <= FATCHPAGE; i++){
			System.out.println("第" + i + "页");
			
			if(i == 1){
				url = url + ".htm";
			}else{
				url = url + "_" + i + ".htm";
			}
			
			spiderSerive.addYCWB(url, "羊城晚报");
		}
		
		return null;
	}
	
	//抓取国际新闻
	@RequestMapping("news-nation")
	public ModelAndView addNation(){
		String url = "http://www.chinanews.com/world.shtml";
			
		spiderSerive.addNation(url, "国际热点");
		
		return null;
	}	
	
	//抓取知乎推荐
	@RequestMapping("news-zhihuRecommend")
	public ModelAndView addZhihuRecommend(){
		String url = "http://www.chinanews.com/world.shtml";
			
		spiderSerive.addNation(url, "国际热点");
		
		return null;
	}	
	
	
	//一键抓取，多线程
}
