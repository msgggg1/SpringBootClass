package org.doit.ik;

import java.util.Arrays;

import org.doit.ik.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;


	/*// 더미 데이터 추가
	@Test
	void insertBoards() {

		IntStream.rangeClosed(1, 100).forEach(i->{

			Member member = Member.builder().email("user"+i+"@aaa.com").build();

			Board board= Board.builder()
					.title("title..."+i)
					.content("content..."+i)
					.writer(member)
					.build();

			this.boardRepository.save(board);
		});      

	}
	 */

	/*
	@Test
	@Transactional
	void testRead1() {
		// 100번 게시글 정보 조회(상세보기)
		Optional<Board> result = this.boardRepository.findById(100L);
		Board board = result.get();

		System.out.println(board);
		// Board(bno=100, title=title...99, content=content...99)

		System.out.println(board.getWriter());

		//Board(bno=100, title=title...100, content=content...100)
		//Member(email=user100@aaa.com, password=1111, name=USER100)
	}
	 */

	/*
	@Test
	void testReadWithWriter() {

		Object result = this.boardRepository.getBoardWithWriter(100L);
		Object [] arr = (Object[]) result;

		System.out.println("=".repeat(70));
		System.out.println(Arrays.toString(arr));

		//Board(bno=100, title=title...100, content=content...100)
		//Member(email=user100@aaa.com, password=1111, name=USER100)
	}
	*/
	/*
	@Test
	   public void testGetBoardWithReply() {

	      List<Object[]> result = this.boardRepository.getBoardWithReply(11L);
	      
	      System.out.println("=".repeat(70));
	      for (Object[] arr : result) {
	         System.out.println(Arrays.toString(arr));
	      }

	   }
	*/
	/*
	@Test
	   public void testWithReplyCount() {

	      Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
	      Page<Object[]> result = this.boardRepository.getBoardWithReplyCount(pageable);

	      result.get().forEach(row->{
	         Object [] arr = (Object[])row;
	         System.out.println("😘"+Arrays.toString(arr));
	      });

	   }
	   */
	
	@Test
	   public void testRead3() {
	      
	      Object result = this.boardRepository.getBoardByBno(11L);      
	      Object[] arr = (Object[])result;      
	      System.out.println(Arrays.toString(arr));
	      
	   }


}
