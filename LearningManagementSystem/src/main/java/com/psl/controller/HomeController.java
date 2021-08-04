package com.psl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/homepage")
	public String sayHello(){
		return "Hello World!";
	}

}
