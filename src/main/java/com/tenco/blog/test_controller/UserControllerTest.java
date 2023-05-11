package com.tenco.blog.test_controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

@Controller
public class UserControllerTest {

	@Autowired
	private UserRepository userRepository;
	
	// 1. key=value 
	@PostMapping("/temp/join")
	public String temp1(String username, String password, String email) {
		
		User reqUser = new User();
		reqUser.setUsername(username);
		reqUser.setPassword(password);
		reqUser.setEmail(email);
		
		return "/index";
	}
	
	@PostMapping("/temp/join2")
	@ResponseBody // **** 페이지 리턴이 아니라 데이터를 리턴하라 (비동기 통신 시 필요한 어노테이션)
	public User temp2(@RequestBody User reqUser) {
		
		System.out.println(reqUser.toString());
		userRepository.save(reqUser);
		
		return reqUser;
	}
	
	
	@PutMapping("/temp/update")
	@Transactional
	@ResponseBody // **** 페이지 리턴이 아니라 데이터를 리턴하라 (비동기 통신 시 필요한 어노테이션)
	public User temp3(@RequestBody User reqUser) {
		
		User userEntity = userRepository.findById(reqUser.getId()).get();
		userEntity.setPassword(reqUser.getPassword());
		
		return userEntity;
	}
	
	@DeleteMapping("/temp/delete")
	@ResponseBody // **** 페이지 리턴이 아니라 데이터를 리턴하라 (비동기 통신 시 필요한 어노테이션)
	public void temp4(@RequestParam Integer id) {
		
		userRepository.deleteById(id);
		
	}
	
	@GetMapping("/temp/index")
	public String indexPage() {
		return "/index";
	}
	
}
