package org.doit.ik.mreview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Review extends BaseEntity{                                       
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long reviewnum;
   
   @ToString.Exclude
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "mno")
   private Movie movie;
   
   @ToString.Exclude
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "mid")
   private Member member;
   
   private int grade;
   private String text;
    
}