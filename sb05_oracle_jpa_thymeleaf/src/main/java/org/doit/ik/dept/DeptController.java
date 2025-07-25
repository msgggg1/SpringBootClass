package org.doit.ik.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@GetMapping(value = {"/dept/list"})
	public ModelAndView deptList() {
		log.info("👍👍👍 DeptController.deptList() 호출");
		
		ModelAndView mv = new ModelAndView();
		
		List<Dept> depts = this.deptService.getDepts();
		mv.addObject("depts", depts);
		
		mv.setViewName("dept/list"); // templates/dept/list.html 호출
		return mv;
	}

	@GetMapping(value = {"/dept/insert"})
	public ModelAndView deptInsert(Dept dept) { // entity
		log.info("👍👍👍 DeptController.deptInsert() 호출");
		ModelAndView mv = new ModelAndView();
		
		Dept saveDept = this.deptService.saveDept(dept); // 엔터티
		mv.addObject("saveDept", saveDept);
		
		mv.setViewName("redirect:/dept/list"); 
		return mv;
	}
	
	@GetMapping(value = {"/dept/delete"})
	public ModelAndView deptDelete(@RequestParam("deptno") Integer deptno) { // entity
		log.info("👍👍👍 DeptController.deptDelete() 호출");
		ModelAndView mv = new ModelAndView();
		
		this.deptService.deleteDept(deptno); // 엔터티
		
		mv.setViewName("redirect:/dept/list"); 
		return mv;
	}
	
	@GetMapping(value = {"/dept/update"})
	public ModelAndView deptUpdate(Dept dept) { // entity
		log.info("👍👍👍 DeptController.deptUpdate() 호출");
		ModelAndView mv = new ModelAndView();
		
		this.deptService.updateDept(dept); // 엔터티
		
		mv.setViewName("redirect:/dept/list"); 
		return mv;
	}
	
	
}
