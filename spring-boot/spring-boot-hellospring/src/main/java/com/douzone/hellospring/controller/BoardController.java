package com.douzone.hellospring.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

	@ResponseBody
	@RequestMapping("/write")
	public String write(@RequestParam("email") String email,
						@RequestParam(value="name", required=false) String name,
						@RequestParam String password, // 변수 이름으로 설정 - required=true
			   /* 추천 */	@RequestParam(value="content", required=true, defaultValue="") String content) { // url에서 name 들어오는 파라미터 값을 세팅 - required=false이면 파라미터 없어도 에러 안남
		
		System.out.println(email);
		System.out.println(name);
		System.out.println(content);
		System.out.println(password);
		
		return "BoardController:write()" + email + name + content + password;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="no", required=false) Long no ) { // 알아서 형변환 string -> long (long 기본타입은 null이 안되기 때문에 Long 같은 객체 타입으로 설정 - null 가능)
		System.out.println(no);
		
		return "BoardController:delete()" + no;
	}
	
	@ResponseBody
	@RequestMapping("/view")
	public String view(@RequestParam(value="no", required=true, defaultValue="0") Long no) { // @안에서는 숫자를 바로 줄 수 없음 - 0 -> "0"
		System.out.println(no);
		
		return "BoardController:view()" + no;
	}
	
	@ResponseBody
	@RequestMapping("/view2/{id}/{no}") // ?no 형식이 아니라 /no처럼 path형식으로 나옴
	public String view2(@PathVariable("id") String id, @PathVariable("no") Long no) {
		System.out.println(id);
		System.out.println(no);
		
		return "BoardController:view2()" + id + no;
	}
}
