dom4j操作

创建SAXReader

read()读取xml文件，得到Document对象

getRootElement()获取root元素

子元素节点：

elementIterator()获取迭代器或elements()获取list



```javascript
// 解析xml文件
File file = new File("xml\\SelectCode.xml");
SAXReader saxReader = new SAXReader();
try {
	Document document = saxReader.read(file);
	// 获取根节点
	Element root = document.getRootElement();
	// 获取次级节点code
	Iterator<Element> iterator = root.elementIterator();
	while (iterator.hasNext()) {
		Element codeElement = iterator.next();
		// 获取code节点的name属性
		String codeAttrName = codeElement.attributeValue("name");

		// 定义集合存储多个Parameter类的对象
		List<Parameter> code = new ArrayList<Parameter>();

		// 获取parameter节点
		Iterator<Element> parameterIterator = codeElement
				.elementIterator();
		while (parameterIterator.hasNext()) {
			Element parameterElement = parameterIterator.next();
			// 获取parameter节点的key和value属性
			String parameterAttrKey = parameterElement
					.attributeValue("key");
			String parameterAttrValue = parameterElement
					.attributeValue("value");

			// 创建Parameter类对象
			Parameter p = new Parameter(parameterAttrKey,
					parameterAttrValue);

			// 将Parameter类对象添加到集合中
			code.add(p);
		}
```

