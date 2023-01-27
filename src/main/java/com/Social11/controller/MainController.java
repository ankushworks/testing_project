package com.Social11.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	
	@GetMapping("admin")
	public String adminpage() {
		return "This page has restriced and can only be accessible by admin";
	}
	
}
