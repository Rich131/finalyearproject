package com.richardmurphy.finalyearproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHomeFromRoot() {
		return "home";
	}
	
	@RequestMapping("/home")
	public String showHome() {
		return "home";
	}
	
}
