package org.doit.ik.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.doit.ik.sbb.exception.DataNotFoundException;
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
    
    // 먼저 1) 답변 조회 후 답변 수정
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
    
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }
    
    // 답변 추가 
    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
    
}
