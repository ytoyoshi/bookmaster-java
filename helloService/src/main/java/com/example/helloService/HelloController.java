package com.example.helloService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloService.service.HelloService;

@RestController
public class HelloController {
	
	private final HelloService helloService;
	
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/api/hello")
    public String hello() {
    	
    	String message = helloService.getHelloMessage("en");
    	
    	return message;
    }
}