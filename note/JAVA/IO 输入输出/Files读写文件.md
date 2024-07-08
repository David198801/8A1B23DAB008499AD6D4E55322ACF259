```java
package com.jiayuan.feature.java8;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Files工具类可以一行代码搞定文件读写, 后面java11又继续升了级，见java11目录
 */
public class FilesDemo {

    /**
     * 把字符串的内容写到D盘的test.txt里去，文件不存在就创建
     *
     * @throws IOException
     */
    @Test
    public void writeFile() throws IOException {
        String str = """
                我是一棵，小小的小草，
                深深地埋在泥土之中。
                """;
        Files.write(Paths.get("D:\\test.txt"), str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }

    @Test
    public void readFile() throws IOException {
        String allText = new String(Files.readAllBytes(Path.of("D:\\test.txt")));
        System.out.println(allText);
    }

    @Test
    public void readFile2() throws IOException {
        List<String> allLines = Files.readAllLines(Path.of("D:\\test.txt"));
        allLines.forEach(System.out::println);
    }

}

```

