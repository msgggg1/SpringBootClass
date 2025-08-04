package org.doit.ik.mreview.repository;


import java.util.List;

import org.doit.ik.mreview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	/*// [1]
	// 번호/제목/리뷰수/평점/평점평균/등록일 - 목록 페이지 + 페이징 처리
	// 여러 칼럼값을 가져오기 때문에 Object[]로 받음
	// JPQL 클래스명이기 때문에 대소문자 정확하게 지켜야함.
	@Query("select m, avg(coalesce(r.grade,0)), count(distinct r)"
			  + " from Movie m "
		      + " left outer join Review r on r.movie = m "
		      + " group by m ")
	Page<Object[]> getListPage(Pageable pageable);
	 */

	/*
	// [2]
	// max(mi) mi엔티티 자체를 집계함수에 사용할 수 없다.
	@Query("select m, max(mi.inum), avg(coalesce(r.grade,0)), count(distinct r)"
			  + " from Movie m "
		      + " left outer join Review r on r.movie = m "
		      + " left outer join MovieImage mi on mi.movie = m "
		      + " group by m ")
	Page<Object[]> getListPage(Pageable pageable);
	 */

	//[3]
	@Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) "
			+ " from Movie m "
			+ "    left outer join Review r on r.movie = m "
			+ "    left outer join MovieImage mi on mi.movie = m "
			+ " group by m , mi") //집계함수 외 칼럼은 group by 절에 있어야한다.
	Page<Object[]> getListPage(Pageable pageable);

	// 특정 영화의 정보를 조회
	@Query( "select m, mi "
			+ " from Movie m "
			+ " left outer join MovieImage mi on mi.movie = m "
			+ " where m.mno = :mno " )
	List<Object[]> getMovieWithAll(@Param("mno") Long mno); 
}
