package org.doit.ik;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.doit.ik.persistence")
public class Sb03OracleMybatisJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb03OracleMybatisJspApplication.class, args);
	}

}
