package org.doit.ik.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResultDTO<DTO, EN> { // 제네릭 클래스 - 컴파일 될때 타입이 결정
	private int totalPage;
	private int page;
	private int size;
	private int start, end;
	private boolean prev, next;
	private List<Integer> pageList;  // 1 2 [3] 4 5 6 7 8 9  10 을 저장
	
	private List<DTO> dtoList;
	
	// 생성자
	// Page<EN> -> List<DTO> 변환 
	// Page<Guestbook> -> List<GuestbookDTO> 변환 
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){      
	      this.dtoList = result.stream()
	                  .map(fn) // Stream<GuestbookDTO>
	                  .collect(Collectors.toList()); // List<GuestbookSTO>
	      
	      totalPage = result.getTotalPages(); 
	      makePageList(result.getPageable()); 
	   }
	
	private void makePageList(Pageable pageable) {
	      this.page = pageable.getPageNumber()+1;
	      this.size = pageable.getPageSize();
	      int tempEnd = (int)(Math.ceil(page/10.0))*10;
	      start = tempEnd - 9;
	      prev = start > 1;
	      end = totalPage > tempEnd  ? tempEnd : totalPage;
	      next = totalPage > tempEnd;
	      pageList = IntStream.rangeClosed(start, end)
	    		  	.boxed() // Stream<Integer>
	    		  	.collect(Collectors.toList()); // List<Integer>
	   }
	
}
