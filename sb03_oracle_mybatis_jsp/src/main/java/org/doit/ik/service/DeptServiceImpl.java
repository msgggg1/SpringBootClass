package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.DeptVO;
import org.doit.ik.persistence.DeptMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor // final로 선언된 것 자동 주입
@Log4j2
public class DeptServiceImpl implements DeptService {
	
	public final DeptMapper deptMapper;

	@Override
	public List<DeptVO> selectDeptList() throws Exception {
		log.info("> 👍👍👍 DeptServiceImpl.selectDeptList() - 호출");
		return this.deptMapper.getDeptList();
	}

}
