https://github.com/dreamhead/moco

文档

https://github.com/dreamhead/moco/blob/master/moco-doc/apis.md



https://repo1.maven.org/maven2/com/github/dreamhead/moco-runner/1.2.0/moco-runner-1.3.0-standalone.jar



```javascript
chcp 65001
java -jar -Dfile.encoding=UTF-8 moco-runner-1.3.0-standalone.jar http -p 9090 -c moco.json
```



moco.json

```javascript
[
	{
        "description": "test",
        "request": {
            "uri": "/firmbank/online/PFCFoxSecurities",
            "method": "POST",
			"headers": {
                "Content-Type": "text/xml; charset=GB18030"
            }
			
        },
        "response": {
			"headers": {
                "Content-Type": "application/xml"
            },
            "text": "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n<QFUNDACCTINFOTRNRQ>\n  <TRNUID>2021111500000020</TRNUID>\n  <RQBODY>\n    <ACCTNO>6666111166662222</ACCTNO>\n    <FUNDACCTNO>8888888888888888</FUNDACCTNO>\n    <PWD>123456</PWD>\n  </RQBODY>\n</QFUNDACCTINFOTRNRQ>\n"
        }
    },
	{
        "description": "chongqing",
        "request": {
            "uri": "/chongqing/commandSend",
            "method": "POST"
			
        },
        "response": {
			"headers": {
                "Content-Type": "application/json",
				"Access-Control-Allow-Origin":"*"
            },
           "json": {
				"head": {
					"reqNo": "20220117195150000001",
					"reqDate": "20220117",
					"reqTime": "195250",
					"reqSys": "ZY"
				},
				"param": {
					"rtnCode": "000000",
					"rtnMsg": "成功",
					"rtnRsn": ""
				}
			}
        }
    }
]
```

延迟响应

```javascript
{
        "description": "产品建账完成",
        "request": {
            "uri": "/pms/pdtcenter/online/valuation",
            "method": "POST"
			
        },
        "response": {
			"headers": {
                "Content-Type": "application/json;charset=UTF-8",
				"Access-Control-Allow-Origin":"*"
            },
			"latency":{
			  "duration": 1,
			  "unit": "second"
			},
           "json": {
			    "head": {
			        "reqNo": "20220117195150000001",
			        "reqDate": "20220117",
			        "reqTime": "195250",
			        "reqSys": "ZY"
			    },
			    "param": {
			        "rtnCode": "111111",
			        "rtnMsg": "失败",
			        "rtnRsn": ""
			    }
			}
        }
    }
```

https

https://blog.csdn.net/qq_41403872/article/details/106433433

