通过Fiddler抓取Java HttpClient的HTTP包

```javascript
public static void main(String[] args) throws Exception {
        
        HttpPost httpPost = new HttpPost("xxx");
        //requestConfig有setProxy(proxy)就行
        HttpHost proxy = new HttpHost("127.0.0.1",8888);
        RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
        CloseableHttpClient httpclient= HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        
        HttpResponse httpResponse = httpclient.execute(httpPost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();

        System.out.println(httpResponse.getStatusLine());

        String result = null;

        if (statusCode == HttpStatus.SC_OK) {
            HttpEntity resEntity = httpResponse.getEntity();
            result = EntityUtils.toString(resEntity);
        }

        httpclient.getConnectionManager().shutdown();
        
        System.out.println("result:"+result);
    }
```

