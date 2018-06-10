package kr.ac.slipp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ac.slipp.entity.User;

@Controller
public class UserController {
	
	private List<User> users=new ArrayList<User>();  // 이게 여기 있는게 맞나? 더 전역에 있어야 할필요는.. new 로 생성해야하나? autowire는
	
	@PostMapping("/create")
	public String create(User user) {
		users.add(user);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("users", users);
		return "list";
	}

}
