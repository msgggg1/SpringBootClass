package org.doit.ik.persistence;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	public String getTimeXML();
	
}
