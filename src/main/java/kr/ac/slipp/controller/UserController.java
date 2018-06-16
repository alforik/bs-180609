package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		session.setAttribute("sessionedUser", user);// 세션에 유저정보 저장
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		

		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String updateform(@PathVariable Long id, Model model , HttpSession session) {
		// 로그인 되었다는 것을 꼭 이런식으로 처리해야 하나?
		Object tempUser = session.getAttribute("sessionedUser");
		if( tempUser == null ) {
			return "redirect:/users/login";
		}
		
		User sessionedUser = (User)tempUser;
		if( !id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("cannot update others");
		}
		
		User user= userRepository.findOne(sessionedUser.getId());
		model.addAttribute("user",user);

		return "/users/updateForm";
	}
	
	
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser , HttpSession session) {
		Object tempUser = session.getAttribute("sessionedUser");
		//System.out.println("here 1");
		if( tempUser == null ) {
			return "redirect:/users/login";
		}
		System.out.println("here 2");
		User sessionedUser = (User)tempUser;
		if( !id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("cannot update others");
		}
		System.out.println("here 3");
		User user= userRepository.findOne(id);
		System.out.println("here 4");
		// update시 id가 동일할 수 있음 

		user.update(updatedUser);
		System.out.println("here 5");
		
		try { // 이부분 엉성함.. sql exception 필요
			userRepository.save(user);
		} catch (Exception  e) {
			throw new IllegalStateException("user id is different.");
		}
		

		System.out.println("here 6");
		return "redirect:/users/"+id;
	}
	

	

}
