package org.doit.ik.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MemoController {

	@Autowired
	private MemoRepository memoRepository;
	
	@GetMapping("/memo/list/{page}")
	public String memoList(@PathVariable("page") int page, Model model) {
		log.info("👍 MemoController.memoList...page :" + page);

		Sort sort = Sort.by("mno").descending();
		Pageable pageable = PageRequest.of(page-1, 10, sort);
		Page<Memo> result = this.memoRepository.findAll(pageable);
		
		// 총 페이지 수
		int totalPages = result.getTotalPages();
		int blockSize = 5;		// < [1] 2 3 >
		int currentBlock = (int) Math.ceil((double) page / blockSize);
	    int startPage = (currentBlock - 1) * blockSize + 1;
	    int endPage = Math.min(startPage + blockSize - 1, totalPages);
		
	    model.addAttribute("result", result);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "/memo/list";
	}
}


