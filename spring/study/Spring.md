### Spring Framework
---

<br>

>### Framework란?

<br>

_소프트웨어의 구체적인 부분에 해당하는 섥와 구현을 재사용이 가능하게끔 일련의 협업화된 형태로 클래스들을 제공하는 것_

__Framework = Design Pattern + Class library__

#### Framework는 구조 품질을 보장

- 구체적이며 확장 가능한 기반 코드를 가짐
- 소프트웨어의 구조 및 기반이 되는 클래스 제공
- 설계자가 의도하는 여러 디자인  패턴의 집합으로 구성

#### Framework는 반제품

- Application의 틀과 구조 결정
- Application 코드의 상당 부분을 제공
- Application 코드는 Framework에 설계되어 있는 제어 흐름에 따라 동작
- Framework 코드가 그 위에 개발된 개발자의 User 코드를 호출하고 제어
- 개발자는 Application의 핵심 로직에만 집중

#### Framework 장점

- 생산성
  - 비즈니스 로직에만 집중할 수 있어 생산성 증대

- 코드 품질
  - 코드의 재사용 및 유지보수 용이
  - 확장성을 가진 코드 설계 가능

~~~
Framework vs Library

1. Framework
   소프트웨어의 구체적인 부분에 해당하는 설계와 구현을 재사용이 가능하게끔 일련의
   협업화된 형태로 클래스들을 제공하는 것
   (기본적으로 구성하고 있는 뼈대)

2. Library
   자주 사용되는 로직을 재사용하기 편리하도록 잘 정리한 일련의 코드들의 집합
~~~

<br><br>

>### Spring이란?

<br>

_Java Enterprise 개발을 편하게 해주는 오픈소스 경량급 애플리케이션 프레임워크_

- JAVA 플랫폼을 위한 오픈소스(Open Source) 어플리케이션 프레임워크
- 자바 개발을 위한 프레임워크로 종속 객체를 생성해주고 조립해주는 도구
- POJO(Plain Old Java Object) BEAN CONTAINNER

#### POJO vs EJB

- __POJO (Plain Old Java Object)__
상속, 인터페이스가 필요없는 아주 단순하고 가벼운 객체 (원하는 비즈니스 로직만 넣을 수 있도록 도와줌)

- __EJB (Enterprise Java Beans)__
기업 환경의 시스템을 구현하기 위한 서버 측 컴포넌트 모델
(EJB는 애플리케이션의 업무 로직을 가지고 있는 서버 애플리케이션으로 특정 환경에 종속적이고 무거움)

<br><br>

>### Spring Framework 특징

- 크기와 부하의 측면에서 경량
- 제어 역행(IOC)을 통하여 어플리케이션의 느슨한 결합을 도모
- 관점지향(AOP) 프로그래밍을 위한 풍부한 지원을 함
- 어플리케이션 객체의 생명주기와 설정을 포함하고 관리한다는 점에서 일종의 컨테이너라고 할 수 있음
- 간단한 컴포넌트로 복잡한 어플리케이션을 구성하고 설정할 수 있음

<br>

- __POJO를 이용한 가볍고(light weight) 비침투적(non-invasive) 개발__
- __DI와 인터페이스 지향을 통한 느슨한 결합도(loose coupling)__
- __Aspect와 공통 규약을 통한 선언적(declarative) 프로그래밍__
- __Aspect와 템플릿(template)을 통한 반복적이고 상투적인(boilerplate) 코드 제거__

<br>

#### POJO (Plan Old Java Object)

특정 인터페이스를 직접 구현하거나 상속받을 필요가 없어 기존 라이브러리를 지원하기가 용이하고 객체가 가벼움

#### 관점 지향 프로그래밍 (Aspect Oriented Programming, AOP)

로깅, 트랜잭션, 보안 등 여러 모듈에서 공통적으로 사용하는 기능을 분리하여 관리

#### 의존성 주입 (Dependency Injection, DI)

- 프로그래밍에서 구성요소 간의 의존관계가 소스코드 내부가 아닌 외부의 설정 파일을 통해 정의되는 방식

- 코드 재사용을 높여 소스코드를 다양한 곳에 사용할 수 있으며 __모듈간의 결합도를 낮춤__

#### 제어 반전 (Inversion of Control, IC)

- 외부 라이브러리 코드가 개발자의 코드를 호출
- 제어권이 프레임워크에 있어 필요에 따라 스프링 프레임워크가 사용자의 코드 호출

#### 생명주기

스프링 프레임워크는 Java 객체의 생성, 소멸을 직접 관리하며 필요한 객체만 사용할 수 있음

#### 다양한 서비스

myBatis와 같은 데이터베이스 처리 라이브러리나 tiles 같은 유용한 인터페이스 제공

~~~
1. POJO (Plain Object Java Object)
   기본적으로 사용자가 만들어낸 객체를 의미
   스프링은 사용자가 만들 객체들에 대해서도 LifeCycle 관리를 위임해서 수행하며
   이들의 조합으로 어플리케이션을 만들 수 있음

2. AOP (Aspect Oriented Programming)
   관점지향형 프로그램으로 사용자들이 트랜잭션 처리/로깅/예외 처리들의 로직을 공통으로
   처리할 수 있는 기능을 지원함
   비즈니스로직과 기타 공통 모듈이 섞이는 것을 방지하여 비즈니스 로직에 집중할 수 있도록 함

3. PSA (Portable Service Abstraction)
   스프링은 다른 여러 모듈을 사용함에 있어서 별도의 추상화 레이어 제공
   (JPA를 사용할 때 Spring JPA를 사용하여 추상화하므로 실제 구현에 있어서 Hibernate나 EclipseLink를 사용하나 사용자는 모듈의 의존없이 프로그램에 집중할 수 있음)
~~~

<br><br>

>### Spring 구성

<br>

__스프링 프레임워크는 총 6개의 모듈로 구성__

#### Core

제어반전(IOC)과 의존성 주입(DI) 기능 제공

#### DAO

JDBC 추상 계층 제공

#### ORM

JPA, myBatis, Hibernate와 같은 ORM API들과 통합할 수 있는 기능 제공

#### AOP

스프링 프레임춰크에서 제공하는 AOP 패키지 제공

#### Web

Spring Web MVC, Strcts, WebWork 등 웹 어플리케이션 구현에 도움되는 기능 제공
