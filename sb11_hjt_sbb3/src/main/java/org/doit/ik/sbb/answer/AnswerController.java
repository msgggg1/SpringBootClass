package org.doit.ik.sbb.answer;

import org.doit.ik.sbb.question.Question;
import org.doit.ik.sbb.question.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	 private final QuestionService questionService;
	 private final AnswerService answerService;
	
	 @PostMapping("/create/{id}")
		public String createAnswer(Model model
							, @PathVariable("id") Integer id
							, @RequestParam(value="content") String content
							, @Valid AnswerForm answerForm
							, BindingResult bindingResult) {
			Question question = this.questionService.getQuestion(id); // 답변이 있는지 먼저 확인
			
			if (bindingResult.hasErrors()) {
				model.addAttribute("question", question);
	            return "/question/detail";
	        }
			
			// TODO: 답변을 저장한다. 
			this.answerService.create(question, answerForm.getContent());
			return String.format("redirect:/question/detail/%s", id);
		}
	 
	 /* [1] Spring Boot validation X
	//	/answer/create/${question.id}
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
		Question question = this.questionService.getQuestion(id); // 답변이 있는지 먼저 확인
		// TODO: 답변을 저장한다. 
		this.answerService.create(question, content);
		return String.format("redirect:/question/detail/%s", id);
	}
	*/
}
