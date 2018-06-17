package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, HttpSession session, Model model) {
		if( !HttpSessionUtils.isLoginUser(session) ) {
			
			return "/users/login";
		}
		
		//Qna qna= qnaRepository.findOne(id);
		model.addAttribute("qna",qnaRepository.findOne(id));
		
		return "/qnas/show";
	}
	
	@GetMapping("/{id}/updateform")
	public String updateform(@PathVariable Long id, Model model , HttpSession session) {
		// 로그인 되었다는 것을 꼭 이런식으로 처리해야 하나?
		//Object tempUser = session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
		if( !HttpSessionUtils.isLoginUser(session) ) {
			return "redirect:/users/login";
		}
		
		//User user= userRepository.findOne(sessionedUser.getId());
		//Qna qna= qnaRepository.findOne(id);
		model.addAttribute("qna",qnaRepository.findOne(id));

		return "/qnas/updateForm";
	}
	
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, Qna updatedQna , HttpSession session) {
		//Object tempUser = session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
		if( HttpSessionUtils.isLoginUser(session) ) {
			return "redirect:/users/login";
		}

		Qna qna= qnaRepository.findOne(id);

		qna.update(updatedQna);
		
		try { // 이부분 엉성함.. sql exception 필요
			qnaRepository.save(qna);
		} catch (Exception  e) {
			throw new IllegalStateException("something wrong");
		}
		

		//System.out.println("here 6");
		return "redirect:/qnas/"+id;
	}
	



}
