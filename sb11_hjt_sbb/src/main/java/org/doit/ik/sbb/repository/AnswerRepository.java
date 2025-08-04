package org.doit.ik.sbb.repository;

import org.doit.ik.sbb.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	
}
