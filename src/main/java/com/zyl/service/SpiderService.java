package com.zyl.service;

public interface SpiderService {
	public void addYoukuVideo(String url,String categoryName);
	public void addRumour(String url, String categoryName);
	public void addYCWB(String url, String categoryName);
	public void addNation(String url, String categoryName);
	public void addHistory(String url, String categoryName);
}
