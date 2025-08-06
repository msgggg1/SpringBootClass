package org.doit.ik.sbb.answer;

import java.time.LocalDateTime;

import org.doit.ik.sbb.question.Question;
import org.doit.ik.sbb.user.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;

	// 답변 INSERT
    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
	        answer.setContent(content);
	        answer.setCreateDate(LocalDateTime.now());
	        answer.setQuestion(question);
	        answer.setAuthor(author); // 답변 작성자
        this.answerRepository.save(answer);
    }
    
}
