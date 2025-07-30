package org.doit.ik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener를 활성화시키기 위해 추가
public class sb08MjtGuestbookCrudSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(sb08MjtGuestbookCrudSearchApplication.class, args);
	}

}
