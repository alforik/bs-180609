package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

		Qna qna= qnaRepository.findOne(id);

		
		model.addAttribute("qna",qna);
		
		return "/qnas/show";
	}
	
	@GetMapping("/{id}/updateform")
	public String updateform(@PathVariable Long id, Model model , HttpSession session) {
		// 로그인 되었다는 것을 꼭 이런식으로 처리해야 하나?
		//Object tempUser = session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
		
		try {
			Qna qna= qnaRepository.findOne(id);
			
			hasPermission(session,qna);
			
			model.addAttribute("qna",qna);
			
			return "/qnas/updateForm";
		} catch ( IllegalStateException e ) {
			
			model.addAttribute("errorMessage",e.getMessage());
			
			return "/users/login";
		}
	
	}
	
	private boolean hasPermission(HttpSession session, Qna qna) {
		if( !HttpSessionUtils.isLoginUser(session) ) {
			
			System.out.println("hasP 로그인필요");
			
			
			throw new IllegalStateException("로그인이 필요합니다.");
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);
		
		if( !qna.isSameWriter(loginUser)) {
			

			System.out.println("hasP 자신글만..");

			throw new IllegalStateException("자신의 글만 수정, 삭제가 가능합니다.");
		}
		return true;
	}
	// put method를 사용함
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents , Model model, HttpSession session) {
		//Object tempUser = session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
		
		try {
			Qna qna= qnaRepository.findOne(id);
			
			hasPermission(session,qna);
			
			qna.update(title, contents);
			
			qnaRepository.save(qna);
			
			return String.format("redirect:/qnas/%d", id); 
			
		} catch ( IllegalStateException e ) {
			
			model.addAttribute("errorMessage",e.getMessage());
			
			return "/users/login";
		}
		

//		try { // 이부분 엉성함.. sql exception 필요
//			qnaRepository.save(qna);
//		} catch (Exception  e) {
//			throw new IllegalStateException("something wrong");
//		}
		
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, Model model, HttpSession session) {
		//Object tempUser = session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
		
		try {
			Qna qna= qnaRepository.findOne(id);
			
			hasPermission(session,qna);
			
			qnaRepository.delete(id);
			
			return "redirect:/";
		} catch ( IllegalStateException e ) {
			
			model.addAttribute("errorMessage",e.getMessage());
			
			return "/users/login";
		}
		

//		try { // 이부분 엉성함.. sql exception 필요
//			qnaRepository.delete(id);
//		} catch (Exception  e) {
//			throw new IllegalStateException("something wrong");
//		}

	}



}
