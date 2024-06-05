专门的反代

https://bili33.top/2019/10/25/CloudFlare-Workers/

https://wangdalao.com/3439.html



通用

https://github.com/ymyuuu/Cloudflare-Workers-Proxy/raw/main/worker.js

https://raw.githubusercontent.com/EtherDream/jsproxy/master/cf-worker/index.js



最简单

```javascript
addEventListener(
  "fetch",event => {
     let url=new URL(event.request.url);
     url.hostname="abc.def.xyz"; // 修改成自己的节点IP/域名
     url.protocol='https'; // 如为http协议请修改为http
     let request=new Request(url,event.request);
     event. respondWith(
       fetch(request)
     )
  }
)
```

https://github.com/itewqq/codeforces-reverse-proxy/blob/main/cf.js