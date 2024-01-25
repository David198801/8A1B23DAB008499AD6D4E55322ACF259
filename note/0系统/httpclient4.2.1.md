```javascript
导入jar包


import java.io.InputStream;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {

//4.2.1版本HttpClient
//@Test
public void doGet(){
        InputStream instream=null;
    try {
        
        HttpClient httpclient = new DefaultHttpClient();
        String url = "http://localhost:8088/GW/back/merchant_dopost.action?name=" + URLEncoder.encode("中文参数" , "UTF-8") + "&otherName=" + URLEncoder.encode("邓紫琪" , "UTF-8") ;
        HttpGet getmethod = new HttpGet(url);
        HttpResponse response = httpclient.execute(getmethod);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            System.out.println(EntityUtils.toString(entity,"UTF-8"));
         instream = entity.getContent();
        int len;
        byte[] tmp = new byte[2048];
        while ((len = instream.read(tmp)) != -1) {
            System.out.println(tmp.toString());
        }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }finally{
        try {
            instream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
  //@Test
  public void testget2(){
    try {
         HttpClient httpclient = new DefaultHttpClient();
        
         List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            qparams.add(new BasicNameValuePair("orderno", "订单号"));
            qparams.add(new BasicNameValuePair("ordertype", "2"));
            qparams.add(new BasicNameValuePair("amout", "233"));
            qparams.add(new BasicNameValuePair("memo", "备注"));
            URI uri = URIUtils.createURI("http", "localhost:8088/GW", -1, "/back/merchant_dopost.action",URLEncodedUtils.format(qparams, "UTF-8"), null);
         
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response=httpclient.execute(httpget);
            
            
            System.out.println(EntityUtils.toString(response.getEntity(),"UTF-8"));//返回数据
            System.out.println(httpget.getURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
          
      }

/** 发送POST   支持http、https
     * @param url        post地址
     * @param paramMap   要传递的参数封装成Map
     * @param num        如果post失败连续post次数
     * @param return     返回 fail\0\..：失败 ;1:成功  ..其他具体消息
     */
    public static String sendPost(String url,Map<String, String> paramMap){
        String returnmsg="";
    try {
           //封装参数
           List<NameValuePair> parameters = new ArrayList<NameValuePair>();
           if(paramMap!=null&¶mMap.keySet()!=null){
                for(String key:paramMap.keySet()){
                    parameters.add(new BasicNameValuePair(key,paramMap.get(key)));
                }
            }
           //创建UrlEncodedFormEntity对象
           UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters,"UTF-8");
           if(url.startsWith("https")){
               returnmsg=sendPostHttps(url,formEntiry);
           }else{
               returnmsg=sendPostHttp(url,formEntiry);
           }
       }catch(Exception e){
          e.printStackTrace();
       }
     return returnmsg;
   }
    
   //发送http请求
   private static String sendPostHttp(String url, UrlEncodedFormEntity formEntiry){
        // 发送请求
        HttpClient client = new DefaultHttpClient();
        String returnmsg="fail"; //失败
    try {
            //实例化HTTP POST方法
            HttpPost postmethod = new HttpPost(url);
            postmethod.setEntity(formEntiry);
            
            //执行请求
            HttpResponse reponse = client.execute(postmethod);
            //回去返回实体
            HttpEntity entity = reponse.getEntity();
             returnmsg=EntityUtils.toString(entity,"UTF-8");
            //System.out.println("POST返回数据:"+returnmsg);
            //若返回消息有中文要进行一下解码   服务器要加码URLEncoder.encode("服务器返回中文", "UTF-8")
            //System.out.println("POST返回数据--:"+URLDecoder.decode(returnmsg,"utf-8"));
     }catch(Exception e) {
            returnmsg="fail";
            e.printStackTrace();
     }finally{
             //关闭连接,释放资源
            client.getConnectionManager().shutdown();
     }
      return returnmsg;
    }
   
    //发送https请求
    private static String sendPostHttps(String url, UrlEncodedFormEntity formEntiry){
        //long responseLength = 0;                         //响应长度
        String returnmsg = null;                   //响应内容
        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例
        
        X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return null; }
        };
        
        
        try {
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
             
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[]{xtm}, null);
             
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
             
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
             
            HttpPost httpPost = new HttpPost(url);                        //创建HttpPost
            httpPost.setEntity(formEntiry);
             
            HttpResponse response = httpClient.execute(httpPost); //执行POST请求
            HttpEntity entity = response.getEntity();             //获取响应实体
             
            if (null != entity) {
                //responseLength = entity.getContentLength();
                returnmsg = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity); //销毁你打开的entity  Consume response content
            }
            //System.out.println("请求地址: " + httpPost.getURI());
            //System.out.println("响应状态: " + response.getStatusLine());
            //System.out.println("响应长度: " + responseLength);
            System.out.println("响应内容: " + returnmsg);
            
        }catch (Exception e) {
            returnmsg="fail";
            e.printStackTrace();
        }finally{
            httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源
        }
        
      return returnmsg;
      
    }
}

```

