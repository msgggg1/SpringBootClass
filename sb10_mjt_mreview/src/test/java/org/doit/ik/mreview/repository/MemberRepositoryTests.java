package org.doit.ik.mreview.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.stream.IntStream;

import org.doit.ik.mreview.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MemberRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	/*
	@Test
	   void insertMembers() {
	      IntStream.rangeClosed(1, 100).forEach(i->{
	         log.info("👌 MemberRepositoryTests.insertMembers()...");
	         Member member = Member.builder()
	               .email("r"+i+"@doit.com")
	               .pw("1111")
	               .nickname("reviewer"+i)
	               .build();
	         this.memberRepository.save(member);
	      });
	   }
	   */
	
	// 회원 삭제
	@Transactional
	@Commit
	@Test
	void testDeleteMember() {
		
		Long mid = 2L;
		Member member = Member.builder()
								.mid(mid)
								.build();
		
		// 처리 순서 : 해당 멤버가 작성한 리뷰 먼저 삭제 후 멤버 삭제 (FK)
		this.reviewRepository.deleteByMember(member);
		this.memberRepository.deleteById(mid);
	}
}
