### Interceptor
---

<br>

>### Interceptor란?

<br>

_controller에 들어오는 요청 HttpRequest와 controller가 응답하는 HttpResponse를 가로채는 역할_

__인터셉터(Interceptor)는 관리자만 접근할 수 있는 관리자 페이지에 접근하기 전에 관리자 인증을 하는 용도로 활용 - 기술 침투적인 HttpSession을 제거__

- Spring에서 HttpRequest오 HttpResponse를 Controller 앞과 뒤에서 가로채는 역할
- Servlet의 앞과 뒤에서 HttpRequest와 HttpResponse를 가로채는 필터와 유사
- Interceptor를 구현하기 위해서는 `HandlerInterceptor` 인터페이스를 구현해야함

<br><br>

>### Filter와 Interceptor의 차이

<br>

1. 호출 시점
  - Filter : DispatcherServlet이 실행되기 전
  - Interceptor : DispatcherServlet이 실행된 후

2. 설정 위치
  - Filter : web.xml
  - Interceptor : spring-servlet.xml

3. 구현 방식
  - Filter : web.xml에서 설정을 하면 구현 가능
  - Interceptor : 설정 + 메서드 구현

<br><br>

>### Interceptor 환경설정

<br>
__spring_servlet.xml에서 설정__

~~~
<!-- Interceptors -->
<mvc:interceptors>
  <mvc:interceptor>
    <mvc:mapping path="/**" />
    <bean class="com.example.interceptor.MyInterceptor" /> <!-- 패키지명 + 클래스명 -->
  </mvc:interceptor>
</mvc:interceptors>
~~~

<br><br>

>### Interceptor 구현

<br>

_HandlerInterceptor 인터페이스를 구현하는 방법과 HandlerInterceptorAdapter 클래스를 상속받는 방법 2가지 존재_

__상속을 받으면 메서드가 구현되어 있으므로 사용이 편하기 때문에 상속받는 방법을 주로 사용__

<br>

- `preHandle()` : 컨트롤러 들어가기 전에 실행
- `postHandle()` : 컨트롤러에 들어갔다가 나온 후 뷰로 보내지기 전에 실행
- `afterCompletion()` : 뷰까지 끝나고 난 후 실행

<br>

### HandlerInterceptor 인터페이스

~~~
public class MyInterceptor implements HandlerInterceptor {
  // Controller로 보내기 전에 처리하는 인터셉터
  // 반환이 false라면 controller로 요청을 안함
  // 매개변수 Object는 핸들러 정보를 의미
  // (RequestMapping, DefaultServletHandler)
  @Override
  public boolean preHandle(HttpServletRequest request,
                            HttpServletResponse resonse,
                            Object obj) {
    System.out.println("MyInterceptor : preHandle()");

    return false;      
  }

  // controller의 handler가 끝나면 처리
  @Override
  public void postHandle(HttpServletRequest request,
                         HttpServletResponse response,
                         Object obj,
                         ModelAndView mav) throws Exception{

  }

  // view까지 렌더링이 끝난후 처리
  @Override
  public void afterCompletion(HttpServletRequest request,
                              HttpServletResponse response,
                              Object obj,
                              Exception e) throws Exception {

  }
}
~~~

1. `preHandle()` 메서드는 컨트롤러가 호출되기 전에 실행
(매개변수 obj는 Dispatcher의 HandlerMapping이 찾아준 컨트롤러의 메서드를 참조할 수 있는 HandlerMethod 객체)

2. `postHandle()` 메서드는 컨트롤러가 실행된 후 호출

3. `afterCompletion`은 view에서 렌더링 후 최종 결과가 생성하는 일을 포함한 모든 일이 완료되었을 때 실행

__각 메서드의 반환값이 true이면 핸들러의 다음 체인이 실행되지만 false이면 중단하고 남은 인터셉터와 컨트롤러가 실행되지 않음__

<br><br>

### HandlerInterceptorAdapter 추상 클래스 상속

~~~
public class MyInterceptor extends HandlerInterceptorAdaptor {

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object obj) throws Exception {
    System.out.println("MyInterceptor : preHandle()");

    return true; // return false;
  }
}
~~~

__HandlerInterceptorAdapter 클래스를 상속받으면 postHandle(), afterCompletion() 메서드가 구현되어 있으므로 필요한 메서드만 오버라이딩하여 사용__

return true인 상태에서 `postHandle()`, `afterCompletion()`이 구현되어 있지 않으면 에러 발생 - return false 해야함

<br><br>

>### Filter vs Interceptor

<br>

_Filter와 Interceptor는 둘 다 컨트롤러 전에 작업을 처리하는 용도로 사용되지만 호출되는 시점이 다름_

- Filter : DispatcherServlet으로 요청이 가기 전에 실행
- Interceptor : Controller로 요청이 가기 전에 실행

<br>

### Filter

- DispatcherServlet 앞단에서 정보 처리
- J2EE 표준 스펙에 정의되어 있는 기능
- Filter 메서드 실행 시점
  - `init()` : 필터 인스턴스 초기화 시점
  - `doFilter()` : 전 / 후 처리
  - `destroy()` : 필터 인스턴스 종료 시

<br>

### Interceptor

- DispatcherServlet에서 Handler(Controller)로 가기 전에 정보 처리
- SpringFramework에서 자체적으로 제공하는 기능
- Interceptor 메서드 실행 시점
  - `preHandle()` : 컨트롤러 들어가기 전
  - `postHandle()` : 컨트롤러에 들어갔다가 나온 후 뷰로 보내지기 전
  - `afterCompletion()` : 뷰까지 끝나고 난 후

__주로 전역적으로 무엇인가를 처리(인코딩, 보안)해야하는 로직은 필터로 구현하고 디테일한 처리 (인증, 권한, 세션)는 인터셉터로 구현__
