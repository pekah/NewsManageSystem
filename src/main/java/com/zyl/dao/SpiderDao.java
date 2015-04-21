package com.zyl.dao;

import org.bson.types.ObjectId;

import com.zyl.bean.News;

public interface SpiderDao {
	public String getLatestNewsTitleByCateId(ObjectId categoryId);
}
