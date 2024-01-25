

```javascript
<plugin>
                <groupId>org.apache.felix</groupId>
                <artifactId>maven-bundle-plugin</artifactId>
                <extensions>true</extensions>
                <executions>
                    <execution>
                        <id>bundle-manifest</id>
                        <phase>process-classes</phase>
                        <goals>
                            <goal>manifest</goal>
                        </goals>
                        <configuration>
                            <instructions>
                                <Private-Package>org.example</Private-Package>
                                <Web-ContextPath>webappcontextpath</Web-ContextPath>
                                <Bundle-ClassPath>.,WEB-INF/classes,{maven-dependencies}</Bundle-ClassPath>
                                <Embed-Dependency>*;scope=compile|runtime</Embed-Dependency>
                                <Embed-Directory>WEB-INF/lib</Embed-Directory>
                            </instructions>
                            <supportedProjectTypes>
                                <supportedProjectType>war</supportedProjectType>
                            </supportedProjectTypes>
                        </configuration>
                    </execution>
                </executions>
</plugin>
```

