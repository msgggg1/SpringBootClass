package org.doit.ik.controller;

import java.util.List;

import org.doit.ik.domain.DeptVO;
import org.doit.ik.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@GetMapping("/list")
	public ModelAndView list() throws Exception {
		log.info("> >>>>>>> DeptController.list()-호출됨");
		
		ModelAndView mv = new ModelAndView();
		List<DeptVO> list = deptService.selectDeptList();
		mv.addObject("list", list);
		mv.setViewName("/dept/list");
		return mv;
		
	}
}
