package org.doit.ik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

import org.doit.ik.sbb.answer.Answer;
import org.doit.ik.sbb.answer.AnswerRepository;
import org.doit.ik.sbb.question.Question;
import org.doit.ik.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AnswerRepositoryTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	// 답변 저장 
	/*
	@Test
    void testJpa() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }
    */
	
	/*
	// 답변 조회
	@Test
    void testJpa() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }
    */
    
	
	// LazyInitializationException
	@Test
	@Transactional
    void testJpa() {
		// 1. 2번째 질문을 조회
        Optional<Question> oq = this.questionRepository.findById(2);
        // 2. 있다면 패스 없으면 failure
        assertTrue(oq.isPresent());
        // 3. 2번째 질문 엔티티 객체
        Question q = oq.get();
        
        // DB 세션이란 스프링 부트 애플리케이션과 데이터베이스 간의 연결을 의미한다
        
        // 4. 2번 질문의 답글 목록 조회
        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }

}
