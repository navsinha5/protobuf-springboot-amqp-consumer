# Introduction
This project is a part of three layer microservices design (gateway - amqp - storage). 
This code base works as a 'storage' service.
- TLS level encryption is applied within REST APIs.
- Data is decrypted at consumer level after receiving from a queue.


# Installation 
Note:- Locally installed `docker-compose` desired

Run: `sudo docker-compose up`


# Test
Note:- The endpoints are exposed on `https://`
- Create
```
POST /gw/person HTTP/1.1
Host: 192.168.1.101:8443
X-File-Type: XML
Content-Type: application/json

{
    "name": "navdeep",
    "age": 28,
    "dob": "05-09-2020",
    "salary": "123456.123"
}
```

- Read
```
GET /gw/person?id=e08b131e-455f-4818-ade3-fccb933aa29d HTTP/1.1
Host: 192.168.1.101:8443
```

- Update
```
PATCH /gw/person?id=e08b131e-455f-4818-ade3-fccb933aa29d HTTP/1.1
Host: 192.168.1.101:8443
X-File-Type: XML
Content-Type: application/json

{
    "name":"fake"
}
```


# Known Issues
- Error handing can be made more elegant
- TLS can be applied between AMQP Broker and Producer


# Related Resources
- https://github.com/navsinha5/protobuf-springboot-amqp-consumer.git


# References
- https://developers.google.com/protocol-buffers/docs/javatutorial
- https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html
- https://www.baeldung.com/spring-amqp

