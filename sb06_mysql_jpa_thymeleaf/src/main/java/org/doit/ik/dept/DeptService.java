package org.doit.ik.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
		
	@Autowired
	private DeptRepository deptRepository;
	
	public List<Dept> getDepts(){
		
		return this.deptRepository.findAll();
	}

	public Dept saveDept(Dept dept) { //entity
		
		return this.deptRepository.save(dept); // 기본제공 메서드
	}

	public void deleteDept(Integer deptno) {
		this.deptRepository.deleteById(deptno); // 기본제공 메서드
	}

	public void updateDept(Dept dept) {
		//this.deptRepository.updateDept(null, null, null)
		
		//save() - insert(), update() - deptno 유무 -> 업데이트 시에도 사용. 자동으로 이미 존재하는 경우 업데이트 일어남
		Dept d = this.deptRepository
					.findById(dept.getDeptno())
					.orElseThrow(()-> new RuntimeException("부서 없음!!!"));
		
		this.deptRepository.save(dept);
	}
	
	public Page<Dept> getDeptsByPage(int pageNum, int pageSize){
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return deptRepository.findAll(pageable);
	}
	
	// 정렬
	public Page<Dept> getDeptsByPageSorted(int pageNum, int pageSize) { 
        Sort sort = Sort.by("dname").ascending(); 
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort); 
        return deptRepository.findAll(pageable);
    }
	
}
