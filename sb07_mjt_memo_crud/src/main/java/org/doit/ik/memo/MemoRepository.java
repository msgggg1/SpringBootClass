package org.doit.ik.memo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

	/*
	insert : save(엔티티객체)
	update : save(엔티티객체)
	delete : deleteById(키값), delete(엔티티객체)
	select : findAll(), findById(키), getOne(키)
	 */

	// 1. 쿼리 메서드 - 메서드의 이름 자체가 질의(Query)문
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);   
	
	
	// 2. 쿼리 메서드 + 페이징처리 결합
	Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
	
	// 3. 쿼리 메서드
	void deleteMemoByMnoLessThan(Long mno);
	// where mno < ?
	
	
	// A. @Query() 어노테이션
	@Query("select m from Memo m order by m.mno desc")
	List<Memo> getListDesc();
	
	// B.
	@Transactional
	@Modifying
	@Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
	int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);
	// :파라미터명
	// ?1	?2
	// :#{}	자바빈 객체 받을 때	@Param("param") Memo memo
	// 예) SQL				:#{param.mno}
	
	// C 네이티브 SQL
	@Query(value="select * from memo where mno > 0", nativeQuery = true)
	List<Object []> getNativeResult();
	

}
