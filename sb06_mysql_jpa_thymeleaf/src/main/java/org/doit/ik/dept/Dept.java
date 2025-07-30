package org.doit.ik.dept;

import java.util.List;

import org.doit.ik.emp.Emp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "dept")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter // @data 사용 말기
public class Dept {
	
	@Id // PK
	/* mysql은 sequence 개념이 없음
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_deptno")
	@SequenceGenerator(name = "seq_deptno", // 이 클래스 내에서의 이름
						sequenceName = "seq_tbldept_deptno", // 오라클에 생성되는 시퀀스 이름
						initialValue = 1,
						allocationSize = 1)
						*/
	@GeneratedValue(strategy = GenerationType.IDENTITY) // mysql에는 시퀀스 개념이 없다. 
	private Integer deptno;
	@Column(length = 14, nullable = true)
	private String dname;
	@Column(length = 13, nullable = true)
	private String loc;
	
	
	// 생성자 2개짜리 추가 설정
	public Dept(String dname, String loc) {
		this.dname = dname;
		this.loc = loc;
	}
	
	// dept 조인(연결) emp
	// Dept 연관관계   Emp		1:1		[1:N]		N:1		N:M
	@OneToMany(mappedBy = "dept", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Emp> empList;
	
}
