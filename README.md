# Thymeleaf Examples

## What is Thymeleaf

Thymeleaf는 HTML, XML, JavaScript, CSS와 같은 웹 문서를 만들기 위한 자바 템플릿 엔진입니다. Spring Boot에서는 Thymeleaf를 사용하여 서버 측의 데이터를 템플릿과 결합하여 동적으로 생성된 웹 페이지를 생성할 수 있습니다.

Thymeleaf는 HTML 파일에서 서버 측의 데이터를 쉽게 바인딩할 수 있는 문법을 제공합니다. 예를 들어, 다음과 같이 서버 측에서 전달된 데이터를 HTML 문서에 출력할 수 있습니다.

## How to Use

### 1. 의존성 추가

- Maven
    
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    ```
  
- Gradle

    ```gradle
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf:'
    ```

- 추가 의존성
  - boot-starter-web
  - boot-starter-test

### 2. 정적 리소스 파일 경로 설정

```text
- resources
    - static <- css/js 같은 에셋들을 저장하는 장소. 자동으로 인식함.
    - templates <- html 파일을 위치시키는 장소.
```

templates 폴더 하위에 있는 html 파일들은 static 폴더 하위의 정적 파일로 접근과 다르게 url을 바로 호출할 수 없습니다.

### 3. thymeleaf 옵션 설정
