

```javascript
CloseableHttpClient client = HttpClients.createDefault();
HttpPost post = new HttpPost(url);
StringEntity entity = new StringEntity(reqJson, ContentType.APPLICATION_JSON);
post.setEntity(entity);
int timeout = 10000;
HttpHost proxy = new HttpHost("127.0.0.1",8888);//lzb测试
RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout)
		.setProxy(proxy)//lzb测试
		.setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
post.setConfig(config);
String responseContent = null;
CloseableHttpResponse resp = null;
try {
	resp = client.execute(post);
	if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		HttpEntity respEntity = resp.getEntity();
		respJson = EntityUtils.toString(respEntity, Consts.UTF_8.name());
	}
}catch(Exception e){
	throw new SOFARuntimeException("HTTP请求失败",e);
}finally {
	if(null!=resp){
		try {
			resp.close();
		} catch (IOException e) {
			throw new SOFARuntimeException("HTTP请求关闭资源失败",e);
		}
	}
	if(null!=client){
		try {
			client.close();
		} catch (IOException e) {
			throw new SOFARuntimeException("HTTP请求关闭资源失败",e);
		}
	}
}
```

