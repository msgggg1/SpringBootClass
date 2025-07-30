package org.doit.ik.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class IndexController {
	@GetMapping("/index")
	public void index() {
		log.info("IndexController.index() 호출");
	}
}
