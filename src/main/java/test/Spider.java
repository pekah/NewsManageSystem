package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class Spider {
	static Logger logger = Logger.getLogger(HttpClientTest.class);
	
	static String sendGet(String url) {

		String result = "";

		BufferedReader in = null;

		// 核心应用类
		HttpClient httpClient = new DefaultHttpClient();

		// HTTP请求
		HttpUriRequest request = new HttpGet(url);

		try {
			// 发送请求，返回响应
			HttpResponse response = httpClient.execute(request);
			// 从response中取出HttpEntity对象
			HttpEntity entity = response.getEntity();
			// 取出服务器返回的数据流
			InputStream stream = entity.getContent();

			in = new BufferedReader(new InputStreamReader(stream, "utf-8"));

			String line;

			while ((line = in.readLine()) != null) {
				result += line;
			}

		} catch (ClientProtocolException e) {
			// 协议错误
			e.printStackTrace();
			logger.error("协议错误");
		} catch (IOException e) {
			// 网络异常
			e.printStackTrace();
			logger.error("网络异常");
		}

		return result;

	}

	static ArrayList<Zhihu> GetRecommendations(String content) {
		ArrayList<Zhihu> results = new ArrayList<Zhihu>();

		// 用来匹配url，也就是问题的链接
		// Pattern urlPattern =
		// Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
		Pattern urlPattern = Pattern
				.compile("question_link.+?href=\"(.+?)\".+?</h2>");
		Matcher urlMatcher = urlPattern.matcher(content);

		boolean isFind = urlMatcher.find();

		while (isFind) {
			Zhihu zhihuTemp = new Zhihu(urlMatcher.group(1));
			results.add(zhihuTemp);
			isFind = urlMatcher.find();
		}

		return results;
	}

}
