package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.slipp.domain.User;
import kr.ac.slipp.dto.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	//private List<User> users=new ArrayList<User>();  // 이게 여기 있는게 맞나? 더 전역에 있어야 할필요는.. new 로 생성해야하나? autowire는
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("")
	public String create(User user) {
		//users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		
		model.addAttribute("users", userRepository.findAll());
		return "/users/list";
	}
	
	@GetMapping("/form")
	public String form() {
		
		return "/users/form";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "/users/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		//users.add(user);
		//userRepository.save(user);
		// 1. 있는 정보인지?
		User user = userRepository.findByUserId(userId);  // userId 로 조회되도록 추가해야 함... 우..
		if( user == null) {
			return "redirect:/users/login";
		}
		
		// 2. 패스워드 일치하는지?
		if( !user.getPassword().equals(password) ) {
			return "redirect:/users/login";
		}
		
		session.setAttribute("user", user);// 세션에 유저정보 저장
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");

		return "redirect:/";
	}
	

}
