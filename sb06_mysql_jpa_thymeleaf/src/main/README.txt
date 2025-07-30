[수업 내용 정리]
1. sb05_oracle_jpa_thymeleaf 프로젝트 생성
2. application.yml 설정 추가

------------------------------------------------------------------
1. ORM 이란 ? 
  1) Object-Relational Mapping
  2) OOP의 객체와   RDMBS의 테이블을 자동으로 연결(매핑)하는 기술.

-------------------------------------------------------------------
1. Hibernate와 JPA는 모두 자바에서 데이터베이스를 다루기 위한 기술
2. JPA (Java Persistence API)
  1) 자바 ORM(Object-Relational Mapping) 표준 인터페이스
  2) 데이터베이스와 자바 객체 간 매핑을 정의하는 **명세(스펙)**
  3) JPA는 프레임워크가 아니라, API의 표준 정의
  3) @Entity, @Id, @OneToMany 같은 어노테이션 기반으로 객체-테이블 매핑을 정의
  4) JPQL(Java Persistence Query Language)이라는 객체 지향 쿼리 언어 사용.
  5) 독립적인 스펙이기 때문에, 여러 구현체가 있을 수 있다.
  (참고) JPA는 "규칙서"일 뿐 실제 동작하지는 않습니다.
3. Hibernate
  1) JPA의 구현체 중 하나, 가장 널리 사용되는 오픈소스 ORM 프레임워크
  2) JPA의 표준을 구현 + 자체적인 고급 기능도 제공
  3) JPA 없이 Hibernate 단독으로도 사용 가능. 
  
4.JPA와   Hibernate 관계
----------------------------------------------------
항목      JPA                           Hibernate
----------------------------------------------------
역할      ORM 표준 API                     ORM 프레임워크 구현체
소속      Java EE (지금은 Jakarta EE)         오픈소스 (Red Hat 관리)
사용    예   javax.persistence.Entity         org.hibernate.Session
관계      인터페이스 (규칙)                  구현체 (실제 동작)
예시      JPA는 Hibernate 외에도 EclipseLink, OpenJPA 등이 있음
----------------------------------------------------
  
5. Spring Boot에서는 내부적으로 Hibernate를 기본 JPA 구현체로 사용하고 있어, 
별도 설정이 없으면 Hibernate가 사용됩니다. 