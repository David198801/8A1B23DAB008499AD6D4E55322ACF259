JAVA中的Socket调用了操作系统的API。

如WinSock，openjdk源码中，jdk/src/windows/native/java/net中的c文件很多导入了winsock

```javascript
#include <winsock2.h>
```

DualStackPlainSocketImpl.c的bind0

```javascript
JNIEXPORT void JNICALL Java_java_net_DualStackPlainSocketImpl_bind0
  (JNIEnv *env, jclass clazz, jint fd, jobject iaObj, jint port,
   jboolean exclBind)
{
    SOCKETADDRESS sa;
    int rv;
    int sa_len = sizeof(sa);

    if (NET_InetAddressToSockaddr(env, iaObj, port, (struct sockaddr *)&sa,
                                 &sa_len, JNI_TRUE) != 0) {
      return;
    }

    rv = NET_WinBind(fd, (struct sockaddr *)&sa, sa_len, exclBind);

    if (rv == SOCKET_ERROR)
        NET_ThrowNew(env, WSAGetLastError(), "JVM_Bind");
}
```

https://www.cnblogs.com/tlxclmm/p/11997222.html