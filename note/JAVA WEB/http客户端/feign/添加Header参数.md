https://www.yisu.com/zixun/603179.html

```javascript
@FeignClient(name="name",url = "127.0.0.1:8090/test",path = "/",configuration = FeignConfig.class)
public interface IUserService {
 
    @RequestMapping(value = "getUserPage",method = RequestMethod.POST)
    @Headers(value={"ContentType=application/x-www-form-urlencoded","Inner_token=PXH0dP5I8qQ8UbFPpzm67cQkm7j8tWT2Kwn6J6SXYkfp2kMo/lSqHQ=="})
    public Map<String,Object> getUserPage(User user);
}
```

@Headers没用就用@RequestMapping的headers

```javascript
@FeignClient(name="name",url = "127.0.0.1:8090/test",path = "/",configuration = FeignConfig.class)
public interface IUserService {
 
    @RequestMapping(value = "getUserPage",method = RequestMethod.POST,headers = {"ContentType=application/x-www-form-urlencoded","Inner_token=PXH0dP5I8qQ8UbFPpzm67cQkm7j8tWT2Kwn6J6SXYkfp2kMo/lSqHQ=="})
    public Map<String,Object> getUserPage(User user);
```





动态header

https://stackoverflow.com/questions/37066331/using-headers-with-dynamic-values-in-feign-client-spring-cloud-brixton-rc2

@RequestHeader

```javascript
@FeignClient(name="Simple-Gateway")
interface GatewayClient {    
    @RequestMapping(method = RequestMethod.GET, value = "/gateway/test")
    String getSessionId(@RequestHeader("X-Auth-Token") String token);
}
```

HttpHeaders 

```javascript
@PostMapping(path = "${path}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
ResponseEntity<?> callService(@RequestHeader HttpHeaders headers, @RequestBody Object object);

private HttpHeaders getHeaders() {
  HttpHeaders headers = new HttpHeaders();

  headers.add("Authorization", "1234");
  headers.add("CLIENT_IT", "dummy");
  return headers;
}
```

