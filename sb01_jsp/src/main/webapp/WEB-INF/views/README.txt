https://spring.io/tools

1. C:\Tools 폴더에 압축 풀어서 이동
2. 롬복 설치 
3. SpringBootClass 폴더 만들고 워크스페이스 잡기

레거시 : jdk-11까지

부트 : jdk-17 이상

jsp 권장 X

1. enc,
2. jdk-17, compiler 17

SB01_JSP : SB로 JSP 연동 프로젝트

3.5 → Mybatis 못씀

3.4.7선택

spring Boot DevTools : 서버 재시작 안해도됨

클래스 만들거 아니면 lombok 필요 x


mybatis → datasource 설정

pom.xml

application.properties


# #서버 포트

server.port=80

# #오라클 연결 문자열

spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XE
spring.datasource.username=scott
spring.datasource.password=tiger

#mybatis.mapper-locations=classpath:mappers/**/*.xml 

class 폴더 안에 컴파일 됨.

static 폴더에 정적인 자원(바뀌지 않는 자원 -이미지, css, js) 들 넣어 둠.


jsp 파일 실행 위해서

1. webapp/WEB-INF/views 아래 위치
2. 

<!-- Spring Boot는 기본적으로 JSP를 지원 X, JSP 파일을 컴파일하고 실행하기 위한 Jasper 엔진을 포함 -->
<dependency>
<groupId>org.apache.tomcat.embed</groupId>
<artifactId>tomcat-embed-jasper</artifactId>
</dependency>
<!-- JSTL(JavaServer Pages Standard Tag Library)의 인터페이스(API) 를 제공, <c:forEach>, <c:if> 등 -->
<dependency>
<groupId>jakarta.servlet.jsp.jstl</groupId>
<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
</dependency>
<!-- JSTL 태그가 실제로 작동하게 만들어 준다. -->
<dependency>
<groupId>org.glassfish.web</groupId>
<artifactId>jakarta.servlet.jsp.jstl</artifactId>
</dependency>





pom.xml

```
	<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>3.0.4</version>
	</dependency>
	<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc11</artifactId>
			<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.mybatis.spring.boot</groupId>
		<artifactId>mybatis-spring-boot-starter-test</artifactId>
		<version>3.0.4</version>
		<scope>test</scope>
	</dependency>

```

application.yml

```jsx
datasource:
driver-class-name: oracle.jdbc.driver.OracleDriver
url: jdbc:oracle:thin:@localhost:1521/XE
username: scott
password: tiger

mybatis:
  mapper-locations: classpath:mappers/**/*.xml
  config-location: classpath:mybatis-config.xml
```

src/main/resources/mybatis-config.xml (rootcontext부터 잡힘)

org.doit.ik.persistence-TimeMapper.java

src/main/resources/mappers -TimeMapper.xml