package org.doit.ik.board.dto;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class PageRequestDTO {
	private int page;
	private int size;

	private String type;   // 추가
	private String keyword;   // 추가

	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}

	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page-1, size, sort);
	}
}
