package com.zyl.service;

public interface SpiderService {
	public int addYoukuVideo(String url,String categoryName);
	public int addRumour(String url, String categoryName);
	public int addYCWB(String url, String categoryName);
	public int addNation(String url, String categoryName);
	public int addHistory(String url, String categoryName);
}
