package kr.ac.slipp.controller;

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
	

}
