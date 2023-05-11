package com.tenco.blog.test_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/temp")
	public String tempPage() {
		return "/temp";
	}
	
}
