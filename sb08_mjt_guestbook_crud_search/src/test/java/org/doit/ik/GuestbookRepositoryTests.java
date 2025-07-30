package org.doit.ik;

import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.entity.QGuestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
class GuestbookRepositoryTests {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	/*
	@Test
	void insertDummies() {
		IntStream.rangeClosed(1, 300).forEach(i->{
			
			Guestbook guestbook = Guestbook.builder()
								.title("title..." + i)
								.content("content..." + i)
								.writer("user" + (i%10))
								.build();
			
			System.out.println("👍👍👍"+this.guestbookRepository.save(guestbook));
		});
	}
	*/
	/*
	@Test
	void updateTest() {
		Optional<Guestbook> result = this.guestbookRepository.findById(300L);
		
		if (result.isPresent()) {
			// entity
			Guestbook guestbook = result.get();
			guestbook.changeTitle("ChangeTitle...")	;
			guestbook.changeContent("ChangeContent...");
			this.guestbookRepository.save(guestbook);
		}
	}
	*/
	
	// Querydsl 사용해서 테스트 -> 방명록 검색 구현
	@Test
	public void querydslTest() {
		//Q도메인클래스 생성 -> 얻어오기
		QGuestbook qGuestbook = QGuestbook.guestbook;
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		// 동적 쿼리 조건절 (where ~~~)
		String type ="tc";
		String keyword = "1";
		
		// title LIKE '%1%'
		BooleanExpression exprTitle = qGuestbook.title.contains(keyword);
		BooleanExpression exprContent = qGuestbook.title.contains(keyword);
		
		BooleanExpression exprAll = exprTitle.or(exprContent);
		
		booleanBuilder.and(exprAll);
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
		
		Page<Guestbook> result = this.guestbookRepository.findAll(booleanBuilder, pageable);
		
		result.stream().forEach(guestbook ->{
			System.out.println("👍👍" + guestbook);
		});
	}
}

















