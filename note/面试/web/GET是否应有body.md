RFC认为GET的body没有语义，并且没有语义的请求body应当被server忽略

https://stackoverflow.com/questions/978061/http-get-with-request-body

https://tools.ietf.org/html/rfc7231#section-4.3.1

https://tools.ietf.org/html/rfc2616#section-4.3

RFC2616

> if the request method does not include defined semantics for an entity-body, then the message-body SHOULD be ignored when handling the request.

RFC7231

> A payload within a GET request message has no defined semantics; sending a payload body on a GET request might cause some existing implementations to reject the request.



