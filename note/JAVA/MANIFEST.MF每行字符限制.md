https://stackoverflow.com/questions/33143460/does-the-jar-manifest-mf-max-line-length-of-72-include-the-eol-bytes



While this isn't strictly answering the question of what does the specification mean when it says line it does answer it in terms of how the JDK has implemented its Manifest support around that specification. You'd hope that they both came from the same team and hence any ambiguity in the specification can be clarified by the details of the implementation, but I'll leave this as unaccepted for a while in case a more canonical answer can be found.

From reading through the JDK source and running a few tests using the JDK Manifest class to write out a manifest file I can say that the JDK (at version 1.7) writes out manifest entries where the line length includes the EOL bytes.

```javascript
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.jar.Attributes.Name.MANIFEST_VERSION;

public class ManifestTest {
  public static void main(String[] args) throws IOException {
    Manifest m = new Manifest();
    Attributes a = m.getMainAttributes();
    a.put(MANIFEST_VERSION, "1.0"); // required or the file doesn't get written

    // Long-Property: This line has 72 characters without eol, it's hard to get
    a.putValue("Line-Property", "This line has 72 characters without eol, it's hard to get");
    // Long-Property: This line has 72 characters with eol, it is hard to get
    a.putValue("Long-Property", "This line has 72 characters with eol, it is hard to get");
    a.putValue("Massive-Property", "This line wraps around always as it has a lot of characters in it");

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    m.write(out);
    System.out.println(new String(out.toByteArray(), UTF_8));
  }
}
```

results in the following output

Manifest-Version: 1.0
Long-Property: This line has 72 characters with eol, it is hard to get
Massive-Property: This line wraps around always as it has a lot of cha
 racters in it
Line-Property: This line has 72 characters without eol, it's hard to g
 et


Note that the Long-Property fits onto one line as the line content is 70 characters + 2 characters of EOL which is <= 72. The Line-Property which has line content of 72 characters gets split into two lines where the first line contains the first 70 characters + CR LF followed by a space and the remaining 2 characters.

It's worth noting here that the Manifest read method is lenient about the line length, so long as the line isn't greater than 512 bytes long including the EOL markers then it will happily read the file as shown by the code below

```javascript
Manifest m = new Manifest();
String longManifest = "Manifest-Version: 1.0\r\n" +
    "Too-Long: This line is longer than 72 characters, does the Manifest class correctly \r\n" +
    " handle parsing of it even over multiple lines?\r\n";
m.read(new ByteArrayInputStream(longManifest.getBytes(UTF_8)));
System.out.println(m.getMainAttributes().getValue("Too-Long"));
```

Which happily outputs This line is longer than 72 characters, does the Manifest class correctly handle parsing of it even over multiple lines? as a single manifest value.