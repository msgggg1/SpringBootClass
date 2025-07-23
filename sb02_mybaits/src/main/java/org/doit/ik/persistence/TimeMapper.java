package org.doit.ik.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface TimeMapper {
	
	public String getTime();
	
	@Select("SELECT SYSDATE FROM dual")
	public String getTime2();
	
}
