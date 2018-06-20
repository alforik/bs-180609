package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.slipp.domain.Answer;
import kr.ac.slipp.domain.Qna;
import kr.ac.slipp.domain.User;
import kr.ac.slipp.dto.AnswerRepository;
import kr.ac.slipp.dto.QnaRepository;
import kr.ac.slipp.util.HttpSessionUtils;

@Controller
@RequestMapping("/qnas/{qnaId}/answers")
public class AnswerController {

	@Autowired
	private QnaRepository qnaRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public String create(@PathVariable Long qnaId, String contents, HttpSession session) {
		
		if( !HttpSessionUtils.isLoginUser(session) ) {
			return "redirect:/users/login";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Qna qna = qnaRepository.findOne(qnaId);
		Answer answer = new Answer(loginUser, qna, contents); 
		answerRepository.save(answer);
		
		return String.format("redirect:/qnas/%d", qnaId);
	}

}
