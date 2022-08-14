package com.spring.instagram;

import com.spring.instagram.library.Book;
import com.spring.instagram.library.BookRepository;
import com.spring.instagram.student.Student;
import com.spring.instagram.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SpringBootApplication
public class InstagramApplication {

	@GetMapping("/")
	String helloworld() {
		return "OK.";
	}

	public static void main(String[] args) {
		SpringApplication.run(InstagramApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student maria = new Student(
					"Maria",
					"Jones",
					"maria.jones@example.com",
					21
			);
			studentRepository.save(maria);
		};
	}

	@Bean(name = "book")
	CommandLineRunner commandLineRunner(BookRepository bookRepository) {
		return args -> {
			Book book = new Book(
					"this is title",
					"this is author",
					new Date()
			);
			bookRepository.save(book);
		};
	}
}
