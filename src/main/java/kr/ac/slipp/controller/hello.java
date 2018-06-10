package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class hello {

	@RequestMapping("/hello")
	public void hello() {
		//return "hello rest";
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
