package org.doit.ik.sbb.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter   
@Entity
public class Question {
   
   @Id      // jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;   // 참고 create_date 컬럼명으로 만들어진다. 
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) 
    private List<Answer> answerList;   // 1:N 관계이기에 List<Answer>로 선언한다. 

}

// 참고 : CascadeType.REMOVE - SBB도 질문을 삭제하면 그에 달린 답변들도 모두 삭제
// 참고 : mappedBy = "question" <- Answer의 private Question ***question***; 
//       Answer 엔티티에서 Question 엔티티를 참조한 속성인 question을 mappedBy에 전달해야 한다.
// 참고 : 엔티티를 만들 때 Setter 메서드는 사용하지 않는다. QuestionDTO X
//       복잡도를 낮추고 원활한 설명을 위해 엔티티에 Setter 메서드를 추가하여 진행함을 기억
// 참고 : @Column 애너테이션을 사용하지 않더라도 테이블의 열로 인식한다.
// 참고 : 테이블의 열로 인식하고 싶지 않다면 @Transient 애너테이션을 사용한다.