package org.doit.ik.board.entry;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Board extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;

	private String title;
	private String content;

	@ToString.Exclude
	@ManyToOne (fetch = FetchType.LAZY)
	private Member writer; // 작성자로 member 엔티티. 연관관계..

	public void changeTitle(String title) {   
		this.title = title;
	}

	public void changeContent(String content) {
		this.content = content;
	}

}
