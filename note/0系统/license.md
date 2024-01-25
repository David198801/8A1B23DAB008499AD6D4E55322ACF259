com.yss.sofa.security.manager.license.service.impl.LicenseServiceImpl



```javascript
package com.lzb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;







public class Test2 {
    public static void main(String[] args) throws Exception {
    	String key = "NTBSQgL3oTIpDWUj3AZMGQ==";
    	String path = "C:\\Users\\LiuZhongbin\\Desktop\\license\\YsstechSystemV2.4.lic";
    	File file = new File(path);
    	File outFile = new File("C:\\Users\\LiuZhongbin\\Desktop\\license\\outYsstechSystemV2.4.lic");
    	
    	InputStream is = null;
    	OutputStream os = null;
    	try {
			is = new FileInputStream(file);
			os = new FileOutputStream(outFile);
			byte[] buffer = new byte[is.available()];
			int read = is.read(buffer);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			byte[] keyB = new BASE64Decoder().decodeBuffer(key);
			SecretKey secretkey = new SecretKeySpec(keyB, "AES");
		    cipher.init(2, secretkey);
		    byte[] decryptdata = cipher.doFinal(buffer);
		    os.write(decryptdata);
		    
		} finally {
			os.close();
			is.close();
		}
    	
    }
}

```

