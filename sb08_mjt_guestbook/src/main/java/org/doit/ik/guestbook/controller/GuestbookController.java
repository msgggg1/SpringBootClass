package org.doit.ik.guestbook.controller;

import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
	
	private final GuestbookService guestbookService;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO , Model model) {
		log.info("👍GuestbookController.list()");
		model.addAttribute("result", this.guestbookService.getList(pageRequestDTO));
	}
	
}







