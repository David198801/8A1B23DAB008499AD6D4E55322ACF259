https://www.zhihu.com/question/424647397/answer/1517773907

https://www.cnblogs.com/aspirant/p/8621848.html

```javascript
try (BufferedReader br =
    new BufferedReader(new FileReader(path))) {
    return br.readLine();
}
```



```javascript
try (
    java.util.zip.ZipFile zf =
        new java.util.zip.ZipFile(zipFileName);
    java.io.BufferedWriter writer =
        java.nio.file.Files.newBufferedWriter(outputFilePath, charset)
) {
    // Enumerate each entry
    for (java.util.Enumeration entries =
                            zf.entries(); entries.hasMoreElements();) {
        // Get the entry name and write it to the output file
        String newLine = System.getProperty("line.separator");
        String zipEntryName =
            ((java.util.zip.ZipEntry)entries.nextElement()).getName() +
            newLine;
        writer.write(zipEntryName, 0, zipEntryName.length());
    }
}
```

