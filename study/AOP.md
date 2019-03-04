### AOP
---

<br>

_Spring의 핵심 개념중 하나인 DI가 애플리케이션 모듈들 간의 결합도를 낮춰준다면 AOP는 애플리케이션 전체에 걸쳐 사용되는 기능을 재사용하도록 지원하는 것_

__AOP : 프로젝트 구조를 바라보는 관점을 바꿔보자__ (제 3자의 관점에서 바라보자)

>### AOP (Aspect-Oriented Programming)란?

<br>

__관점(관심) 지향 프로그래밍__

- 기존의 OOP에서 바라보던 관점을 다르게 하여 부가 기능적인 측면에서 보았을 떄 공통된 요소를 추출
- 가로(횡단) 영역의 공통된 부분을 잘라냈다고 하여 AOP를 __크로스 컷팅(Cross-Cutting)__ 이라고 부르기도 함

<br>

OOP | AOP
--- | ---
비즈니스 로직의 모듈화 | 인프라 혹은 부가기능의 모듈화
모듈화의 핵심 단위는 비즈니스 로직 | 각각의 모듈들의 주 목적 외에 필요한 부가적인 기능들
 | 대표적 예 : 로깅, 트랜잭션, 보안 등

 <br>

__AOP도 결국은 공통된 기능을 재사용하는 기법__

OOP에선 공통된 기능을 재사용하는 방법으로 상속이나 위임을 사용하지만 전체 애플리케이션에서 여기저기에 사용되는 부가기능들을 상속이나 위임으로 처리하기에는 깔끔하게 모듈화가 어려움 - AOP를 이용하여 해결

### AOP 장점

- 애플리케이션 전체에 흩어진 공통 기능이 하나의 장소에서 관리됨
- 다른 서비스 모듈들이 본인의 목적에만 충실하고 그 외 사항들은 신경쓰지 않아도 됨

<br><br>

>### AOP 용어

<br>

- __Target__
  - 부가기능을 부여할 대상
  - 핵심 기능을 담당하는 Service

<br>

- __Aspect__
  - 객체지향 모듈을 오브젝트라 부르는 것과 비슷하게 부가기능 모듈을 Aspect라 부름
  - __핵심 기능에 부가되어 의미를 갖는 특별한 모듈__
  - 부가될 기능을 정의한 Advice와 Advice를 어디에 적용할지를 결정하는 Point Cut을 함께 가짐
  - AOP(Aspect Oriented Programming)라는 뜻 자체가 애플리케이션의 핵심적인 기능에서 부가적인 기능을 분리해서 Aspect라는 독특한 모듈로 만들어서 설계하고 개발하는 방법을 뜻함

<br>

- __Advice__
  - 실질적으로 부가기능을 담은 구현체
  - 타겟 오브젝트에 종속되지 않기 때문에 순수하게 __부가기능에만 집중__ 가능
  - Aspect가 '무엇을', '언제' 할지를 정의

<br>

- __Point Cut__
  - 부가기능이 적용될 대상(메서드)를 선정하는 방법
  - __Advice를 적용할 JoinPoint를 선별하는 기능을 정의한 모듈__

<br>

- __JoinPoint__
  - Advice가 적용될 수 있는 위치
  - 다른 AOP 프레임워크와 달리 Spring에서는 __메서드 JoinPoint만 제공__
  - Spring Framework 내에서 JoinPoint라 하면 메소드를 가리킴

<br>

- __Proxy__
  - 타겟을 감싸서 타겟의 요청을 대신 받아주는 랩핑(Wrapping) 오브젝트
  - 클라이언트에서 타겟을 호출하게 되면 타겟이 아닌 타겟을 감싸고 있는 프록시가 호출되어 타겟 메서드 실행전에 선처리, 타겟 메서드 실행후, 후처리를 실행시키도록 구성

<br>

- __Introduction__
  - 타겟 클래스에 코드 변경없이 신규 메서드나 멤버변수를 추가하는 기능

<br>

- __Weaving__
  - 지정된 객체에 Aspect를 적용해서 새로운 프록시 객체를 생성하는 과정
  - 컴파일 타임, 클래스로드 타임, 런타임과 같은 시점에서 실행되지만 Spring AOP는 런타임에서 프록시 객체가 생성

~~~
Weaving Ex

A라는 객체에 트랜잭션 Aspect가 지정되어 있다면
A라는 객체가 실행되기 전 커넥션을 오픈하고
실행이 끝나면 커넥션을 종료하는 기능이 추가된 프록시 객체가 생성되고
이 프록시 객체가 앞으로 A객체가 호출되는 시점에 사용

이때의 프록시 객체가 생성되는 과정을 Weaving이라고 함
~~~

<br><br>

>### 사용법

<br>

~~~
@Aspect
@Component
public class MyAspect{
  @Before("execution(public ProductVo com.douzone.ProductService.fine(..))")
  public void beforeAdvice(){
    // code ...
  }

  @After("executuion(* *..*.ProductService.*(..))")
  public void afterAdvice(){
    // code ...
  }

  @AfterReturning("execution(* *..*.ProductService.*(..))")
  public void afterReturningAdvice(){
    // code ...
  }

  @AfterThrowing("execution(* *..*.ProductService.*(..))")
  public void afterThrowing(){
    // code ...
  }

  @Around("execution(* *..*.ProductService.*(..))")
  public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
    // before

    Object result = jpj.proceed();

    // after

    return result
  }
}
~~~

<br>

- `@Before`
Advice 타겟 메서드가 호출되기 전에 Advice 기능을 수행

- `@After`
타겟 메서드의 결과에 관계없이 (성공, 예외 관계없이) 타겟 메서드가 완료되면 Advice 기능을 수행

- `@AfterReturning` (정상적 반환 이후)
타겟 메서드가 성공적으로 결과값을 반환 후에 Advice 기능을 수행

- `@AfterThrowing` (에외 발생 이후)
타겟 메서드가 수행 중 예외를 던지게 되면 Advice 기능을 수행

- `@Around` (메서드 실행 전후)
  - Advice가 타겟 메서드를 감싸서 타겟 메서드 호출 전과 후에 Advice 기능을 수행
  - __반드시 proceed() 메서드가 호출되어야함__
  - proceed() 메서드는 타겟 메서드를 지칭하기 때문에 proceed 메서드를 실행시켜야만 타겟 메서드가 수행됨

~~~
@Around("포인트컷 표현식")
public void Advice메서드(){
  // code ...
}
~~~

<br><br>

>### PointCut 표현식

<br>

__Advice의 value로 들어간 문자열을 포인트컷 표현식이라 부름__

포인트컷 표현식은 2가지로 나눠지는데 `execution`을 __지정자__ 라고 부르며
`(* com.example.BoardService.find(..))`는 __타겟 명세__ 라고 함

***다양한 지정자가 있지만 실제로 execution과 @annotation을 주로 사용***

- `args()`
  - 메서드의 인자가 타겟 명세에 포함된 타입일 경우
  - arg(java.io.Serializable) : 하나의 파라미터를 갖고 그 인자가 Serializable 타입인 모든 메서드

<br>

- `@args()`
  - 메서드의 인자가 타겟 명세에 포함된 Annotation 타입을 갖는 경우
  - @args(com.example.session.User) : 하나의 파라미터를 갖고 그 인자의 타입이 @User Annotation을 갖는 모든 메서드 (@User User user 같이 인자 선언된 메서드)

<br>

- `execution()`
  - 접근 제한자, 리턴타입, 인자타입, 클래스/인터페이스, 메서드명, 파라미터타입, 예외타입 등을 전부 조합가능한 가장 세심한 지정자
  - 풀 패키지에 메서드명까지 직접 지정할 수도 있으며 아래와 같이 특정 타입내의 모든 메서드 지정 가능
  - execution(* com.example.AccountService.* (..) : AccountService 인터페이스의 모든 메서드

<br>

- `within()`
  - execution 지정자에서 클래스/인터페이스까지만 적용된 경우
  - 클래스 혹은 인터페이스 단위까지만 범위 지정 가능
  - within(com.example.service.* ) : service 패키지 아래의 클래스와 인터페이스가 가진 모든 메서드
  - within(com.example.service..) : service 아래의 모든 하위패키지까지 포함한 클래스와 인터페이스가 가진 메서드

<br>

- `@within()`
  - 주어진 Annotation을 사용하는 타입으로 선언된 메서드

<br>

- `this()`
  - 타겟 메서드가 지정된 빈 타입의 인스턴스인 경우

<br>

- `target()`
  - this와 유사하지만 빈 타입이 아닌 타입의 인스턴스인 경우

<br>

- `@target()`
  - 타겟 메서드를 실행하는 객체의 클래스가 타겟 명세에 지정된 타입의 Annotation이 있는 경우

<br>

- `@annotaion`
  - 타겟 메서드에 특정 Annotaion이 지정된 경우
  - @annotation(org.springframework.transaction.annotation.Transactional) : Transactional Annotation이 지정된 메서드 전부
