package org.doit.ik.guestbook.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PageRequestDTO {
	
	// localhost/guestbook/list?page=1&size=10&type=t&keyword=xxx
	private int page; // 현재 페이지 번호
	private int size; // 한 페이지에 출력할 게시글 수
	// 검색 관련 추가
	private String type;
	private String keyword;
	
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	// 덤. 입력값 안넘어오면 기본값
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page-1, size, sort);
	}
	
	
}
