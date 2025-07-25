package org.doit.ik.controller;

import org.doit.ik.persistence.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private TimeMapper timeMapper;
	
	@GetMapping("/list")
	public void list(Model model) {
		System.out.println(" > BoardController.list() - 호출");
		model.addAttribute("now", this.timeMapper.getTime());
	}
	
}
