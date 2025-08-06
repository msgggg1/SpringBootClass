package org.doit.ik.sbb.answer;

import java.time.LocalDateTime;

import org.doit.ik.sbb.question.Question;
import org.doit.ik.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT") // 글자 수 제한 없음. 문자열
    private String content;

    // @Transient 이 필드는 테이블의 컬럼으로 추가되지 않는다. 
    private LocalDateTime createDate; 

    @ManyToOne   // FK생성, Question(부모T)-Answer(자식T)
    private Question question;  
    
    @ManyToOne
    private SiteUser author;
    
    private LocalDateTime modifyDate;
}
