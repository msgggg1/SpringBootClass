package org.doit.ik;


import java.util.List;

import javax.sql.DataSource;

import org.doit.ik.domain.DeptVO;
import org.doit.ik.persistence.DeptMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.java.Log;

@SpringBootTest
@Log
class Sb03OracleMybatisJspApplicationTests {
	
	@Autowired
	DataSource dataSource;
	
	@ Autowired
	private DeptMapper deptMapper;

	@Test
	void testDeptMapper() throws Exception {
		List<DeptVO> result = this.deptMapper.getDeptList();
		result.forEach(vo ->{
			System.out.println("👍👍" + vo);
		});
		
	}
	
	/*
	@Test
	void testConnection() {
		try (Connection con = this.dataSource.getConnection()){
			log.info("👍👍👍👍👍👍👍👍👍Connection : " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/

}
