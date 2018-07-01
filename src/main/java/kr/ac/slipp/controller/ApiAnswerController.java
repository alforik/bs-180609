package kr.ac.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.slipp.domain.Answer;
import kr.ac.slipp.domain.Qna;
import kr.ac.slipp.domain.Result;
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

		qna.addCountOfAnswer();
		
		return answerRepository.save(answer);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long qnaId, @PathVariable Long id, HttpSession session) {
		
		if( !HttpSessionUtils.isLoginUser(session) ) {
			return Result.fail("로그인해야 합니다.");
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Answer answer = answerRepository.findOne(id);
		if( !answer.isSameWriter(loginUser ) ) {
			return Result.fail("자신의 글만 수정 삭제가능합니다.");
		}

		answerRepository.delete(id);
		Qna qna = qnaRepository.findOne(qnaId);
		qna.subtractCountOfAnswer();
		qnaRepository.save(qna);
		
		return Result.ok();
	}

}
