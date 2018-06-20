package kr.ac.slipp.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.slipp.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
