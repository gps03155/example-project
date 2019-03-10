### Form Validation
---

<br>

Validation이란 어떤 데이터의 값이 유효한지, 타당한지 확인하는 것을 의미

__UI에서 Javascript로 Form을 체크하는 것은 UX 측면에서 사용자에게 편의를 주기 위한 것__ 이기 때문에 UI단에서 유효성 검사를 하는 것은 보안적인 측면에서는 좋지 않음

___보안적인 측면에서 유효성 검사란 올바르지 않은 데이터가 서버로 전송되거나 DB에 저장되지 않도록 하는 것___



>### Form Validation

Spring은 Annotation 기반으로 validation을 제공

- `@AssertFalse` : 거짓인가?
- `@Max` : 지정 값 이하인가?
- `@AssertTrue` : 참인가?
- `@Min` : 지정 값 이상인가?
- `@DecimalMax` : 지정 값 이하 실수인가?
- `@NotNull` : Null이 아닌가?
- `@DecimalMin` : 지정 값 이상 실수인가?
- `@Null` : Null인가?
- `@Digits (integer = , fraction = )` : 대상 수가 지정된 정수, 소수 자리 수 이내인가?
- `@Pattern (regex = , flag = )` : 정규식을 만족하는가?
- `@Future` : 미래 날짜인가?
- `@Past` : 과거 날짜인가?
- `@Size(min = , max = )` : 문자열 ,배열 등의 크기가 지정 크기를 만족하는가?

<br><br>

>### Hibernate Validation

- `@NotEmpty` : Empty 값이 아닌가?
- `@Email` : 이메일 형식
- `@URL` : URL 형식
- `@Length (min = , max = )` : 문자열 길이 min과 max 사이인가?
- `@Range (min = , max = )` : 숫자 범위 체크

### 환경 설정

- pom.xml

~~~
<!-- validation -->
<dependency>
  <groupId>javax.validation</groupId>
  <artifactId>validation-api</artifactId>
  <version>1.0.0.GA</version>
</dependency>
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-validator</artifactId>
  <version>4.2.0.Final</version>
</dependency>
~~~

### 유효성 검사
__파라미터에서 `@Valid` 어노테이션을 붙이는 유효성 검사에 대한 선언은 컨트롤러에서 하고 유효성 검사를 체크하는 근거는 Vo 클래스에 작성__

- 유효성 검사가 필요한 객체에 대해 `@Valid` 어노테이션 추가
- `BindingResult` 객체는 검증 결과에 대한 결과 정보를 담고 있음

~~~
// UserController.java

@RequestMapping(value="/user/join", method=RequestMethod.POST)
public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model){
  if(result.hasErrors()){
    model.addAllAttribute(result.getAllErrors());

    return "/user/join";
  }

  userService.join(vo);

  return "redirect:/user/joinsuccess";
}
~~~
검증 결과 정보 BindingResult는 DispatcherServlet이 JSP에 넘겨줌
즉 __컨트롤러에서 뷰 이름을 반환하면 에러 내용을 바인딩해서 jsp에 넘겨줄 테니 값을 사용자에게 보여주라는 의미의 forwarding으로 화면 전환__

~~~
// UserVo.java

public class UserVo {
  private Integer no;

  @NotEmpty
  @Length(min=2, max=5)
  private String name;

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=])(?=\\S+$).{8,}")
  private String pwd;

  @NotEmpty
  private String gender;

  private String regDate;

  // getter, setter ...
}
~~~

<br>

### JSP에 메시지 출력
__컨트롤러에서 Validation을 선언하고 Vo 객체에서 검증 조건을 작성하면 유효한 데이터만 받을 수 있음__

~~~
// form.jsp

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<form>
  <input name="name" type="text" value="">
  <spring:hasBindErrors name="userVo">
    <c:if test="${errors.hasFieldErrors('name')}">
      <strong>${errors.getFieldError('name').defaultMessage}</strong>
    </c:if>
  </spring:hasBindErrors>
</form>
~~~

- 유효성 검사가 실패했을 경우 에러 메시지를 출력하도록 작성
- 서버에서 전달해준 BindingResult 객체를 사용하기 위해서는 태그 라이브러리를 추가해야함
- `defaultMessage`는 유효성 검사가 실패했을 경우 검증 조건을 알려주는 기본 메시지

<br>

### 에러 메시지 커스터마이징

~~~
// spring-servlet.xml

<!-- MessageSource -->
<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
  <property name="defaultEncoding">
    <value>utf-8</value>
  </property>

  <property name="basenames">
    <list>
      <value>message/messages_ko</value> <!-- 다국어 가능 -->
    </list>
  </property>
</bean>
~~~

메시지를 커스터마이징 하기 위해서는 bean을 생성해야 하며 한국어로 바꿀 것이기 때문에 `messages/messages_ko`를 value로 작성

~~~
// message_ko.properties

NotEmpty.userVo.name = ......
~~~

__NotEmpty라는 검증 조건.검증 조건이 있는 객체 이름.변수명 = 에러 메시지__ 양식으로 출력될 에러 메시지를 입력하면 위와 같이 UTF-8로 인코딩 되어 메시지 등록 가능

- 메시지를 어떤 내용으로 바꿀 것인지에 대한 설정 파일 생성
- `/src/main/resources/messages` 폴더를 생성한 후 `messages_ko.properties` 이름으로 파일 생성
- `spring-servlet.xml` 에서 정의했으므로 폴더 이름과 파일 이름을 똑같이 작성해야함

~~~
// form.jsp

<form>
  <input type="text" name="name" value="">
  <spring:hasBindErrors name="userVo">
    <c:if test="${errors.hasFieldErrors('name')}">
      <spring:message
              code="${errors.getFieldError('id').codes[0]}"
              text="${errors.getFieldError('id').defaultMessage}"/>
    </c:if>
</form>
~~~

커스터마이징한 에러 메시지를 띄울 수 있지만 하나의 입력값에 대해서 유효성 검사에 실패하면 모든 입력 값이 날아가게 됨

<br>

### Form 양식 유지

__유효성 검증에 실패해도 사용자가 입력했던 내용을 그대로 유지__

~~~
// form.jsp

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form
        modelAttribute="userVo"
        method="POST"
        action="${pageContext.request.contextPath}/user/join">

    <form:input path="name"/>
    <form:errors path="name" />

    <form:input path="email"/>
    <form:errors path="email"/>

    <form:password path="pwd"/>
    <form:errors path="errors"/>

    <fieldset>
      <legend>gender</legend>
      <label>female</label> <form:radiobutton path="gender" value="female"/>
      <label>male</label> <form:radiobutton path="gender" value="male" />
    </fieldset>

    <input type="submit" value="join">
</form:form>
~~~

- defaultMessage를 출력하는 `spring-message` 태그를 사용했을 때보다 form 태그를 사용하면 에러 메시지를 출력하기 위한 코드가 상당히 짧아지고 가독성도 좋아짐
- __유효성 검사에 실패했을 때 기존에 작성했던 입력 값은 그대로 남아 있게 되고 커스터마이징한 에러 메시지가 출력__

>### 참고

[spring validation] (https://seonhyungjo.github.io/Spring-FormTag&Validation/)
