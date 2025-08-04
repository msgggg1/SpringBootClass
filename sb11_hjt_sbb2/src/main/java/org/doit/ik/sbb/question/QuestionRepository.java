package org.doit.ik.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	// 커스텀 쿼리 메서드 선언 : 제목 조회
	Question findBySubject(String subject);
	
	// 제목 조건 + 내용 조건 조회
	Question findBySubjectAndContent(String subject, String content);
	
	// 
	List<Question> findBySubjectLike(String subject);
	
}
