# 命令行编译

1. 下载编译器

   https://github.com/JetBrains/kotlin/releases

   https://github.com/JetBrains/kotlin/releases/download/v2.1.0-Beta2/kotlin-compiler-2.1.0-Beta2.zip

2. hello.kt

```kotlin
fun main(args: Array<String>) {
    println("Hello, World!")
}
```

```shell
kotlinc hello.kt -include-runtime -d hello.jar
```

- **-d**: 用来设置编译输出的名称，可以是 class 或 .jar 文件，也可以是目录。
- **-include-runtime** : 让 .jar 文件包含 Kotlin 运行库，从而可以直接运行。

3. 执行

```javascript
   java -jar hello.jar
```

   

# idea

直接新建kotlin项目即可
