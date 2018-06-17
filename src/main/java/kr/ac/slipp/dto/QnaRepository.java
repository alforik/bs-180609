package kr.ac.slipp.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.slipp.domain.Qna;

public interface QnaRepository extends JpaRepository<Qna, Long> {

}
