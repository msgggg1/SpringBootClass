package org.doit.ik.board.service;

import java.util.function.Function;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.dto.PageResultDTO;
import org.doit.ik.board.entry.Board;
import org.doit.ik.board.entry.Member;
import org.doit.ik.board.repository.BoardRepository;
import org.doit.ik.board.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

	// 자동 의존성 주입 (DI)
    private final ReplyRepository replyRepository;
	private final BoardRepository boardRepository;

	@Override
	public Long register(BoardDTO dto) {
		log.info("👍 BoardServiceImpl.register()");
		// dto -> entity 변환
		Board entity = dtoToEntity(dto);
		this.boardRepository.save(entity);
		return entity.getBno();
	}

	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info("😊 BoardServiceImpl.getList()" + pageRequestDTO);
		
		Function<Object[], BoardDTO> fn = (en -> entityToDTO( (Board)en[0], (Member)en[1], (Long)en[2] ));
	      Page<Object[]> result = this.boardRepository.getBoardWithReplyCount(
	            pageRequestDTO.getPageable(Sort.by("bno").descending())
	            );
	            
	      // pagable<Object[]> -> PageResultDTO
	      return new PageResultDTO<>(result, fn);
	}

	@Transactional
	@Override
	public void removeWithReplies(Long bno) {
		// (주의) 게시글 삭제 + 해당 게시글의 댓글 삭제
		//		순서) 댓글 먼저 삭제 후 게시글 삭제
		//this.replyRepository.deleteById(bno);
		this.replyRepository.deleteByBno(bno);
		this.boardRepository.deleteById(bno);
	}

	@Override
	public BoardDTO get(Long bno) {
		Object result = this.boardRepository.getBoardByBno(bno);
		// b, w, count(r)
		Object [] arr = (Object [])result;
		return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}

	@Override
	public void modify(BoardDTO boardDTO) {
		// dto -> entity 변환
		Board entity = this.boardRepository.getReferenceById(boardDTO.getBno());
		if (entity != null) {
			entity.changeTitle(boardDTO.getTitle());
			entity.changeContent(boardDTO.getContent());
			this.boardRepository.save(entity);
		}
		
	}

	
	
}
