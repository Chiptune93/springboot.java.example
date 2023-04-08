# Spring WebClient Example

```
- WebClient : localhost:5001
    > 클라이언트 역할을 하는 프로젝트
- WebServer : localhost:5011
    > 서버 역할을 하는 프로젝트
````

## 목적
1. Spring WebClient를 사용하는 방법에 대해 기술한다.
2. 해당 클라이언트를 이용해, XML/JSON 데이터를 가져오는 방식에 대해 기술한다.
3. JAXB를 통해 XML 데이터를 파싱하여 가져온다.

## 테스트방법
1. webclient와 webserver를 구동한다.
2. localhost:5001/getJsonExample 또는 localhost:5001/getXmlExample를 호출하여 데이터 가져오는 것을 확인한다.
3. 각 소스를 참고한다.

## 클라이언트
```
main.java.com.springcloud.webclient
└httpClient
    └ getXmlDataExample.java : XML데이터를 가져오기 위한 데이터 객체
    └ httpWebClient.java : 웹 클라이언트 모듈
└ WebClientController.java : 기본 컨트롤러
```

## 서버
```
main.java.com.springcloud.webserver
└data
    └ exampleXmlData.java : XML데이터를 생성하기 위한 데이터 객체
└ WebServerController.java : 기본 컨트롤러
```

<br/>
참고 : https://happycloud-lee.tistory.com/220
