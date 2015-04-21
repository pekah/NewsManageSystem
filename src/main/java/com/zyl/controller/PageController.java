package com.zyl.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyl.bean.News;

public class PageController {

	
	@RequestMapping(value = "/keywordSet-list-mongo")
	public String keywordSetListByMongo(Model model, Integer pageNumber) {
		if (pageNumber == null) {
			pageNumber = 1;
		}

		Page<News> keywordSetPage = antiKeywordextractMongoService
				.getKeywordsSetMongoBeanByPage(pageNumber, Constants.pageSize);

		model.addAttribute("keywordSetPage", keywordSetPage);
		model.addAttribute("pageNumber", pageNumber);
		return "forward:/keywords/keywordset-list-mongo.jsp";
	}

}
