package org.doit.ik;

import java.util.stream.IntStream;

import org.doit.ik.board.entity.Member;
import org.doit.ik.board.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;
	
	// 1. Member 테이블에 회원 추가 100명
	@Test
	void insertMembers() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			Member member = Member.builder()
									.email("user"+i+"@aaa.com")
									.password("1111")
									.name("USER" + i)
									.build();
			this.memberRepository.save(member);
		});
			
	}

}
