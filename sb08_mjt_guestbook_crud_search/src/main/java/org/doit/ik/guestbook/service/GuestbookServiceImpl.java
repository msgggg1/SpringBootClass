package org.doit.ik.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.entity.QGuestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

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
	
	private BooleanBuilder getSearchPredicate(PageRequestDTO requestDTO) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		String type = requestDTO.getType(); // tw twc c w t tc
		String keyword = requestDTO.getKeyword();
		
		// where gno>0 index 걸려서 성능 빨라짐. 
		BooleanExpression expr1 = qGuestbook.gno.gt(0L);
		booleanBuilder.and(expr1);
		
		if (type == null || type.trim().length()==0) {
			return booleanBuilder;
		}
		
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		String [] typeArr = type.split("");
		for (String t : typeArr) {
			if (t.equals("t")) {
				BooleanExpression exprTitle = qGuestbook.title.contains(keyword);
				conditionBuilder.or(exprTitle);
			} else if (t.equals("w")) {
				conditionBuilder.or(qGuestbook.writer.contains(keyword));
			} else if (t.equals("c")) {
				conditionBuilder.or(qGuestbook.content.contains(keyword));
			}
		}
		
		booleanBuilder.and(conditionBuilder);
		
		return booleanBuilder;
	}

	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
		log.info("👍👍👍GuestbookServiceImpl.getList()");
		
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		// 페이징 처리 O + 검색 기능 X 방명록 목록
		//Page<Guestbook> result = this.guestbookRepository.findAll(pageable);
		
		BooleanBuilder booleanBuilder = this.getSearchPredicate(requestDTO);
		Page<Guestbook> result = this.guestbookRepository.findAll(booleanBuilder, pageable);
		
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
