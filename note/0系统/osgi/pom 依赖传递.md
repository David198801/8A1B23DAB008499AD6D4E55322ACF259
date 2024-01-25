ci.config没有开启依赖传递

```javascript
  	<build>
	    <plugins>
			<plugin>
				<groupId>org.apache.felix</groupId>
				<artifactId>maven-bundle-plugin</artifactId>
				<extensions>true</extensions>
				<configuration>
					<instructions>
						<Embed-Transitive>true</Embed-Transitive>
					</instructions>
				</configuration>
			</plugin>
	  	</plugins>
	</build>
```



```javascript
			<plugin>
				<groupId>org.apache.felix</groupId>
				<artifactId>maven-bundle-plugin</artifactId>
				<extensions>true</extensions>
				<configuration>
					<obrRepository>NONE</obrRepository>
					<instructions>
						<Bundle-SymbolicName>${project.artifactId}</Bundle-SymbolicName>
						<Embed-Dependency>*;scope=compile|runtime;inline=false</Embed-Dependency>
						<Embed-Transitive>true</Embed-Transitive>
						<Embed-Directory>lib</Embed-Directory>
						<Embed-StripVersion>true</Embed-StripVersion>
						<Bundle-ClassPath>.</Bundle-ClassPath>
						<Bundle-Version>${com.yss.sofa.export.web.client}</Bundle-Version>
						<Include-Resource>
							{maven-resources}, {maven-dependencies}
							<!-- ,target/classes/cml-view-applet.jar, target/classes/cml-vendor-applet.jar -->
						</Include-Resource>
					</instructions>
				</configuration>
			</plugin>
```

