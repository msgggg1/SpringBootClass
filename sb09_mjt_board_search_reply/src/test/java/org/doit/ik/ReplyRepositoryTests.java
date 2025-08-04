package org.doit.ik;

import java.util.stream.IntStream;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;
import org.doit.ik.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReplyRepositoryTests {
	

	@Autowired
	private ReplyRepository replyRepository;
	
	/*
	@Test
	void testListByBoard() {
		Board board = Board.builder()
							.bno(29L)
							.build();
		List<Reply> replyList = this.replyRepository.getRepliesByBoardOrderByRno(board);
		
		replyList.forEach(reply -> {
			System.out.println("💕" + reply);
		});
	}
	*/

	
	@Test
	void insertReply() {

		IntStream.rangeClosed(1, 300).forEach(i->{

			long bno = (long)(Math.random()*99)+2;

			Board board= Board.builder()
					.bno(bno)
					.build();

			Reply reply = Reply.builder()
					.text("reply..."+i)
					.board(board)
					.replyer("guest")
					.build();

			this.replyRepository.save(reply);
		});   
	}
	

}
