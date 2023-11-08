### MyBatis
---

<br>

>### MyBatis란?

<br>

_객체지향 언어인 자바의 관계형 데이터 베이스 프로그래밍을 좀 더 쉽게 할 수 있게 도와주는 개발 프레임워크_

자바는 JDBC API를 제공해주지만 이런 JDBC를 이용하면 1개 클래스에 반복되는 코드가 존재, 한 파일에 JAVA 언어와 SQL 언어가 있어서 재사용성 등이 떨어지는 단점 존재

__MyBatis는 JDBC의 이러한 단점들을 개선했으며 개발자가 작성한 SQL 명령어와 자바 객체를 매핑해주는 기능을 제공하며 기존에 사용하던 SQL 명령어를 재사용__

___MyBatis는 JDBC보다 좀 더 편하게 사용하기 위해 개발됨___

<br>

### 특징

- 한두줄의 자바 코드로 DB 연동을 처리
- SQL 명령어를 자바 코드에서 분리하여 XML 파일에 따로 분리
- 오픈 소스이며 무로

특징 | 설명
--- | ---
간단하다 | 간단한 퍼시스턴스 프레임워크
생산성 | 62% 정도 줄어드는 코드와 간단한 설정
성능 | 구조적 강점 (데이터 접근 속도를 높여주는 Join매핑), 여러가지 방식의 데이터를 가져오기 전략 (가져오기 미루기, SQL 줄이기 기법)
관심사의 분리 | 설계 향상 (유지보수성), 리소스를 관리하여 계층화를 지원 (Connection, PreparedStatement,  ResultSet)
작업의 분배 | 팀을 세분화하는 것을 도와줌
SQL문 분리 | SQL문이 애플리케이션 소스코드로부터 완전 분리
이식성 | 어떤 프로그래밍 언어로도 구현 가능 (Java, C#, .NET, Ruby)

<br><br>

>### MyBatis의 구성

<br>

~~~
// pom.xml에 MyBatis 추가

<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.2.8</version>
</dependency>
~~~

<br>

### 환경 설정 파일

__MyBatis 전반에 걸친 세팅__

- Mapping 설정이 어디에 있는지
- DB에 어떻게 접속할건지
- 사용할 Model Class들에 대한 별명

<br>

### Mapping 설정 파일

__사용할 SQL문들에 대한 정의__

<br>

### Session 빌드 및 사용

- 실제 SQL문 실행
- 설정 파일을 통해 SqlSessionFactoryBuilder객체 생성
- SqlSessionFactoryBuilder를 이용해서 SqlSession Open
- SqlSession을 통해서 원하는 SQL구문의 id를 호출해서 사용
- SqlSession Close

<br><br>

>### 용어정리

<br>

- SqlSessionFactoryBuilder
  - 설정 파일을 읽어서 SqlSessionFactory객체 생성

- SqlSessionFactoryFactory
  - SqlSession을 만드는 역할
  (Dao는 Factory을 멤버로 유지하면서 필요할 떄 SqlSession을 Open해서 사용하고 다쓰면 SqlSession을 Close)

- SqlSession
  - sql 문을 실제 호출해주는 역할
  (필요할 때 open하고 close해줘야함)

<br><br>

>### #{} vs ${}

<br>

~~~
<select id="select_list" parameterType="HashMap" resultType="HashMap">
  select name, id
  from user
  where id = ${user_id}
        and pw = ${user_pw}
</select>
~~~

~~~
<select id="select_list" parameterType="HashMap" resultType="resultType">
  select name, id
  from user
  where id = #{user_id}
        and pw = #{user_pw}
</select>
~~~

두 쿼리의 차이점? ${}, #{}

<br>

### #{}

- 파라미터가 String 형태로 들어와 자동적으로 'paremeter' 형태
- #{user_id}의 경우 user_id의 값이 test라면 쿼리문에는 id = 'test'의 형태
- 쿼리 주입을 예방할 수 있어 보안 측면에서 유리

<br>

### ${}

- 파라미터가 바로 출력
- 해당 컬럼의 자료형에 맞추어 파라미터의 자료형 변경
- 쿼리 주입을 예방할 수 없어 보안 측면에서 불리하므로 사용자의 입력을 전달할 때는 사용하지 않는 편이 나음
- 테이블이나 컬럼명을 파라미터로 전달하고 싶을 때 사용 (#{}는 자동으로 ''가 붙어서 이 경우 사용할 수 없음)

~~~
<select id="select_list" parameterType="HashMap" resultType="HashMap">
  select ${column}
  from user
</select>
~~~
