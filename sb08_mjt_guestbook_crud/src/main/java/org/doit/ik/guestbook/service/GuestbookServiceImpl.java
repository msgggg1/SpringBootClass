package org.doit.ik.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

	private final GuestbookRepository guestbookRepository;
	
	@Override
	public Long register(GuestbookDTO guestbookDTO) {
		log.info("👍👍👍 GuestbookServiceImpl.register()");
		// DTO -> Entity 변환
		Guestbook entity = this.dtoToEntity(guestbookDTO);
		this.guestbookRepository.save(entity); // entity manager에 의해 저장하는 작업 + entity 살아있음
		return entity.getGno();
	}

	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
		log.info("👍👍👍GuestbookServiceImpl.getList()");
		
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		Page<Guestbook> result = this.guestbookRepository.findAll(pageable);
		
		Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
		// Page<Guestbook> -> List<GuestbookDTO>
		return new PageResultDTO<>(result, fn);
	}

	@Override
	public void modify(GuestbookDTO guestbookDTO) {
		Optional<Guestbook> result = this.guestbookRepository.findById(guestbookDTO.getGno());
		if (result.isPresent()) {
			 Guestbook entity = result.get();
			 entity.changeTitle(guestbookDTO.getTitle());
			 entity.changeContent(guestbookDTO.getContent());
			 this.guestbookRepository.save(entity);
		}// if
	}

	@Override
	public void remove(Long gno) {
		log.info("👍👍 GuestbookServiceImpl.remove()");
		
		this.guestbookRepository.deleteById(gno);
		
	}

	@Override
	public GuestbookDTO read(Long gno) {
		log.info("👍👍 GuestbookServiceImpl.read()");
		
		Optional<Guestbook> result = this.guestbookRepository.findById(gno);
		return result.isPresent() ? entityToDto(result.get()) : null ;
	}

}
