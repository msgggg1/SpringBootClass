package org.doit.ik.guestbook.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class Guestbook extends BaseEntity{
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long gno;
   
   @Column(length = 100, nullable = false)
   private String title;
   
   @Column(length = 1500, nullable = false)
   private String content;
   
   @Column(length = 50, nullable = false)
   private String writer;

   /* BaseEntity.java
   @Column(name = "regdate", updatable = false)
   private LocalDateTime regDate;
   
   @Column(name = "moddate")
   private LocalDateTime modDate;
   */
   
   public void changeTitle(String title) {
	   this.title = title;
   }

   public void changeContent(String content) {
	   this.content = content;
   }
}






