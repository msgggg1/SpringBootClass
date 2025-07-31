package org.doit.ik.board.controller;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor // final 선언 필수 DI
@Log4j2
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	// http://localhost/board/list?page=1&size=10&type=t&
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("😊 BoardController. list() " + pageRequestDTO);
		// PageResultDTO
		model.addAttribute("result", this.boardService.getList(pageRequestDTO));
	}

	@GetMapping("/register")
	public void register() {
		log.info("😊 BoardController. register() ");
	}

	@PostMapping("/register")
	public String register(BoardDTO dto, RedirectAttributes rttr) {      
		log.info("👌 BoardController.register()...POST" + dto);
		Long bno = this.boardService.register(dto);
		rttr.addFlashAttribute("msg", bno);
		return "redirect:/board/list";
	}

	// localhost/board/read?bno
	// localhost/board/modify
	@GetMapping(value = {"/read", "/modify"})   
	public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO
			, @RequestParam("bno") Long bno, Model model) {   // 283      
		log.info("👌 BoardController.read/modify()...GET");
		BoardDTO boardDTO = this.boardService.get(bno);
		model.addAttribute("dto", boardDTO);
	}

	@PostMapping("/modify") 
	public String modify(BoardDTO dto
			, @ModelAttribute("requestDTO") PageRequestDTO requestDTO
			, RedirectAttributes rttr) {
		log.info("👌 BoardController.modify()...POST" + dto);      
		this.boardService.modify(dto);      
		
		// 아래 없어도 됨 @modalattribute
		rttr.addAttribute("page", requestDTO.getPage());
		rttr.addAttribute("type", requestDTO.getType());
		rttr.addAttribute("keyword", requestDTO.getKeyword());      
		rttr.addAttribute("bno", dto.getBno());      
		
		return "redirect:/board/read";
	}
	
	@PostMapping("/remove")
	public String remove( @RequestParam("bno") Long bno
					,RedirectAttributes rttr) {
		log.info("👌 BoardController.delete()...GET");     
		this.boardService.removeWithReplies(bno);
		rttr.addFlashAttribute("msg", bno);
		
		return "redirect:/board/list";
	}

}
