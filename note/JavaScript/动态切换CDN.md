https://segmentfault.com/q/1010000041149228?utm_source=sf-homepage

```javascript
<script>
// 可实现多个cdn失效切换
var cdnArr =[{
css:'//unpkg.com/bootstrap@3.3.7/dist/css/bootstrap.css',
js:'//unpkg.com/bootstrap@3.3.7/dist/js/bootstrap.min.js'}];

function autoCDN(){
      if(!cdnArr.length) return;
      var cdn=cdnArr.shift();

      var l=document.createElement('link');
      l.href=cdn.css;
      l.onerror=function(){autoCDN(this);};
      document.head.appendChild(l);

      var s=document.createElement('script');
      s.src=cdn.js;
      s.onerror=function(){autoCDN(this);};
      document.body.appendChild(s);
}
</script>
<script 
src="//cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" 
onerror="autoCDN();" 
></script>
```

