package org.doit.ik.board.entity;

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
public class Reply extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	
	private String text;
	private String replyer; // 회원이 아니여도 댓글 쓸 수 있게
	
	@ToString.Exclude //★
	@ManyToOne(fetch = FetchType.LAZY) //★
	@JoinColumn(name = "bno")
	private Board board;
}
