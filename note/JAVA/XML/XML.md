XML

XML（EXtensible Markup Language）,可扩展标记语言



主要用于：

1.配置文件

2.储存数据



语法：

声明：版本，字符编码

<?xml version="1.0" encoding="UTF-8"?>

标签：

<元素名 属性名=“属性值”>元素内容</元素名>

<元素名 属性名=“属性值” />

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<books>
    <!--图书信息 -->
    <book id="bk101">
        <author>王珊</author>
        <title>.NET高级编程</title>
        <description>包含C#框架和网络编程等</description>
    </book>
    <book id="bk102">
        <author>李明明</author>
        <title>XML基础编程</title>
        <description>包含XML基础概念和基本作用</description>
    </book>
</books>
```



规则：

所有XML元素都必须有结束标签

XML标签对大小写敏感

XML必须正确的嵌套

同级标签以缩进对齐

元素名称可以包含字母、数字或其他的字符

元素名称不能以数字或者标点符号开始

元素名称中不能含空格



命名空间：

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<batchCompany xmlns="http://www.Aptech_edu.ac"
    xmlns:tea="http://www.tea.org">
    <batch-list>
        <batch type="thirdbatch">第三批次</batch>
        <batch tea:type="thirdbatch">第三批茶</batch>
        <batch>午班批次</batch>
    </batch-list>
</batchCompany>
```



