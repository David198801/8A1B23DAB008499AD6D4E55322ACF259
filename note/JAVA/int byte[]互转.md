

```javascript
int 转 byte[]   低字节在前（低字节序）
public static byte[] toLH(int n) {  
  byte[] b = new byte[4];  
  b[0] = (byte) (n & 0xff);  
  b[1] = (byte) (n >> 8 & 0xff);  
  b[2] = (byte) (n >> 16 & 0xff);  
  b[3] = (byte) (n >> 24 & 0xff);  
  return b;  
}
int 转 byte[]   高字节在前（高字节序）
public static byte[] toHH(int n) {  
  byte[] b = new byte[4];  
  b[3] = (byte) (n & 0xff);  
  b[2] = (byte) (n >> 8 & 0xff);  
  b[1] = (byte) (n >> 16 & 0xff);  
  b[0] = (byte) (n >> 24 & 0xff);  
  return b;  
}
byte[] 转 int 低字节在前（低字节序）
public int toInt(byte[] b){
    int res = 0;
    for(int i=0;i<b.length;i++){
        res += (b[i] & 0xff) << (i*8);
    }
    return res;
}
byte[] 转 int 高字节在前（高字节序）
public static int toInt(byte[] b){
    int res = 0;
    for(int i=0;i<b.length;i++){
        res += (b[i] & 0xff) << ((3-i)*8);
    }
    return res;
}
```

