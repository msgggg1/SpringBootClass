package org.doit.ik.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.doit.ik.sbb.exception.DataNotFoundException;
import org.doit.ik.sbb.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	public List<Question> getList() {
		return this.questionRepository.findAll();
	}

	public Question getQuestion(Integer id) {  
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}

	// 질문 insert
	public void create(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user); // 질문작성자
		this.questionRepository.save(q);
	}

	public Page<Question> getList(int page) {
		Pageable pageable = PageRequest.of(page-1, 10, Sort.by("id").descending());
		return this.questionRepository.findAll(pageable);
	}

	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	public void delete(Question question) {
        this.questionRepository.delete(question);
    }


}