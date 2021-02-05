package com.jt;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.omg.CORBA.StringHolder;

/**
 * @author wangning
 * @create 2021-02-04 15:42
 */
public class TestHttpClient {
	/**
	 * 模拟发起get请求
	 * 1.创建httpclient
	 * 2.定义url
	 * 3.定义请求方式
	 * 4.发起request请求，获取response
	 * 5.判断状态码是否正确
	 * 	200-请求正确
	 * 	400-请求参数异常
	 * 	406-返回结果与页面要求不匹配
	 * 	404-找不到请求的路径
	 * 	500-服务器异常
	 * 6.获取响应结果
	 *
	 */
	@Test
	public void get() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = "http://m.news.cctv.com/2021/02/04/ARTIR6VQ3OIZmWK8yc2hmk8p210204.shtml";
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(get);
		if(response.getStatusLine().getStatusCode() == 200){
			System.out.println("恭喜你，学会了跨域系统的访问");
			String result = EntityUtils.toString(response.getEntity());
			System.out.println("跨域请求的结果："+result);
		}


	}
}
