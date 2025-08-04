package org.doit.ik.mreview.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass //  엔티티마다 공통으로 있어야하는 필드 따로 빼겠다. 테이블로 따로 만들어지지 않는다.
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
public class BaseEntity {
	@CreatedDate
	@Column(name="regdate", updatable =false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime modDate;
}
