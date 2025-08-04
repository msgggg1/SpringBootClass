package org.doit.ik.board.repository;

import java.util.List;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	// @Query 어노테이션 -> select 기본 어노테이션이기 때문에
	// update, delete JPQL 사용하려면 @Modifying 붙여야함
	@Modifying
	@Query("delete from Reply r where r.board.bno = :bno")
	void deleteByBno(@Param("bno") Long bno);
	
	// 
	List<Reply> getRepliesByBoardOrderByRno(Board board);
}
