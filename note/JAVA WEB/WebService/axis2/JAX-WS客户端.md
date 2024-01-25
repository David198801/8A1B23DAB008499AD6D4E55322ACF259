

```javascript
<dependency>
    <groupId>org.apache.axis2</groupId>
    <artifactId>axis2-jaxws</artifactId>
    <version>1.7.9</version>
</dependency>
<dependency>
    <groupId>org.apache.axis2</groupId>
    <artifactId>axis2-adb</artifactId>
    <version>1.7.9</version>
</dependency>
<dependency>
    <groupId>org.apache.axis2</groupId>
    <artifactId>axis2-transport-local</artifactId>
    <version>1.7.9</version>
</dependency>
```



```javascript
package com.lzb.ws.client;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        //URL
        EndpointReference targetEPR =
                new EndpointReference("http://localhost:8080/helloWorld");
        options.setTo(targetEPR);
        // 对应soapAction
        options.setAction("");
        // 指定方法的参数值
        Object[] opAddEntryArgs = new Object[] {"XiaoMing"};
        // nameSpaceURI对应targetNamespace ,localPart对应operation的name即方法名
        QName opAddEntry = new QName("http://ws.lzb.com/", "sayHello");
        // 调用方法并输出该方法的返回值
        OMElement response = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs);
        System.out.println(response);

    }
}

```







生成代码的形式

```javascript
wsdl2java -uri http://localhost:8080/helloWorld?wsdl -d . -p
 com.lzb.ws.client -ss -sd -ssi -g
```

报错则加上No proper databinding has taken place

```javascript
wsdl2java -uri http://localhost:8080/helloWorld?wsdl -d . -p
 com.lzb.ws.client -ss -sd -ssi -g -d adb
```



```javascript
public static void main(String[] args) throws Exception {
    WebServiceTestServiceStub stub = new WebServiceTestServiceStub();
    SayHello sayHello = new SayHello();
    sayHello.setArg0("XiaoMing");
    SayHelloE e = new SayHelloE();
    e.setSayHello(sayHello);
    SayHelloResponseE sayHelloResponseE = stub.sayHello(e);
    System.out.println(sayHelloResponseE.getSayHelloResponse().get_return());
}
```

或

```javascript
HelloServiceStub stub = new HelloServiceStub();
Add add = new Add();
add.setX(1);
add.setY(2);
int result = stub.add(add).get_return();
System.out.println(result);
```

或

```javascript
// 创建客户端 stub 对象
MyWebServiceStub stub = new MyWebServiceStub();
// 创建请求对象
MyWebServiceStub.SayHello request = new MyWebServiceStub.SayHello();
request.setUsername("Alice");
// 调用 WebService 方法
MyWebServiceStub.SayHelloResponse response = stub.sayHello(request);
System.out.println(response.getGreeting());
```

