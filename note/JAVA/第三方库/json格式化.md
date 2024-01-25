https://repo1.maven.org/maven2/com/bazaarvoice/jsonpps/jsonpps/1.1/jsonpps-1.1.jar



```javascript
java -jar jsonpps-1.1.jar input.json -o output.json
```





Shortcut (for Windows):

1. Create file jsonpps.cmd in the same directory with the following content:

```javascript
@echo off
java -Xms64m -Xmx64m -jar %~dp0\jsonpps-1.1.jar %*
```

Shortcut usage examples:

1. Format stdin to stdout:

```javascript
echo { "x": 1 } | jsonpps
```

1. Format stdin to file

```javascript
echo { "x": 1 } | jsonpps -o output.json
```

1. Format file to file:

```javascript
jsonpps input.json -o output.json
```

