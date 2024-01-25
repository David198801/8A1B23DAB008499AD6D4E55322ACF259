zy

```javascript
package com.yss.acs.esd.zy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Test {

	public static void main(String[] args) {

		String code = "160029";

		String reqBody = readUtf8File("C:\\Users\\LiuZhongbin\\Desktop\\核心\\test\\" + code + "req.xml");
		String respBody = "";

		HttpClient client = new DefaultHttpClient();

		String url = "http://10.238.144.228:8092/services/" + code;

		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(reqBody, ContentType.create("application/xml", Charset.forName("UTF-8")));
		post.setEntity(entity);
		int timeout = 60 * 1000;
		client.getParams().setParameter("http.socket.timeout", timeout);
		client.getParams().setParameter("http.connection.timeout", timeout);
		HttpResponse resp = null;
		try {
			System.out.println("开始发送请求，url:" + url);
			System.out.println("请求报文:\n" + reqBody);
			resp = client.execute(post);
			HttpEntity respEntity = resp.getEntity();
			respBody = EntityUtils.toString(respEntity, Consts.UTF_8.name());
			// if(StringUtil.isEmpty(respJson)){
			// throw new SOFARuntimeException("HTTP响应为空");
			// }
			// log.info("响应状态码:" + resp.getStatusLine().getStatusCode());
			// if(resp.getStatusLine()!=null && 404 == resp.getStatusLine().getStatusCode()){
			// throw new SOFARuntimeException("请求返回404，请检查url并确认对方系统开启");
			// }
			System.out.println("响应报文:\n" + respBody);
		} catch (Exception e) {
			// throw new SOFARuntimeException("HTTP请求失败",e);
			throw new RuntimeException("HTTP请求失败", e);
		} finally {
			if (null != client && null != client.getConnectionManager()) {
				client.getConnectionManager().shutdown();
			}
		}
	}

	private static String readUtf8File(String fileName) {

		// 以utf-8编码读取文件：
		FileInputStream fis = null;
		InputStreamReader isr = null;
		String fileContent = "";

		try {
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				fileContent += line;
				fileContent += "\r\n"; // 补上换行符
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent;
	}
}

```

