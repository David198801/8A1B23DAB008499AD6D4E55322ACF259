自己创建

https://www.jianshu.com/p/9ad32c78ef9f

http://maven.apache.org/guides/mini/guide-creating-archetypes.html





使用自建archetype需要设置创建archetype时使用本地仓库

在设置里maven的VM Options加上-DarchetypeCatalog=local参数

或使用maven创建项目时加入属性archetypeCatalog=local