package org.doit.ik.sbb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// security-context.xml 동일
@Configuration // 스프링 환경 설정 파일
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티 제어
@EnableMethodSecurity(prePostEnabled = true) // @PreAuthorize 애너테이션이 동작할 수 있도록
public class SecurityConfig {
	@Bean 
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // 필터 적용
		http
		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
				// /h2-console/시작하는 모든 요청 URL은 CSRF 검증을 하지 않는다.
				.csrf((csrf) -> csrf
	                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
				.headers((headers) -> headers
		                .addHeaderWriter(new XFrameOptionsHeaderWriter(
		                		// 스프링 부트에서 X-Frame-Options 헤더는 클릭재킹 공격을 막기 위해 사용한다. 
		                		// 클릭재킹은 사용자의 의도와 다른 작업이 수행되도록 속이는 보안 공격 기술이다.
		                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
				.formLogin((formLogin) -> formLogin
						.loginPage("/user/login")
						.defaultSuccessUrl("/"))
				.logout((logout) -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true))
		;
		return http.build();
	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
