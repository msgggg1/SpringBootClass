package org.doit.ik.mreview.repository;

import java.util.List;

import org.doit.ik.mreview.entity.Member;
import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	// ★★★ 특정 영화의 모든 리뷰 정보 + 회원 닉네임
	@EntityGraph(attributePaths = {"member"}, type=EntityGraphType.FETCH)
	List<Review> findByMovie(Movie movie);
	
	// FK 삭제 위해
	// [1]과 [2] 실행쿼리 비교
	
	//[1]
	//void deleteByMember (Member member);
	
	//[2]
	@Modifying
	@Query("delete from Review mr where mr.member = :member")
	void deleteByMember (@Param("member") Member member);
	
}
