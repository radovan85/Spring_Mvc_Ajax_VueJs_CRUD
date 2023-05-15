package com.radovan.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="/home")
	public String home() {
		return "fragments/homePage :: ajaxLoadedContent";
	}
	
	
}
