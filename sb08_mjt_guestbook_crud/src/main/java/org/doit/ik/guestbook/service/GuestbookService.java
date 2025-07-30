package org.doit.ik.guestbook.service;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;

public interface GuestbookService {
	
	void modify(GuestbookDTO guestbookDTO);

	void remove(Long gno);

	GuestbookDTO read(Long gno);
	
	Long register(GuestbookDTO guestbookDTO);
	
	// DTO -> Entity 변환 메서드
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
							.gno(dto.getGno())
							.title(dto.getTitle())
							.content(dto.getContent())
							.writer(dto.getWriter())
							.build();
		return entity;
	}
	
	// Entity -> DTO 변환 메서드
	default GuestbookDTO entityToDto(Guestbook entity) {
		GuestbookDTO dto = GuestbookDTO.builder()
							.gno(entity.getGno())
							.title(entity.getTitle())
							.content(entity.getContent())
							.writer(entity.getWriter())
							.modDate(entity.getModDate())
							.regDate(entity.getRegDate())
							.build();
		return dto;
	}
	
	//
	PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);
}
