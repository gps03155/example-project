### Spring Annotation
---

<br>

>### Annotation이란?

<br>

_@ 사인 하나로 많은 컨텍스트들을 제공받을 수 있게 함_
(Spring Framework에서 제공하는 많은 컨텍스트 활용 가능)

__@ 사인으로 시작하는 것을 어노테이션이라고 함__

<br><br>

>### Spring Annotation

<br>

#### 의존성 주입 용도

- __@Required__
optional 하지 않음 꼭 필요한 속성들을 정의
(Controller 클래스의 메서드에서 @requestBody로 가져올 만한 것들이나 @PathVariable로 가져올만한 꼭 필요한 값들에 쓰일 수 있음)

<br>

- __@Autowired__
속성(field), setter method, 생성자(constructor)에서 사용 - 무조건적인 객체에 대한 의존성 주입하여 스프링이 자동적으로 값을 할당
(Controller 클래스에서 DAO나 Service에 관한 객체들을 주입시킬 때 주로 사용)

<br>

- __@Inject__
@Autowired와 비슷한 역할

<br><br>

#### 컨트롤러 관련

- __@Component__
@Component Annotation이 있는 클래스에 대하여 bean 인스턴스 생성

<br>

- __@Controller__
스프링 MVC의 컨트롤러 객체임을 명시하는 애노테이션 (클래스)

<br>

- __@RestController__
스프링에서 Controller 중 View로 응답하지 않는 컨트롤러를 의미
(HttpResponse로 바로 응답이 가능하며 @ResponseBody 역할을 자동적으로 해줌)

<br>

- __@RequestMapping__
  - 특정 URI에 매칭되는 클래스나 메소드임을 명시하는 애노테이션 (클래스, 메소드)
  - 요청을 받는 형식인 GET, POST, PATCH, PUT, DELETE를 정의하기도 함
  (요청 받는 형식을 정의하지 않는다면 자동적으로 GET으로 설정)

<br>

- __@RequestParam__
  - 요청(request)에서 특정한 파라미터의 값을 찾아낼 때 사용하는 애노테이션 (파라미터)
  - `?moviename-thepurge`와 같은 쿼리 파라미터 파싱
  ~~~
  @RequestMapping(value="/search/movie", method=RequestMethod.GET)
  public ResponseEntity<?> someMethod(@RequestParam String moviename){
    // request URI would be like '/search/movie?moviename=thepurge'

    try{
      List<Movie> movies = service.searchByMoviename(moviename);
    } catch(Exception e){
      e.printStackTrace();
    }

    // return some response here
  }
  ~~~

<br>

- __@RequestHeader__
요청(request)에서 특정 HTTP 헤더 정볼ㄹ 추출할 때 사용 (파라미터)

<br>

- __@PathVariale__
  - 현재의 URI에서 원하는 정보를 추출할 때 사용하는 애노테이션 (파라미터)
  - URI에서 / 다음으로 넘어오는 값들을  파싱
  ~~~
  @RequestMapping(value="/some/path/{id}", method=RequestMethod.GET)
  public ResponseEntity<?> someMethod(@PathVariable int id){
    // some logic here
  }
  ~~~

<br>

- __@CookieValue__
현재 사용자의 쿠키가 존재하는 경우 쿠키의 이름을 이용해서 쿠키의 값을 추출 (파라미터)

<br>

- __@ModelAttribute__
자동으로 해당 객체를 뷰까지 전달하도록 만드는 애노테이션 (메소드, 파라미터)

<br>

- __@SessionAttribute__
세션상에서 모델의 정보를 유지하고 싶은 경우에 사용 (클래스)

<br>

- __@InitBinder__
파라미터를 수집해서 객체로 만들 경우에 커스터마이징 (메소드)

<br>

- __@ResponseBody__
  - 리턴 타입 HTTP의 응답 메시지로 전송 (메소드, 리턴타입)
  - HttpMessageConverter를 이용하여 JSON 혹은 xml로 요청에 응답할 수 있게 해주는 어노테이션
  - @RestController가 붙어있다면 사용할 필요가 없지만 그렇지 않은 단순 컨트롤러라면 HttpResponse로 응답할 수 있게 해줌

<br>

- __@RequestBody__
  - 요청(request) 문자열이 그대로 파라미터로 전달 (파라미터)
  - POST나 PUT, PATCH로 요청을 받을 때 요청에서 넘어온 body 값들을 자바 타입으로 파싱
  ~~~
  @RequestMapping(value="/book", Method=RequestMethod.POST)
  public ResponseEntity<?> someMethod(@RequestBody Book book){
    try{
      service.insertBook(book);
    } catch(Exception e){
      e.printStackTrace();
    }

    // return some response here
  }
  ~~~

<br><br>

#### 데이터 접근 관련

- __@Service__
  - 서비스 객체 (클래스) - Service Class에서 사용
  - 비즈니스 로직을 수행하는 클래스라는 것을 나타냄

<br>

  - __@Repository__
    - DAO 객체 (클래스)
    - DB에 접근하는 메서드를 가지고 있는 클래스에서 사용
