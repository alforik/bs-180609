package kr.ac.slipp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

	@RequestMapping("/hello")
	public String hello() {
		return "hello rest";
	}
	
//	@GetMapping("/")
//	public String root(Model model, HttpSession session)
//	{
//		session.setAttribute("title", "title test");
//		session.setAttribute("msg", "Hello!!");
//		model.addAttribute("title", "타이틀임");
//		model.addAttribute("msg", "전달메시지");
//		return "mustache";
//	}
}
