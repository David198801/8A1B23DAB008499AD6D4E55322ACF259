

```javascript
private static String readUtf8File(String fileName) {

		// 以utf-8编码读取文件：
		FileInputStream fis = null;
		InputStreamReader isr = null;
		String fileContent = "";

		try {
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				fileContent += line;
				fileContent += "\r\n"; // 补上换行符
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent;
	}
```

