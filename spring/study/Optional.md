### Optional
---

<br>

>### NullPointException

- 런타임에 NullPointException 예외 발생
- NullPointException 방지를 위해 들어간 null 체크 로직 때문에 코드 가독성과 유지 보수성이 떨어짐

<br><br>

__자바가 "존재하지 않는 값"을 표현하기 위해서 null을 사용하였다면 함수형 언어들은 "존재할지 안할지 모르는 값"을 표현할 수 있는 별개의 타입을 가지고 있음__

존재할지 안할지도 모르는 값을 제어할 수 있는 여러가지 API를 제공하기 때문에 개발자들은 해당 API를 통해서 간접적으로 그 값에 접근하게 됨

_Java8은 함수형 언어의 접근 방식에서 영감을 받아 `java.util.Optional<T>`라는 새로운 클래스를 도입_

<br><br>

>### Optional

__`Optional`은 "존재할수도 있지만 안할수도 있는 객체" 즉, "null이 될 수도 있는 객체"를 감싸고 있는 일종의 래퍼 클래스__

- 원소가 없거나 최대 하나 밖에 없는 `Collection`이나 `Stream`으로 생각해도 무방
- 직접 다루기에 위험하고 까다로운 null을 담을 수 있는 특수한 그릇

<br>

### Optional의 효과

- NullPointException을 유발할 수 있는 null을 직접 다루지 않아도 됨
- 수고롭게 null 체크를 직접하지 않아도 됨
- 명시적으로 해당 변수가 null일 수도 있다는 가능성을 표현할 수 있음 (불필요한 방어 로직 줄이기)

<br><br>

>### Optional 사용법

### Optional 변수 선언

~~~
Optional<String> str; // String 타입의 객체를 감쌀 수 있는 Optional 변수
~~~

변수명은 클래스 이름을 사용하기도 하지만 "opt"와 같은 접두어를 붙여서 Optional 타입의 변수라는 것을 명확히 나타내기도 함

### Optional 객체 생성

Optional 클래스는 간편하게 객체 생성을 할 수 있도록 3가지 정적 팩토리 메소드 제공

- `Optional.empty()`

__null을 담고 있는 비어있는 Optional 객체를 얻음__
(비어있는 객체는 Optional 내부적으로 미리 생성해놓은 싱글턴 인스턴스)

~~~
Optional<Object> optObject = Optional.empty();
~~~

<br>

- `Optional.of(value)`

__null이 아닌 객체를 담고 있는 Optional 객체 생성__

~~~
Optional<Object> optObject = Optional.of(tmpObject);
~~~

null이 넘어올 경우 NullPointException을 던지기 때문에 주의해서 사용해야함

<br>

- `Optional.ofNullable(value)`

__null인지 아닌지 확신할 수 없는 객체를 담고 있는 Optional 객체 생성__

~~~
Optional<Object> optObject = Optional.ofNullalbe(tmpObject);
Optional<Object> optObject2 = Optional.ofNullable(null);
~~~

null이 넘어올 경우 NullPointException을 던지지 않고 `Optional.empty()`와 동일하게 비어있는 Optional 객체 생성
(해당 객체가 null인지 아닌지 자신이 없는 상황에서 사용해야함)

<br>

### Optional이 담고 있는 객체 접근

Optional 클래스는 담고 있는 객체를 꺼내오기 위해서 다양한 인스턴스 메소드 제공
(Optional이 담고 있는 객체가 존재할 경우 동일하게 해당 값 반환)

Optional이 비어있는 경우 (null인 경우) 다르게 동작

- `get()`
비어있는 Optional 객체에 대해 `NoSuchElementException` 발생

- `orElse(T other)`
비어있는 Optional 객체에 대해서 넘어온 인자 반환

- `orElseGet(Supplier<? extends T> other)`
비어있는 Optional 객체에 대해서 넘어온 함수형 인자를 통해 생성된 객체 반환
(비어있는 경우에만 함수가 호출되기 때문에 `orElse(T other)` 대비 성능상 이점 기대 가능)

- `orElseThrow(Supplier<? extends X> exceptionSupplier)`
비어있는 Optional 객체에 대해서 넘어온 함수형 인자를 통해 생성된 예외 던짐

<br><br>

>### Controller에서 Optional 사용

~~~
@RequestMapping({"/simple", "/simple/{game}"})
public void example(@PathVariable Optional<String> game){
  if(game.isPresent()){
    // game.get()
    // url path = "/simple/{game}"
  }
  else {
    // url path = "/simple"
  }
}
~~~

if문을 사용하여 `isPresent()` 함수로 null 체크를 하는 것은 기존의 null 체크 로직과 다를 바가 없음

__Optional을 사용하는 의미가 없음__

보다 제대로된 Optional 사용법이 필요함

[Optional 사용법] (http://www.daleseo.com/java8-optional-effective/)
