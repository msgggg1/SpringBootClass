package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.DeptVO;

public interface DeptService {
	
	public List<DeptVO> selectDeptList() throws Exception;
	
}
