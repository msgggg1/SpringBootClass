package org.doit.ik;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.doit.ik")
public class Sb02MybaitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb02MybaitsApplication.class, args);
	}

}
