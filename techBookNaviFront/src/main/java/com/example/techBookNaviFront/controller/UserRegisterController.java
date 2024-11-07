package com.example.techBookNaviFront.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.techBookNaviFront.client.UserServiceClient;
import com.example.techBookNaviFront.form.UserRegisterForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserRegisterController {
	private final  UserServiceClient userServiceClient;
	
	@GetMapping("/userRegister")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "userRegister";
    }
    
    @PostMapping("/userRegister")
    public String userRegiste(@Valid UserRegisterForm form,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            return "userRegister";
        }
        
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("error", "パスワードが一致しません");
            return "userRegister";
        }
        
        try {
            userServiceClient.registerUser(form);
            model.addAttribute("message", "登録が完了しました。ログインしてください。");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "userRegister";
        }
    }

}
