package org.doit.ik.board.service;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.dto.PageResultDTO;
import org.doit.ik.board.entry.Board;
import org.doit.ik.board.entry.Member;

public interface BoardService {

	// dto -> entity
	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder().email(dto.getWriterEmail()).build();
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		return board;      
	}

	// entity -> dto
	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {   // p264
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())
				.build();
		return boardDTO;

	}

	// [1] 게시글 등록
	Long register(BoardDTO dto);

	// [2] 게시글 목록
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

	// [3] 게시글 삭제
	void removeWithReplies(Long bno);   

	// [4] 게시글 상세보기
	BoardDTO get(Long bno);   

	// [5] 게시글 수정
	void modify(BoardDTO boardDTO);

}
