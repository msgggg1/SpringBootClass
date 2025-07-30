package org.doit.ik.guestbook.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass // 해당 어노테이션이 적용된 클래스는 테이블로 생성되지 않는다.
@EntityListeners(value = { AuditingEntityListener.class })   // 엔티티 생명주기 이벤트에 따라 자동으로 날짜/시간 등을 처리
@Getter
public class BaseEntity {

   @CreatedDate   // 엔티티 최초 생성 시 자동 세팅
   @Column(name = "regdate", updatable = false)
   private LocalDateTime regDate;
   
   @LastModifiedDate  // 엔티티 업데이트 시 자동 갱신
   @Column(name = "moddate")
   private LocalDateTime modDate;

}
