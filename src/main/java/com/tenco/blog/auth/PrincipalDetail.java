package com.tenco.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tenco.blog.model.User;

public class PrincipalDetail implements UserDetails {

	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	/**
	 * 계정의 권한을 반환함
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// "ROLE_" 스프링 시큐리티 규칙 (꼭 넣어야 함)
		// "ROLE_" + user.getRole();
		
		Collection<GrantedAuthority> collections = new ArrayList<>();
		
		collections.add(() -> {
			return "ROLE_" + user.getRole();
		});
		
		return collections;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/**
	 * 계정이 만료되지 않았는지 여부를 리턴함
	 * true <- 만료되지 않음
	 * false <- 계정 만료됨
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 계정 잠김 여부를 리턴함
	 * true <- 사용 가능
	 * false <- 사용 불가능
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 비밀번호 만료 여부를 알려줌
	 * true <- 사용 가능
	 * false <- 사용 불가능
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 계정 활성화 여부를 알려줌
	 * true <- 사용 가능
	 * false <- 로그인 불가능
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
