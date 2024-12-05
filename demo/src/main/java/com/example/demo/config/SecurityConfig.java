package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링 설정 클래스 지정, 등록된 Bean 생성 시점
@EnableWebSecurity // 스프링 보안 활성화
public class SecurityConfig { // 스프링에서 보안 관리 클래스
    @Bean // 명시적 의존성 주입 : Autowired와 다름
    // 5.7버전 이전 WebSecurityConfigurerAdapter 사용
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .headers(headers -> headers
            .addHeaderWriter((request, response) -> {
                response.setHeader("X-XSS-Protection", "1; mode=block"); // XSS-Protection 헤더설정
            })
            )
            //버전이 달라서 withDefaults()가 안 먹어서 이렇게 바꿨습니다. 
            .csrf(csrf -> csrf
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/**")) // CSRF 보호 활성화
            )
            .sessionManagement(session -> session
                        .invalidSessionUrl("/session-expired") // 세션만료시이동페이지
                        .maximumSessions(1) // 사용자별세션최대수
                        .maxSessionsPreventsLogin(true) // 동시세션제한
            );
        return http.build(); // 필터 체인을 통해 보안설정(HttpSecurity)을 반환
    }
    
    @Bean // 암호화 설정
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화 저장
    }       

    
}

