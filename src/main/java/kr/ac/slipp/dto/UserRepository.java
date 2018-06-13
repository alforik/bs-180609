package kr.ac.slipp.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.slipp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(String userId); // 와 대단함..
}
