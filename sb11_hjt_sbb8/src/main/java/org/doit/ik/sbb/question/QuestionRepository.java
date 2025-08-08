package org.doit.ik.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	// 커스텀 쿼리 메서드 선언 : 제목 조회
	Question findBySubject(String subject);
	
	// 제목 조건 + 내용 조건 조회
	Question findBySubjectAndContent(String subject, String content);
	
	// 
	List<Question> findBySubjectLike(String subject);
	
	// 페이징 처리 + 목록 조회
	Page<Question> findAll(Pageable pageable);
	
	// QueryDsl 사용 -> 동적쿼리, 복잡한 조인 등
	// @Query( JPQL ) 메서드 : 검색
	@Query("select "
            + "distinct q "
            + "from Question q " 
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
	
}
