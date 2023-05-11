package com.tenco.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tenco.blog.auth.PrincipalDetail;
import com.tenco.blog.auth.PrincipalDetailService;


/**
 * 
 * 권한 부여 페이지 설정
 * /auth/**
 *
 */

@Configuration // IoC 등록
@EnableWebSecurity // 시큐리티 필터로 등록해라 (필터 커스텀)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증 처리를 미리하겠다는 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	// 비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	// 특정 주소 필터를 설정할 예정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); // csrf 토큰 비활성화 처리 (테스트 시 사용 권장) => 보안 향상
												// 여기에 등록하지 않으면 나머지는 다 시큐리티가 막음
		http.authorizeHttpRequests().antMatchers("/auth/**", "/", "/js/**", "/css/**", "/images/**")
		.permitAll() // 어떤 요청이든 권한을 모두 부여
		.anyRequest()
		.authenticated() 
		.and()
		.formLogin()
		.loginPage("/auth/loginPage") // 시큐리티 로그인 페이지를 우리가 만든 페이지로 커스텀
		.loginProcessingUrl("/auth/loginProc")
		.defaultSuccessUrl("/"); 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
		super.configure(auth);
	}
	
}
