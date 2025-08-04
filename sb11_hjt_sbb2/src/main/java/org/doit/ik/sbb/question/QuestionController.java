package org.doit.ik.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	/* [1]
	private final QuestionRepository questionRepository;

    @GetMapping("/list")
    public void list(Model model) {
    	List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
    }
	 */

	private final QuestionService questionService;

	@GetMapping("/list")
	public void list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
	}

	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
		return "/question/detail";
	}

	@GetMapping("/create")
	//public void questionCreate() {        
	public void questionCreate() {
	}

	@PostMapping("/create")
	public String questionCreate(
								@RequestParam(value="subject") String subject, 
								@RequestParam(value="content") String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
	}

}