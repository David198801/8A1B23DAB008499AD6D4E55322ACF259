2024/06/25 18:29:54 [error] 5047#0: *14 open() "/root/aaabbb.com/favicon.ico" failed (13: Permission denied), client: 192.168.56.1, server: localhost, request: "GET /favicon.ico HTTP/1.1", host: "aaabbb.com", referrer: "http://aaabbb.com/"





没有访问权限，排查权限的同时conf的user 设为当前用户并去掉注释