package org.doit.ik.board.repository;

import java.util.List;

import org.doit.ik.board.entry.Board;
import org.doit.ik.board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository{ // SearchBoardRepository.searchPage() 사용가능
	//[1]
	@Query(   "select b, w "
			+ "from Board b left join b.writer w "
			+ "where b.bno= :bno")
	Object getBoardWithWriter(@Param("bno") Long bno);

	//[2]
	@Query(   "select b, r "
			+ "from Board b left join Reply r on r.board = b "
			+ "where b.bno= :bno")
	List<Object[]> getBoardWithReply(@Param("bno") Long bno);

	//[3] 게시물 + 회원 + 댓글
	@Query( value = 
			"select b, w, count(r) "
					+ "from Board b left join b.writer w"
					+ "             left join Reply r on r.board = b "
					+ "group by b"
					, countQuery = "select count(b) from Board b" )
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
	/*
	   Hibernate: 
		    select
		        b1_0.bno,
		        b1_0.content,
		        b1_0.moddate,
		        b1_0.regdate,
		        b1_0.title,
		        b1_0.writer_email,
		        w1_0.email,
		        w1_0.moddate,
		        w1_0.name,
		        w1_0.password,
		        w1_0.regdate,
		        count(r1_0.rno) 
		    from
		        board b1_0 
		    left join
		        member w1_0 
		            on w1_0.email=b1_0.writer_email 
		    left join
		        reply r1_0 
		            on r1_0.board_bno=b1_0.bno 
		    group by
		        b1_0.bno 
		    order by
		        b1_0.bno desc 
		    limit
		        ?
		Hibernate: 
		    select
		        count(b1_0.bno) 
		    from
		        board b1_0
	 */

	// 조회화면 JPQL
	@Query("select b, w, count(r)"
			+ " from Board b left join b.writer w "
			+ " left outer join Reply r on r.board = b "
			+ " where b.bno = :bno")   
	Object getBoardByBno(@Param("bno") Long bno);
	
	
}
