package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientTest {
	static Logger logger = Logger.getLogger(HttpClientTest.class);

	public static void main(String[] args) {
		String result = "";
		BufferedReader in = null;

		// 核心应用类
		HttpClient httpClient = new DefaultHttpClient();

		// HTTP请求
		HttpUriRequest request = new HttpGet(
				"http://www.zhihu.com/explore/recommendations");

		// 打印请求信息
		System.out.println(request.getRequestLine());
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

			System.out.println(result);

			// 打印响应信息
			System.out.println(response.getStatusLine());
		} catch (ClientProtocolException e) {
			// 协议错误
			e.printStackTrace();
			logger.error("协议错误");
		} catch (IOException e) {
			// 网络异常
			e.printStackTrace();
			logger.error("网络异常");
		}

	}
}
