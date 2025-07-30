package org.doit.ik.guestbook.controller;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.service.GuestbookService;
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
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
	
	private final GuestbookService guestbookService;
	
	@GetMapping("/list")
	// @ModealAttribute 자동 전달
	public void list(PageRequestDTO pageRequestDTO , Model model) {
		log.info("👍GuestbookController.list()");
		model.addAttribute("result", this.guestbookService.getList(pageRequestDTO));
		//model.addAttribute("pageRequestDTO", pageRequestDTO); // 안써도 뷰에 전달, 바인딩
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("👍 GuestbookController.register()... GET");
		
	}

	@PostMapping("/register")
	public String register(GuestbookDTO dto, RedirectAttributes rttr) {
		log.info("👍 GuestbookController.register()... POST");
		Long gno = this.guestbookService.register(dto);
		rttr.addFlashAttribute("msg", gno);
		return "redirect:/guestbook/list";
		
	}

	@PostMapping("/modify")
	public String modify(GuestbookDTO dto
						, @ModelAttribute("requestDTO") PageRequestDTO requestDTO
						, RedirectAttributes rttr) {
		log.info("👍 GuestbookController.modify()... POST");
		this.guestbookService.modify(dto);
		//rttr.addFlashAttribute("msg", gno);
		rttr.addAttribute("page", requestDTO.getPage());
		rttr.addAttribute("gno", dto.getGno());
		
		return "redirect:/guestbook/read";
		
	}
	
	// /guestbook/read?gno=302&page=3
	@GetMapping({"/read","/modify"})
	public void read(@RequestParam("gno") Long gno
						, @ModelAttribute("requestDTO") PageRequestDTO requestDTO
						, Model model) {
		log.info("👍 GuestbookController.read()... GET");
		GuestbookDTO dto = this.guestbookService.read(gno);
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("gno") Long gno, RedirectAttributes rttr) {
		log.info("👍 GuestbookController.register()... POST");
		this.guestbookService.remove(gno);
		rttr.addFlashAttribute("msg", gno);
		return "redirect:/guestbook/list";
		
	}
	
	
	
}







