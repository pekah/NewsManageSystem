package com.zyl.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.zyl.bean.Category;

public interface CategoryDao {
	public void addCategory(String name);
	public void removeCategory(String name);
	public List<Category> getCategorys();
	public ObjectId getCategoryIdByName(String name);
}
