https://www.aqistudy.cn/

jquery中setTimeout反复定义两个函数endebug、txsdefwsw

解决：charles劫持修改jquery.min.js、/、city_realtime.php



[test.rar](assets/test.rar)

jquery.min.js，eval混淆



```javascript
// debug detect
eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('2 M(g,y){h(!g){!2(e){2 n(e){2 n(){j u}2 o(){4.8&&4.8.m&&4.8.m.H?t("9"):(a="g",v.G(d),v.F(),t(a))}2 t(e){u!==e&&(u=e,"2"==7 c.5&&c.5(e))}2 r(){l||(l=!0,4.I("k",o),L(f))}"2"==7 e&&(e={5:e});3 i=(e=e||{}).K||J,c={};c.5=e.5;3 a,d=B z;d.D("C",2(){a="9"});3 u="E";c.A=n;3 f=s(o,i);4.S("k",o);3 l;j c.N=r,c}3 o=o||{};o.x=n,"2"==7 6?(6.P||6.R)&&6(2(){j o}):"O"!=7 b&&b.p?b.p=o:4.q=o}(),q.x(2(e){3 a=0;3 n=s(2(){h("9"==e){w(2(){h(a==0){a=1;w(y)}},Q)}},T)})}}',56,56,'||function|var|window|onchange|define|typeof|Firebug|on||module|||||off|if||return|resize||chrome|||exports|jdetects||setInterval|||console|setTimeout|create|code|Image|getStatus|new|id|__defineGetter__|unknown|clear|log|isInitialized|removeEventListener|500|delay|clearInterval|endebug|free|undefined|amd|200|cmd|addEventListener|100'.split('|'),0,{}));
// debug detect
eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('3 l(){2 r="v",n="5",e="8";3 o(r){m(!r)4"";h(2 t="",n=w,e=0;e<r.6;e++){2 o=r.9(e)^n;n=n*e%u+p,t+=k.j(o)}4 t}q{2 a=["r",o("z"),"g",o("x"),3(t){m(!t)4"";h(2 o="",a=r+n+e+"7",c=y,f=0;f<t.6;f++){2 i=t.9(f);c=(c+1)%a.6,i^=a.9(c),o+=k.j(i)}4 o}("@"),"b","e","d"].B().E("");!3 c(r){(1!==(""+r/r).6||0===r)&&3(){}.D(a)(),c(++r)}(0)}C(a){A(l,s)}}',41,41,'||var|function|return||length|||charCodeAt||||||||for||fromCharCode|String|txsdefwsw|if|||2333|try||100||256|V|44106|갭|45860|갯|setTimeout|reverse|catch|constructor|join'.split('|'),0,{}));

```



```javascript
<script type="text/javascript" src="js/jquery.min.js?v=1.1"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/tab.js?v=2.22"></script>
<script type="text/javascript">

var debugflag = false;

  endebug(false, function () {
      document.write('检测到非法调试, 请关闭调试终端后刷新本页面重试!');
      document.write("<br/>");
      document.write("Welcome for People, Not Welcome for Machine!");
      debugflag = true;
  });
  txsdefwsw();
  document.onkeydown = function() {
    if ((e.ctrlKey) && (e.keyCode == 83)) {
      alert("检测到非法调试，CTRL + S被管理员禁用");
      return false;
    }
  }
  document.onkeydown = function() {
    var e = window.event || arguments[0];
    if (e.keyCode == 123) {
      alert("检测到非法调试，F12被管理员禁用");
      return false;
    }
  }
  document.oncontextmenu = function() {
    alert('检测到非法调试，右键被管理员禁用');
    return false;
  }
	$(function()
	{
		if (!debugflag && !window.navigator.webdriver) {
      loadTab();
    }
		if(!isSupportCanvas())
		{
			$("#browertip").show();
		}
	});

	function isSupportCanvas()
	{
	   var elem = document.createElement('canvas');
	   return !!(elem.getContext && elem.getContext('2d'));
	}
	</script>
```



```javascript
function endebug(off, code) {
    if (!off) { !
        function(e) {
            function n(e) {
                function n() {
                    return u
                }
                function o() {
                    window.Firebug && window.Firebug.chrome && window.Firebug.chrome.isInitialized ? t("on") : (a = "off", console.log(d), console.clear(), t(a))
                }
                function t(e) {
                    u !== e && (u = e, "function" == typeof c.onchange && c.onchange(e))
                }
                function r() {
                    l || (l = !0, window.removeEventListener("resize", o), clearInterval(f))
                }
                "function" == typeof e && (e = {
                    onchange: e
                });
                var i = (e = e || {}).delay || 500,
                c = {};
                c.onchange = e.onchange;
                var a, d = new Image;
                d.__defineGetter__("id",
                function() {
                    a = "on"
                });
                var u = "unknown";
                c.getStatus = n;
                var f = setInterval(o, i);
                window.addEventListener("resize", o);
                var l;
                return c.free = r,
                c
            }
            var o = o || {};
            o.create = n,
            "function" == typeof define ? (define.amd || define.cmd) && define(function() {
                return o
            }) : "undefined" != typeof module && module.exports ? module.exports = o: window.jdetects = o
        } (),
        jdetects.create(function(e) {
            var a = 0;
            var n = setInterval(function() {
                if ("on" == e) {
                    setTimeout(function() {
                        if (a == 0) {
                            a = 1;
                            setTimeout(code)
                        }
                    },
                    200)
                }
            },
            100)
        })
    }
}



function txsdefwsw() {
    try {
        var a = "debugger";
		!
        function c(r) { true &&
            function() {}.constructor(a)(),
            c(++r)
        } (0)
    } catch(a) {
        setTimeout(txsdefwsw, 100)
    }
}


Function.prototype.constructor = function(){}
```

