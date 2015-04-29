package com.zyl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zyl.bean.News;
import com.zyl.service.SpiderService;
import com.zyl.service.UsersService;
@Controller
@RequestMapping("spider")
public class SpiderController {
	//抓取的页数
	private static final int FATCHPAGE = 2;
	@Autowired
	private SpiderService spiderSerive;
	
	@Autowired
	@Qualifier("usersService")
	private UsersService userSerivce;
	
	//抓取太极拳视频
	@RequestMapping("news-taijiquan")
	@ResponseBody
	public ModelAndView addTaijiquan(){
		ModelAndView mv = new ModelAndView();
		String taijiquanUrl = "http://www.soku.com/search_video/q_%E5%A4%AA%E6%9E%81%E6%8B%B3_orderby_2";
		
		int total = 0;
		
		for(int i = 1; i <= FATCHPAGE; i++){
			taijiquanUrl = taijiquanUrl + "_page_" + i;
			total += spiderSerive.addYoukuVideo(taijiquanUrl,"太极拳");
		}
		
		List<News> newsList = null;
		if(total > 0){
			newsList = userSerivce.getLatestNewsByCateName("太极拳", total);
		}
		
		mv.addObject("cname","太极拳");
		mv.addObject("total",total);
		mv.addObject("newsList", newsList);
		
		mv.setViewName("admin/spider-index.jsp");
		
		return mv;
	}
	
	//抓取广场舞视频
	@RequestMapping("news-squareDance")
	public ModelAndView addSquareDance(){
		ModelAndView mv = new ModelAndView();
		String squareDanceUrl = "http://www.soku.com/search_video/q_%E5%B9%BF%E5%9C%BA%E8%88%9E_orderby_2";
		
		int total = 0;
		
		for(int i = 1; i <= FATCHPAGE; i++){
			squareDanceUrl = squareDanceUrl + "_page_" + i;
			total += spiderSerive.addYoukuVideo(squareDanceUrl,"广场舞");
		}
		
		List<News> newsList = null;
		if(total > 0){
			newsList = userSerivce.getLatestNewsByCateName("广场舞", total);
		}
		
		mv.addObject("cname","广场舞");
		mv.addObject("total",total);
		mv.addObject("newsList", newsList);
		
		mv.setViewName("admin/spider-index.jsp");
		
		return mv;	
	}	
	
	//抓取流言百科的新闻
	@RequestMapping("news-rumour")
	public ModelAndView addRumour(){
		ModelAndView mv = new ModelAndView();
		String url = "http://www.liuyanbaike.com/category/";
		
		int total = 0;
		
		for(int i = 1; i <= FATCHPAGE; i++){
			url = url + "?page=" + i;
			total += spiderSerive.addRumour(url,"流言百科");
		}
		
		List<News> newsList = null;
		if(total > 0){
			newsList = userSerivce.getLatestNewsByCateName("流言百科", total);
		}
		
		mv.addObject("cname","流言百科");
		mv.addObject("total",total);
		mv.addObject("newsList", newsList);
		
		mv.setViewName("admin/spider-index.jsp");
		
		return mv;	
	}		
	
	//抓取羊城晚报的新闻
	@RequestMapping("news-ycwb")
	public ModelAndView addYCWB(){
		ModelAndView mv = new ModelAndView();
		String url = "http://news.ycwb.com/n_bd_gz";
		
		int total = 0;
		
		for(int i = 1; i <= FATCHPAGE; i++){
			System.out.println("第" + i + "页");
			
			if(i == 1){
				url = url + ".htm";
			}else{
				url = url + "_" + i + ".htm";
			}
			
			total += spiderSerive.addYCWB(url, "羊城晚报");
		}
		
		List<News> newsList = null;
		if(total > 0){
			newsList = userSerivce.getLatestNewsByCateName("羊城晚报", total);
		}
		
		mv.addObject("cname","羊城晚报");
		mv.addObject("total",total);
		mv.addObject("newsList", newsList);
		
		mv.setViewName("admin/spider-index.jsp");
		
		return mv;	
		
	}
	
	//抓取国际新闻
	@RequestMapping("news-nation")
	public ModelAndView addNation(){
		ModelAndView mv = new ModelAndView();
		String url = "http://www.chinanews.com/world.shtml";
		
		int total = 0;
		
		total += spiderSerive.addNation(url, "国际热点");
		
		List<News> newsList = null;
		if(total > 0){
			newsList = userSerivce.getLatestNewsByCateName("国际热点", total);
		}
		
		mv.addObject("cname","国际热点");
		mv.addObject("total",total);
		mv.addObject("newsList", newsList);
		
		mv.setViewName("admin/spider-index.jsp");
		
		return mv;	
	}	
	
	//抓取历史新闻
	@RequestMapping("news-history")
	public ModelAndView addZhihuRecommend(){
		ModelAndView mv = new ModelAndView();
		String url = "http://view.news.qq.com/l/history_new/history_article/list201206115621.htm";
		
		int total = 0;
		
		total += spiderSerive.addHistory(url, "历史新闻");
		
		List<News> newsList = null;
		if(total > 0){
			newsList = userSerivce.getLatestNewsByCateName("历史新闻", total);
		}
		
		mv.addObject("cname","历史新闻");
		mv.addObject("total",total);
		mv.addObject("newsList", newsList);
		
		mv.setViewName("admin/spider-index.jsp");
		
		return mv;	
	}	
	
	
	//一键抓取，多线程
}
