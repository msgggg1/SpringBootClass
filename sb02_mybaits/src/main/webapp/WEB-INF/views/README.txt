sb02_mybatis → sb03_oracle_mybatis_jsp 프로젝트 생성

프로젝트 복사 후

[application.properties](http://application.properties) or .yml

pom.xml - artifactID

[Application.java](http://Application.java) 파일명 변경

1. Connection 단위 테스트 작업
2. O + M + J 실습
1) http://localhost/dept/list → C → S → D → dept/list.jsp 응답
2) org.doit.ik.controller.DeptController.java
3) org.doit.ik.service.DeptService.java
    org.doit.ik.service.DeptServiceImpl.java
4) [org.doit.ik.persistence.DeptMapper.java](http://org.doit.ik.persistence.DeptMapper.java) + DeptMapper.xml
4-2)  [org.doit.ik.persistence.](http://org.doit.ik.persistence.DeptMapper.java)domain.DeptVO.java
5) dept/list.jsp 구현
3. sb03_oracle_mybatis_thymeleaf 생성

thymeleaf 사용

1.  application.properties
     viewresolver 삭제 

1. pom.xml 
dependency 추가

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

1. WEB-INF/views 모든 폴더와 jsp 파일 필요없다.

3-2. src/main/resources/templates 폴더
         ㄴ dept 폴더 생성
               ㄴ list.html
        index.html

타임리프 설정