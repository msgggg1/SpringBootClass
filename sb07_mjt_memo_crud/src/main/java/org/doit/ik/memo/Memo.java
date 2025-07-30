package org.doit.ik.memo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tbl_memo")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Memo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mno;
	
	// 실제 테이블에 컬럼으로 생성되기를 원하지 않을 경우 @Transient
	@Column(length = 30, nullable = false)
	private String memoText;
	
}
/*
naming:
    physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
*/