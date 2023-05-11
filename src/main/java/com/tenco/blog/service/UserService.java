package com.tenco.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public int createUser(User user) {
		
		user.setRole("user");
		userRepository.save(user);
		
		try {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setRole("user");
			user.setPassword(encPassword);
			userRepository.save(user);
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public User readUser(User user) {
		
		// 필요한 기능을 JPA가 제공하지 않는 경우 직접 만들어야 함
		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
		
		return userEntity;
	}
	
}
