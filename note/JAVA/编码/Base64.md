

```javascript
BASE64Encoder base64Encoder = new BASE64Encoder();
BASE64Decoder base64Decoder = new BASE64Decoder();

String s1 = base64Encoder.encode("中文".getBytes("UTF-8"));
String s2 = new String(base64Decoder.decodeBuffer(s1), "UTF-8");
System.out.println(s2);
```

