### MessageConverter
---

<br>

___메서드에 `@ResponseBody`로 어노테이션이 되어 있다면 메서드에서 리턴되는 값은 View를 통해서 출력되지 않고 HTTP ResponseBody에 직접 쓰여지게 됨___

__쓰여지기 전에 리턴되는 데이터 타입에 따라 MessageConverter에서 변환이 이뤄진 후 쓰여지게 됨__

>### MessageConverter

__HTTP 통신에서 일반 문자열이 아닌 XML이나 JSON으로 통신하기 위해 사용__


브라우저 내에 XMLHttpRequest라는 내장 브라우저가 존재하여 Ajax 통신이 가능한데 XML로 데이터를 전송하는 것은 비효율적이므로 JSON으로 데이터를 주고 받음

<br>

### MessageConverter 종류

- __StringHttpMessageConverter__
- FormHttpMessageConverter
- ByteArrayMessageConverter
- MarshallingHttpMessageConverter
- __MappingJackson2HttpMessageConverter__
  - Jackson's ObjectMapper를 사용하여 request, response를 JSON으로 변환할 때 사용되는 MessageConverter
  - application/json 지원
- SourceHttpMessageConverter
- BufferedImagedHttpMessageConverter

<br><br>

>### 환경 설정

- __pom.xml__

~~~
<!-- jackson -->
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.1.1</version>
</dependency>
~~~

에러날 경우 최신버전으로 바꿔주면 정상 적용됨

<br>

- __spring-servlet.xml__

~~~
<mvc:annotation-driven>
  <!-- messageConverter -->
  <mvc:message-converters>
    <bean class="org.springframework.http.converter.StringHttpMessageConverter">
      <property name="supportedMediaTypes">
        <list>
          <value>text/html; charset=UTF-8</value>
        </list>
      </property>
    </bean>

    <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
      <property name="supportedMediaTypes">
        <list>
          <value>application/json; charset=UTF-8</value>
        </list>
      </property>
    </bean>
  </mvc:message-converters>
</mvc:annotation-driven>
~~~

<br>

- __Controller__

~~~
@Controller
public class JoinControlelr {

  @ResponseBody
  @RequestMapping("/join")
  public Map<String, Object> join(){
    Map<String, Object> map = new HashMap<String, Object>();

    map.put(key, value);

    return map
  }
}
~~~

http://localhost:8080/messageConverterTest/join으로 요청을 하면 JSON으로 응답되는 것을 확인할 수 있음

__MessageConverter를 통해 java 객체를 JSON으로 응답하게 되면 Ajax 통신이 가능해짐__

<br><br>

>### ViewResolver vs MessageConverter

컨트롤러에서 응답을 할 때 String으로 반환하면 뷰 페이지를 응답해줌

`@ResponseBody`가 있으면 문자열을 그대로 반환해주는데 반환되는 문자열에 대해 ViewResolver가 실행될지 MessageConverter가 실행될지 알 수 없음

DispatchServlet은 handlerMethod()를 호출하여 응답으로 String, ModelAndView, Object 중 하나를 반환

이후 ViewResolver, MessageConverter가 실행되며 DispatchServlet이 반환되는 타입을 보고 어떤 처리를 할지 결정함

- String이 반환되면 뷰 이름을 찾아서 jsp를 렌더링하고 뷰가 없으면 404 반환
- 메서드 위에 `@ResponseBody` 어노테이션이 있으면 뷰를 찾지 않고 String을 그대로 반환

<br>

- DispatchServlet이 ModelAndView를 반환하면 ViewResolver 실행
- `@ResponseBody`가 있으면 Object는 MessageConverter가 실행되어 반환
