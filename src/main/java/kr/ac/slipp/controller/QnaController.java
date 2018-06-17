package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.slipp.domain.Qna;
import kr.ac.slipp.domain.User;
import kr.ac.slipp.dto.QnaRepository;
import kr.ac.slipp.util.HttpSessionUtils;

@Controller
@RequestMapping("/qnas")
public class QnaController {
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if( !HttpSessionUtils.isLoginUser(session) ) {
			
			return "/users/login";
		}
		
		return "/qnas/form";
	}
	@PostMapping("")
	public String create(String title, String contents, HttpSession session ) {
		if( !HttpSessionUtils.isLoginUser(session) ) {
			
			return "/users/login";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		
		Qna newQna = new Qna(sessionUser, title, contents);
		qnaRepository.save(newQna);
		
		return "redirect:/";
		
	}
	
//	@PostMapping("")
//	public String create(Qna qna) {
//		//users.add(user);
//		qnaRepository.save(qna);
//		return "redirect:/qnas";
//		
//	}
	


}
