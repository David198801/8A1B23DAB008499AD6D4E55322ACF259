2.DOM操作

创建工厂

创建解析器

解析xml获取document对象

```javascript
//获取工厂类实例
DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();

try {
	//获取DocumentBuilder
	DocumentBuilder builder = f.newDocumentBuilder();
	//解析xml
	File file = new File("xml\\收藏信息.xml");
	Document document = builder.parse(file);
	//获取Brand节点
	NodeList brands = document.getElementsByTagName("Brand");
	for (int i = 0; i < brands.getLength(); i++) {
		Node brandNode = brands.item(i);
		Element elementBrand = (Element)brandNode;
		//输出Brand的name属性
		System.out.println("品牌：" + elementBrand.getAttribute("name"));
		
		//获取Type节点
		NodeList types = document.getElementsByTagName("Type");
		for (int j = 0; j<types.getLength(); j++) {
			Node childNode = types.item(j);
			//判断是否为元素节点
			if(childNode.getNodeType()==Node.ELEMENT_NODE){
				Element elementType = (Element)childNode;
				System.out.println("\t型号：" + elementType.getAttribute("name"));
			}
			
		}
	}
```



