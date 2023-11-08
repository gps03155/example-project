### Spring MVC
---

<br>

>### Spring Web MVC

<br>

_Servlet API를 기반으로 작성된 최초의 웹 프레임워크이며 Spring Framework에 포함_

__Spring MVC는 많은 다른 웹 프레임워크와 같이 프론트 컨트롤러 패턴을 중심으로 설계되어있으며 중앙처리 장치 Servlet(DispatcherServlet)는 요청 처리를 위한 공유 알고리즘을 제공하고 실제 작업을 구성 가능한 구성요소에 의해 수행 - 유연하고 다양한 workflow 지원__

#### DispatcherServlet

- Spring Framework가 제공하는 Servlet 클래스
- 사용자의 요청을 받음
- Dispatcher가 받은 요청은 HandlerMapping으로 넘어감

~~~
web.xml 설정

<servlet>
  <servlet-name>app</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value></param-value>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
  <servlet-name>app</servlet-name>
  <url-pattern>/app/*</url-pattern>
</servlet-mapping>
~~~

<br>

#### HandlerMapping

- 사용자의 요청을 처리할 Controller를 찾음 (Controller URL Mapping)
- 요청 url에 해당하는 Controller 정보를 저장하는 table을 가짐
- 클래스에 `@RequestMapping("/url")` Annotation을 명시하면 해단 URL에 대한 요청이 들어왔을 때 table에 젖아된 정보에 따라 해당 클래스 또는 메서드에 Mapping

<br>

#### ViewResolver

- Controller가 반환한 View Name(logical name)에 prefix, suffix를 적용하여 View Object(physical view files)를 반환
- view name : home, prefix : /WEB-INF/views/, suffix : .jsp는 `/WEB-INF/views/home.jsp`라는 위치의 View(JSP)에 Controller에게 받은 Model을 전달
- 해당 View에서 Model data를 이용하여 적절한 페이지를 만들어 사용자에게 보여줌

<br><br>

>### Spring MVC에서의 Model, View, Controller

<br>

#### Model

__Controller에서 View로 객체를 전달__

- 명명된 객체들의 집합
  - Key-Value 형식의 하나의 쌍(열)을 명명된 객체라 부름
  - 명명된 객체는 model attribute라 부름
  - 여러 개의 attribute가 모여 Table 형식을 이룸
- View에서 attribute의 Key 값을 통해 Value값 사용

<br>

__Model Implementations__

- Model을 표현하기 위해 여러 자료구조 사용 가능
- Controller 메서드에 input argument로 값을 넣어주면 Spring Framework가 자동으로 Model을 만들어주고 해당 Model의 주소값만 넘겨줌

~~~
Spring에서 제공하는 ModelMap 객체

@RequestMappig("/url")
pubilc String example(Model model){
  model.addAttribute("key", value);

  return "/WEB-INF/views/index.jsp";
}
~~~

<br><br>

#### Controller

- `@Controller`
  - bean으로 등록
  - 해당 클래스가 Controller로 사용됨을 Spring Framework에 알림
  - `@Component` : 구체화 (`@Controller`, `@Service`, `@Repository`)

- `@RequestMapping`
  - value : 해당 url로 요청이 들어오면 이 메서드가 수행
  - method : 요청 method를 명시
~~~
@Controller
@RequestMapping("/home") // 1) Class Level
public class HomeController{
  // HTTP GET : /home
  @RequestMapping(method=RequestMethod.GET) // 2) Handler Level
  public String getAllEmployees(Model model){
    // code ...
  }

  // HTTP POST : /home/employees
  @RequestMapping(value="employees", method=RequestMethod.POST)
  public String addEmployee(Employee employee){
    // code ...
  }
}
~~~

- Class Level Mapping
  - 모든 메서드에 적용되는 경우
  - `/home`으로 들어오는 모든 요청에 대한 처리를 해당 클래스에서 한다는 것을 의미

- Handler Level Mapping
  - 요청 url에 대해 해당 메서드에서 처리해야 되는 경우
  - `/home/employees` POST 요청에 대한 처리를 addEmployee()에서 한다는 것을 의미

- Model.addAttribute()
  - Business Logic의 처리 결과 값을 model attribute에 지정하면 Spring이 Model 객체를 만들어 해당 Model의 주소값을 넘겨줌
  - 하나의 요청 안에서만 Controller와 View가 Model을 공유

- `@RequestParam`
  - HTTP GET 요청에 대해 매칭되는 request parameter 값이 자동으로 들어감
  - ex) http://localhost:8080/login?username=scott&password=tiger

<br><br>

#### View

View를 생성하는 방법은 여러가지 존재 __(JSP 이외에도 Thymeleaf, Groovy, Freemarker 등 여러 Template Engine)__ 이 존재

- __JSP (Java Server Pages)__
  - Java EE에 종속적
  - SpringBoot에서는 공식적으로 jsp를 지원하지 않음
    - SpringBoot의 내장 Tomcat에 하드코딩 패턴 때문에 jar 형식으로는 webapp 내용을 가져올 수 없음
    - SpringBoot에서는 war가 아닌 jar로 사용할 때는 jsp를 사용할 수 없음

<br>

- __JSTL (JSP Standard Tag Library)__
  - 많은 JSP 애플리케이션의 공통적인 핵심 기능을 캡슐화하는 유용한 JSP 태그 모음
  - JSP 페이지를 작성할 때 유용하게 사용할 수 있는 여러가지 action과 함수가 포함된 라이브러리
  - 태그 확장 라이브러리
  - 자신만의 Custom Tag를 추가할 수 있는 기능 제공
  - 사용하는 이유?
    - JSP에 Java Code가 들어가는 것을 막기 위해 사용
    - Java Code (JSP Script) 대신 Tag를 사용하여 프로그래밍 할 수 있도록 하기 위해 도입
