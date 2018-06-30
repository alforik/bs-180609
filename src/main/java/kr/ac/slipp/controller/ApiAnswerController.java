package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.slipp.domain.Answer;
import kr.ac.slipp.domain.Qna;
import kr.ac.slipp.domain.User;
import kr.ac.slipp.dto.AnswerRepository;
import kr.ac.slipp.dto.QnaRepository;
import kr.ac.slipp.util.HttpSessionUtils;

@RestController
@RequestMapping("/api/qnas/{qnaId}/answers")
public class ApiAnswerController {

	@Autowired
	private QnaRepository qnaRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public Answer create(@PathVariable Long qnaId, String contents, HttpSession session) {
		
		if( !HttpSessionUtils.isLoginUser(session) ) {
			return null;
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Qna qna = qnaRepository.findOne(qnaId);
		Answer answer = new Answer(loginUser, qna, contents); 
		
		return answerRepository.save(answer);
	}

}
