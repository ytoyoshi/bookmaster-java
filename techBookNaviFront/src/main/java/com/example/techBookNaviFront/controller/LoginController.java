package com.example.techBookNaviFront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login") // URLマッピング
	public String login(Model model) {
		// login.htmlを返す
		return "login";
	}

	// デフォルトページのリダイレクト
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
}