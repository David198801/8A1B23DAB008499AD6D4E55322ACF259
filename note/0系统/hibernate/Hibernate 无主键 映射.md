https://blog.csdn.net/iteye_5963/article/details/82093684



</composite-id>

</key-property>



```javascript

<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
 
<hibernate-mapping>
	<class name="com.quanjj.function.test.model.TestInfo" 
	table="MASS_APP_DEVICE_TOKEN" schema="mass">
		<composite-id>
			<key-property name="deviceId" type="java.lang.String">
				<column name="DEVICEID" />
			</key-property>
 
			<key-property name="token" type="java.lang.String">
				<column name="TOKEN" />
			</key-property>
 
			<key-property name="createDate" type="java.util.Date">
				<column name="CDATE" />
			</key-property>
 
			<key-property name="updateDate" type="java.util.Date">
				<column name="UDATE" />
			</key-property>
		</composite-id>
	</class>
</hibernate-mapping>
```

