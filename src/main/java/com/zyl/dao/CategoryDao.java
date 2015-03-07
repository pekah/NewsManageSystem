package com.zyl.dao;

import java.util.List;

import org.bson.types.ObjectId;

public interface CategoryDao {
	public void addCategory(String name);
	public void removeCategory(String name);
	public List<String> getCategorys();
	public ObjectId getCategoryIdByName(String name);
}
