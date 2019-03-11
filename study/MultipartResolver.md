### MultipartResolver
---

<br>

>### MultipartResolver

### 환경 설정

- __pom.xml__
pom.xml 파일에 라이브러리 추가

~~~
<dependency>
  <groupId>commons-fileupload</groupId>
  <artifactId>commons-fileupload</artifactId>
  <version>1.3.1</version>
</dependency>
<dependency>
  <groupId>commons-io</groupId>
  <artifactId>commons-io</artifactId>
  <version>2.4</version>
</dependency>
~~~

<br>

- __spring-servlet.xml__
view단에서 `multipart/form-data` 방식으로 서버에 전송되는 데이터를 스프링 MVC의 `multipartResolver`로 처리 가능

~~~
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
  <property name="maxUpLoadSize" value="52428800" /> <!-- byte -->
  <property name="defaultEncoding" value="UTF-8" />
</bean>
~~~

- 업로드할 파일 위치 지정
~~~
<bean id="uploadPath" class="java.lang.String">
  <constructor-arg value="C://uploads" />
</bean>
~~~

- 파일 물리 주소와 가상 주소 매핑
실제 파일이 저장되어 있는 위치와 애플리케이션 상의 위치를 일치시키는 작업 필요

~~~
<!-- location : 물리적 주소 , mapping : 가상 주소 -->
<mvc:resources location="/file:/upload" mapping="/upload/*"/>
~~~

실제 파일이 저장된 서버 상의 위치(물리 주소)와 애플리케이션에서 보여주고자 하는 파일 경로(가상 주소)가 일치하지 않을 경우 이미지가 나타나지 않음


<br>

- __web.xml (한글 파일 인코딩)__
한글 파일이 업로드될때 파일명이 깨지는 것을 해결하기 위해 filter 추가

~~~
<filter>
  <filter-name>encodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
  <init-param>
    <param-name>forceEncoding</param-name>
    <param-value>true</param-value>
  </init-param>
</filter>

<filter-mapping>
  <filter-name>encodingFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
~~~

<br>

- __form.jsp__

~~~
<form action="/user/join" method="POST" enctype="multipart/form-data">
  <input type="file" name="file" />
  <input type="submit" value="join" />
</form>
~~~

<br>

- __Controller__

~~~
@RequestMapping("/user/join")
public String upload(@RequestParam(value="file") MultipartFile multipartFile){
  // file.getName();
  // file.getOriginalFilename();
  // file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
  // file.getSize();
  // file.getContentType();
}
~~~

<br><br>

>### 서버에 파일을 저장할 때 고려해야할 사항

- __파일 업로드 방식 결정하기__
Post 방식으로 전송할지 ajax 방식으로 전송할지 결정해야함

- __파일 이름 중복 문제__
DB에 파일을 저장할 수도 있지만 일반적으로 파일 시스템에 파일을 저장하게 됨
(업로드 되는 파일의 이름의 중복을 해결할 방법 필요 : UUID로 해결)

- __파일 저장경로에 대한 문제__
  - Windows나 Linux 등 운영체제에서 폴더 내의 파일 개수가 너무 많아지면 속도저하 문제 발생
  - Windows의 파일 시스템의 경우 폴더 내 최대 파일 개수의 제한 존재

이와 같은 문제를 해결하기 위해 보통 파일이 업로드 되는 시점별로 폴더 관리
즉 업로드 할 때 파일을 저장할 폴더의 유무에 따라 폴더 생성로직이 필요함

- __이미지 파일의 경우 썸네일(Thumbnail) 생성__
  - 이미지 파일인 경우 저장된 파일을 다시 화면에 보여줄 때 보통 그 이미지 파일의 썸네일 파일을 보여주게 됨
  - 이미지 파일이 서버에 저장될 때는 추가적으로 그 이미지 파일의 썸네일 파일을 생성해주어야함
  - `imgscalr-lib` : 이미지 썸네일 생성 라이브러리

1. pom.XML
common-upload
common-io

2. spring-servlet.XML
MultipartResolver

3. upload.jsp
<form method = POST enctype = multipart/form-data>

4. 구현

5. ResourceMapping 작업
