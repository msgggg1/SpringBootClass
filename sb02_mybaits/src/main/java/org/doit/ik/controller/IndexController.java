package org.doit.ik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class IndexController {
	@GetMapping("/index")
	public void index() {
		System.out.println(">IndexController.index()-호출됨");
	}
}
