package com.spring.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class InstagramApplication {

	@GetMapping("/")
	String hellworld() {
		return "OK.";
	}

	public static void main(String[] args) {
		SpringApplication.run(InstagramApplication.class, args);
	}

}
