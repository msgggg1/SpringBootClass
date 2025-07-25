package org.doit.ik.dept;

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
public interface DeptRepository extends JpaRepository<Dept, Integer>{ //@Id 준 칼럼의 자료형

	// 방법1) 기본 제공 메서드 - 선언 안해도 됨. 지금은 학습 차원
	//List<Dept> findAll();

	// 방법2) 부서명으로 조회	- JpaRepository 안에 없음 -> 개발자가 커스텀 : 쿼리메서드
	Dept findByDname(String dname);
	List<Dept> findByLoc(String loc);

	// 방법3) @Query 사용. 규칙대로X
	@Query("SELECT d FROM Dept d") // 암기
	List<Dept> getAllDepts();
	
	// 페이징 처리 + 전체 조회
	Page<Dept> findAll(Pageable pageable);

	// [1] 부서 수정 : @Modifying + @Query 사용 (직접 SQL 작성)

	@Modifying
	@Transactional
	@Query("UPDATE Dept d SET d.dname = :dname, d.loc = :loc WHERE d.deptno = :deptno")
	int updateDept(@Param("deptno") Integer deptno,
			@Param("dname") String dname,
			@Param("loc") String loc);

}

/*
복잡한 쿼리 사용
1) 쿼리메서드
2) JPQL
3) @Query

동적쿼리 사용
QueryDsl 사용
*/











