### IOC / DI
---

<br>

프레임워크 기반의 개발에서는 프레임워크 자신이 흐름을 제어하는 주체가 되어 필요할때마다 애플리케이션 코드를 호출하여 사용

프레임워크에서 이 제어권을 가지는 것이 __컨테이너(Container)__ 이며 객체에 대한 제어권이 개발자로부터 컨테이너에게 넘어가면서 객체의 생성부터 생명주기 관리까지의 모든 것을 컨테이너가 맡아서 하게 됨

__일반적인 제어권의 흐름이 바뀌었다고 하여 IoC(Inversion of Control : 제어의 역전) 이라고 함__

___Spring Framework의 가장 중요한 특징은 객체의 생성과 의존관계를 컨테이너가 자동으로 관리한다는 점인데 이것이 바로 Spring IOC의 핵심원리___

__Spring은 IoC를 Dependency Lookup, Dependency Injection의 2가지 형태로 지원__

Spring 프레임워크에서 지원하는 IoC 컨테이너는 흔히 개발하고 사용해왔던 일반 __POJO(Plain Old Java Object)__ 클래스들이 지금까지 EJB를 통하여 실행했던 많은 기능들을 서비스 가능하도록 지원

<br>

>### IoC Container

<br>

### Dependency Lookup

__실제 애플리케이션 개발 과정에서는 사용하지 않음__

- 컨테이너가 애플리케이션 운용에 필요한 객체 생성
- 클라이언트는 컨테이너가 생성한 객체를 검색(Lookup)하여 사용하는 방식

<br>

### Dependency Injection

__컨테이너가 직접 객체들 사이에 의존 관계를 처리__

- 객체 사이의 의존 관계를 스프링 설정 파일에 등록된 정보(<bean>)를 바탕으로 컨테이너가 자동으로 처리
- 의존성 설정을 바꾸고 싶을 때 프로그램 코드를 수정하지 않고 스프링 설정 파일 수정만으로 변경사항을 적용할 수 있어 유지보수 향상
- `Setter Injection`
  - setter 메서드를 기반으로 사용
- `Constructor Injection`
  - 생성자를 기반으로 사용

<br><br>

>### IoC 용어 정리

<br>

- `bean` : __Spring에서 제어권을 가지고 직접 만들어 관계를 부여하는 오브젝트__
  - Java Bean, EJB의 Bean과 비슷한 오브젝트 단위의 애플리케이션 컴포넌트
  - Spring을 사용하는 애플리케이션에서 만들어지는 모든 오브젝트가 bean은 아님
  - Spring의 bean은 스프링 컨테이너가 생성하고 관계 설정, 사용을 제어해주는 오브젝트를 말함

<br>

- `bean factory` : __Spring의 IoC를 담당하는 핵심 컨테이너__
  - Bean을 등록/생성/조회/반환/관리
  - 보통 bean factory를 바로 사용하지 않고 이를 확장한 application context를 이용
  - Bean Factory는 bean factory가 구현하는 interface (getBean() 등의 메서드 정의)

<br>

- `application context` : __bean factory를 확장한 IoC 컨테이너__
  - Bean의 등록/생성/조회/반환/관리 기능은 bean factory와 같지만 추가적으로 spring의 각종 부가 서비스 제공
  - ApplicationContext는 application context가 구현해야 하는 interface이며 BeanFactory를 상속

<br>

- `configuration metadata` : __application context 혹은 bean factory가 IoC를 적용하기 위해 사용하는 메타정보__
  - Spring의 설정 정보는 컨테이너에 어떤 기능을 세팅하거나 조정하는 경우에도 사용하지만 주로 bean을 생성/구성하는 용도로 사용

<br>

- `container (IoC container)` : __IoC 방식으로 bean을 관리한다는 의미에서 bean factory나 application context를 가리킴__
  - application context는 그 자체로 ApplicationContext 인터페이스를 구현한 오브젝트를 말하기도 함
  - 하나의 애플리케이션에 보통 여러개의 ApplicationContext 객체가 만들어지며 이를 통해 spring container라고 부를 수 있음

<br><br>

>### DI (Dependency Injection : 의존 관계의 주입)

<br>

_Object 레퍼런스를 외부로부터 제공(주입)받고 이를 통하여 타 Object와 다이나믹하게 의존 관계가 만들어짐_  

__IoC는 직관적이지 못하기 때문에 DI(Dependency Injection)라고도 부름__

___어떤 오브젝트의 프로퍼티(인스턴스 변수)에 오브젝트가 이용할 오브젝트를 설정한다는 의미___

__클래스에서 new 연산자가 사라짐으로써 개발자가 팩토리 메서드 같은 디자인 패턴을 구사하지 않아도 DI 컨테이너가 건내주는 인스턴스를 인터페이스로 받아서 인터페이스 기반의 컴포넌화 구현 가능__

- 오브젝트를 생성하고 오브젝트끼리의 관계를 생성해 소프트웨어의 부품화 및 설계를 가능하게 함
- DI를 이용하면 인터페이스 기반의 컴포넌트를 쉽게 구현할 수 있음

<br>

- 의존 관계란?
  - 누가 누구에게 의존하는 관계에 있는가
  - 의존 관계에는 방향성이 존재
  - 의존하지 않는다는 말은 B는 A의 변화에 영향을 받지 않는다는 뜻

### 의존성 관계

- 객체와 객체의 결합 관계
- 하나의 객체에서 다른 객체의 변수나 메서드를 이용해야 한다면 이용하려는 객체에 대한 객체 생성과 생성된 객체의 레퍼런스 정보가 필요

<br>

- __XML로 작성된 Bean 정의 파일을 이용한 DI__
- __Annotation을 이용한 DI__
- __JavaConfig에 의한 DI__

<br><br>

>### @Autowired와 @Component

<br>

__인스턴스 변수 앞에 `@Autowired`를 붙이면 DI 컨테이너가 그 인스턴스 변수의 타입에 대입할 수 있는 클래스를 `@Component`가 붙은 클래스 중에서 찾아내 인스턴스를 인젝션 함__

만약 `@Component`가 붙은 클래스가 여러 개 있어도 형이 다르면 `@Autowired`가 붙은 인스턴스 변수에 인젝션되지 않음 - __타입을 보고 인젝션하는 방법을 `byType`__ 이라고 함

### @Autowired

__인젝션 할 수 있는 클래스의 형은 반드시 하나로 해야함__
인스턴스 변수 앞에 붙이는 것 외에도 메서드, 생성자 앞에도 붙일 수 있음

~~~
@Controller
public class TestClass{
  @Autowired
  private UserService userService; // UserService 객체 주입
}
~~~

<br>

### 확장된 @Component

__클래스가 어느 레이어에 배치될지 고려해서 배치될 레이어에 있는 `@Component` 확장 Annotation을 사용하는 것이 좋음__

- `@Controller` : 프레젠테이션층 스프링 MVC용 Annotation
- `@Service` : 비즈니스 로직층 Service용 Annotation (`@Component`와 동일)
- `@Repository` : 데이터 액세스층의 DAO용 Annotation
- `@Configuration` : Bean 정의를 자바 프로그램에서 실행하는 JavaConfig용 Annotation

`@Component`와 함께 사용하는 어노테이션의 하나로 `@Scope`가 있으며 `@Scope` 뒤에 Value 속성을 지정하면 인스턴스화와 소멸을 제어할 수 있음 (`@Scope`를 생략하면 해당 클래스는 싱글톤이 됨)

value 속성의 값은 직접 문자열로 넣어도 되지만 상수가 존재하기 때문에 상수를 사용하는것이 좋음

~~~
@Component
@Scope("singleton")
public class ProductDaoImple implements ProductDao {
  // code ...
}
~~~

- singleton : 인스턴스를 싱글턴으로 함
  - BeanDefinition.SCOPE_SINGLETON

- prototype : 이용할 떄마다 인스턴스화함
  - BeanDefinition.SCOPE_PROTOTYPE

- request : Servlet API의 request Scope인 동안만 인스턴스가 생존
  - WebApplicationContext.SCOPE_REQUEST

- session : Servlet API의 session Scope인 동안만 인스턴스가 생존
  - WebApplicationContext.SCOPE_SESSION

- application : Servlet API의 application Scope 동안만 인스턴스가 생존
  - WebApplicationContext.SCOPE_APPLICATION

<br><br>

>### 생명주기 관리

<br>

Spring DI 컨테이너에는 인스턴스의 생성과 소멸 타이밍에 호출되는 메서드를 설정할 수 있는 `@PostConstruct`와 `@PreDestroy`라는 2개의 어노테이션 존재

- `@PostConstruct`
  - 초기 처리를 하는 메서드 선언 (메서드 이름은 임의로 지정 가능)
  - 메서드 인수 없이 반환형은 void로 해야함

<br>

- `@PreDestroy`
  - 종료 처리를 하는 메서드 선언 (메서드 이름은 임의로 지정 가능)
  - 메서드 인수 없이 반환형은 void로 해야함

`@PostConstruct`는 DI 컨테이너에 의해 인스턴스 변수에 무언가 인젝션된 다음에 호출되므로 인젝션된 값으로 초기 처리를 할 때 사용 (생성자에서도 초기 처리 가능)

`@PreDestroy`는 소멸자가 없는 자바에서 종료 처리를 하기 위해 사용

<br><br>

>### 설정 방식 - XML 설정 / Java 설정

<br>

_각각의 장점이 서로 다르기 때문에 한가지 방식만 사용하는 것 보다는 상황에 따라 알맞게 방식을 조합해서 사용하는 것이 좋음_

<br>

### XML 설정 방식

- 설정 정보를 변경할 때는 XML만 변경하면 됨
- 많은 프레임워크, 라이브러리가 XML 스키마를 이용한 설정의 편리함 지원
- XML 작성 과정이 다소 번거로움
- 코드를 실행해야 설정 정보 오류 확인 가능

<br>

### Java 설정 방식

- 컴파일러의 도움을 받기 때문에 오타 등의 설정 정보 오류 미리 확인 가능
- 자바 코드이기 때문에 IDE에서 제공하는 코드 자동 완성 기능 사용 가능
- 설정 정보를 변경하려면 자바 코드를 재컴파일 해야함
- XML 스키마 기반의 설정을 지원하는 프레임워크, 라이브러리 중 아직 자바 기반의 편리한 설정을 지원하지 않는 경우 존재
