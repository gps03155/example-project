package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JSONResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller("guestbookAPIController")
@RequestMapping("/guestbook/api")
public class GuestBookController {
	@Autowired
	private GuestBookService guestbookService;
	
	@RequestMapping("")
	public String index() {
		return "guestbook/index-ajax";
	}
	
	@ResponseBody
	@RequestMapping("/ajax-list")
	public JSONResult getList(@RequestParam(value="page", required=true, defaultValue="1") int page) {
		List<GuestBookVo> list = guestbookService.ajaxGetList(page);
		
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/ajax-delete")
	public JSONResult delete(@RequestParam(value="page", required=true, defaultValue="1") int page, 
							 @RequestParam(value="password", required=true, defaultValue="") String password, 
							 @RequestParam(value="no", required=true, defaultValue="") int no) {
		GuestBookVo vo = new GuestBookVo();
		
		vo.setPassword(password);
		vo.setNo(no);
		
		int count = guestbookService.delete(vo);
		boolean result = false;
		System.out.println(count);
		
		if(count == 1) {
			result = true;
		}
		
		return JSONResult.success(result);
	}
	
	@ResponseBody
	@RequestMapping("/ajax-insert/{page}/{name}/{password}/{content}")
	public JSONResult insert(@PathVariable int page, @PathVariable String name, @PathVariable String password, @PathVariable String content) {
		guestbookService.insert(name, password, content);
		System.out.println(guestbookService.getLastID());
		
		return JSONResult.success(guestbookService.ajaxInsert(guestbookService.getLastID()));
	}
}
