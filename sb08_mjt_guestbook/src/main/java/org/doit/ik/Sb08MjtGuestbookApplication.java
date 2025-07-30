package org.doit.ik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener를 활성화시키기 위해 추가
public class Sb08MjtGuestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb08MjtGuestbookApplication.class, args);
	}

}
