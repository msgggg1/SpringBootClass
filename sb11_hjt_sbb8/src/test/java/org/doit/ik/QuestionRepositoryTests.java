package org.doit.ik;

import org.doit.ik.sbb.question.QuestionRepository;
import org.doit.ik.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionRepositoryTests {

	@Autowired
	private QuestionRepository questionRepository;

	/*
	@Test
	void testJpa() {        
		//insert into question (id, subject, content) values (1, '안녕하세요', '가입 인사드립니다 ^^');
		//insert into question (id, subject, content) values (2, '질문 있습니다', 'ORM이 궁금합니다');

		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}
	 */

	// 2. 조회
	/*
	@Test
    void testJpa() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        // Junit.assertEquals(A, B) A!=B 테스트 실패 failures
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }
	 */

	// 1개의 질문을 조회
	/*
	@Test
    void testJpa() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }
	 */

	// 제목으로 검색해서 조회 - CRUD
	// => 커스텀 쿼리 메서드, @Query() 등등
	/*
	@Test
	void testJpa() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}
	*/
	
	//    where q1_0.subject=? and q1_0.content=?
	/*
	@Test
    void testJpa() {
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }
    */
	
	/*
	@Test
    void testJpa() {
		// WHERE subject LIKE '%bbb%'
		// WHERE subject LIKE 'bbb%'
		// WHERE subject LIKE '%bbb'
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }
    */
	
	/*
	// Insert, Select, 수정 - save() pk 있으면 수정, 없으면 추가
	@Test
    void testJpa() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        
        Question q = oq.get(); // PK 존재 
        q.setSubject("수정된 제목");
        this.questionRepository.save(q); // UPDATE
    }
    */
	
	// 삭제
	/*
	@Test
    void testJpa() {
		// count() : 테이블의 레코드 수
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }
    */
	
	@Autowired
    private QuestionService questionService;
	
	/*
	@Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content);
        }
    }
	*/
	
}
