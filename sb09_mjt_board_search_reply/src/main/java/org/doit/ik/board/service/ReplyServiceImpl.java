package org.doit.ik.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.doit.ik.board.dto.ReplyDTO;
import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Reply;
import org.doit.ik.board.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyRepository replyRepository;

	@Override
	   public Long register(ReplyDTO replyDTO) {
	      Reply reply = dtoToEntity(replyDTO);
	      this.replyRepository.save(reply); // entity
	      return reply.getRno();
	   }

	   @Override
	   public List<ReplyDTO> getList(Long bno) {
	      List<Reply> result = this.replyRepository.getRepliesByBoardOrderByRno(
	            Board.builder().bno(bno).build());
	      return result.stream().map(reply->entityToDTO(reply)).collect(Collectors.toList());
	   }

	   @Override
	   public void modify(ReplyDTO replyDTO) {
	      Reply reply = dtoToEntity(replyDTO);
	      this.replyRepository.save(reply);
	   }

	   @Override
	   public void remove(Long rno) {
	      this.replyRepository.deleteById(rno);
	   }     

}
