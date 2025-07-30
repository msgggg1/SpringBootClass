package org.doit.ik;

import java.util.List;

import org.doit.ik.memo.Memo;
import org.doit.ik.memo.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import jakarta.transaction.Transactional;

@SpringBootTest
class MemoRepositoryTests {
	
	@Autowired
	private MemoRepository memoRepository;

	/*
	@Test
	void testClass() {
		System.out.println("👍👍👍"+ this.memoRepository.getClass().getName());
	}
	*/
	
	// 더미 데이터 insert
	/*
	@Test
	void testInsertDummies() {
		IntStream.rangeClosed(1, 100)
				.forEach(i->{
					Memo memo = Memo.builder()
									// .mno(i) 자동 증가 처리함
									.memoText("Sample Memo..." + i)
									.build();
					this.memoRepository.save(memo); // 엔티티객체 매개변수
				});
	}
	*/
	
	// mno=20 메모 내용을 SELECT
	/*
	@Test
	void testSelectOne() {
		Optional<Memo> result = this.memoRepository.findById(20L);
		result.ifPresent(memo->{
			System.out.println("👍👍 메모텍스트:" + memo.getMemoText());
		});
	}
	*/
	
	// 1번 메모글의 memoText='수정..' update 작업
	/*
	@Test
	void testUpdate() {
		Memo memo = Memo.builder()
						.mno(1L)
						.memoText("수정...")
						.build();
		memo = this.memoRepository.save(memo); 
		System.out.println("👍👍👍👍👍" + memo);

	}
	*/
	
	// 1번 메모글 delete
	/*
	@Test
	void testUpdate() {
		this.memoRepository.deleteById(1L); 
	}
	*/
	
	// 페이징 처리
	/*
	@Test
	void testPageSelect() {
		
		Sort sort = Sort.by("mno").descending();
		Pageable pageable = PageRequest.of(0, 10, sort) ;
		Page<Memo> result = this.memoRepository.findAll(pageable); 
		List<Memo> memoList = result.getContent();
		
		System.out.println("=".repeat(50));
		for (Memo memo : memoList) {
			System.out.println("👍" + memo);
		}
		System.out.println("=".repeat(50));
			System.out.println("총 페이지 수"+result.getTotalPages());
			System.out.println("총 레코드 수"+result.getTotalElements());
			System.out.println("현재 페이지"+result.getNumber());
			System.out.println("이전 페이지"+result.hasPrevious());
			System.out.println("다음 페이지"+result.hasNext());
			System.out.println(result.isFirst());
		System.out.println("=".repeat(50));
		
	}
	*/
	
	// 쿼리 메서드 단위 테스트
	//List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
	/*  
	@Test
	public void testQueryMethod() {
		List<Memo> list = this.memoRepository.findByMnoBetweenOrderByMnoDesc(35L, 76L);
		list.forEach(memo ->{
			System.out.println("👍👍👍"+memo);
		});
	}
	*/
	
	//Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
	/*
	@Test
	public void testFindByMnoBetween() {
		Sort sort = Sort.by("mno").descending();
		Pageable pageable = PageRequest.of(0, 10, sort) ;
		Page<Memo> result = this.memoRepository.findByMnoBetween(10L, 50L, pageable); 
		List<Memo> memoList = result.getContent();
		
		System.out.println("=".repeat(50));
		for (Memo memo : memoList) {
			System.out.println("👍" + memo);
		}
	}
	*/
	
	//void deleteMemoByMnoLessThan(Long mno);
	@Test
	@Transactional // select + delete 하나로 묶기 위해
	@Commit // 안붙이면 자동 롤백되어진다.
	public void testDeleteMemoByMnoLessThan() {
		// select 작업 : 엔티티 조회
		// delete 작업 : 
		// 두 작업이 일어나기 때문에 @Transactional + @commit 붙여야 함.
		this.memoRepository.deleteMemoByMnoLessThan(3L); 
	}
	
	
}
