package org.doit.ik.sbb.question;

import java.util.List;

import org.doit.ik.sbb.answer.AnswerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
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
	public String detail(Model model
						, @PathVariable("id") Integer id
						, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
		return "/question/detail";
	}

	@GetMapping("/create")
	//public void questionCreate() {        
	public void questionCreate(QuestionForm questionForm) { // 없으면 오류나서 추가.
		
	}

	// [2] Spring Boot Validation 라이브러리 O
	@PostMapping("/create")
	public String questionCreate(
							@Valid QuestionForm questionForm
							, BindingResult bindingResult // 위 코딩과 앞 뒤 순서가 바뀌면 안됨
							) {
		if (bindingResult.hasErrors()) {
            return "/question/create";
        }       
            
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
	}
	
	/*
	// [1] Spring Boot Validation 라이브러리 X
	@PostMapping("/create")
	public String questionCreate(
								@RequestParam(value="subject") String subject, 
								@RequestParam(value="content") String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
	}
	*/
	

}