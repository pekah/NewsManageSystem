package com.zyl.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;

import com.zyl.bean.News;

public class PageServiceImpl {
	/**
	 * ================================== 分页获取操作
	 */
	public Page<News> getKeywordsSetMongoBeanByPage(
			Integer pageNumber, Integer pageSize) {
		int skip = pageSize * (pageNumber - 1);

		Query query = new Query();
		// 搜索结果总数
		Query countQuery = new Query();

		String orderBy = "_id";

		query.skip(skip);
		query.limit(pageSize);

		List<KeywordsSetMongoBean> list = mongoTemplate.find(query,
				KeywordsSetMongoBean.class, "keywords_set");
		List<KeywordsSetMongoBean> wordList = new ArrayList<KeywordsSetMongoBean>();
		if (list != null && list.size() > 0) {
			for (KeywordsSetMongoBean word : list) {
				KeywordsSetMongoBean bean = new KeywordsSetMongoBean();
				BeanUtils.copyProperties(word, bean);
				wordList.add(bean);
			}
		}
		long count = mongoTemplate.count(countQuery, "keywords_set");

		Page<KeywordsSetMongoBean> keywordsPage = new Page<KeywordsSetMongoBean>();
		keywordsPage.setPageNumber(pageNumber);
		keywordsPage.setPageSize(pageSize);
		keywordsPage.setTotalPage(count % pageSize == 0 ? (count / pageSize)
				: (count / pageSize + 1));
		keywordsPage.setTotalRow(count);
		keywordsPage.setPageList(wordList);

		return keywordsPage;
	}
}
