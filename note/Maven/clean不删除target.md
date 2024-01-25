https://github.com/Alfresco/alfresco-sdk/issues/261

```javascript
   <profile>
        <id>purge</id>
        <build>
            <plugins>
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <configuration>
                        <excludeDefaultDirectories>true</excludeDefaultDirectories>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
```



```javascript
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-clean-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <id>Deleting all files under target, but not target itself</id>
                        <phase>clean</phase>
                        <goals>
                            <goal>clean</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <excludeDefaultDirectories>true</excludeDefaultDirectories>
                    <filesets>
                        <fileset>
                            <directory>target/</directory>
                            <followSymlinks>false</followSymlinks>
                            <includes>
                                <include>**/*</include>
                            </includes>
                        </fileset>
                    </filesets>
                </configuration>
            </plugin>
```

