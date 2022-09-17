package com.spring.instagram;

import com.spring.instagram.library.Book;
import com.spring.instagram.library.BookRepository;
import com.spring.instagram.models.*;
import com.spring.instagram.student.Student;
import com.spring.instagram.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@SpringBootApplication
@EnableAspectJAutoProxy
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

	@Bean(name = "instagram")
	CommandLineRunner commandLineRunner(AccountRepository accountRepository,
										PostRepository postRepository,
										ResourceRepository resourceRepository,
										PostCommentRepository postCommentRepository,
										PostGoodRepository postGoodRepository) {
		return args -> {
			Account account1 = new Account(1L, "user1@example.com", "1234");
			Account account2 = new Account(2L, "user2@example.com", "1234");
			Account account3 = new Account(3L, "user3@example.com", "1234");
			accountRepository.saveAll(List.of(account1, account2, account3));

			Resource resource1 = new Resource(1L, "image/jpeg", new Date(), "https://picsum.photos/200/200?t=1");
			Resource resource2 = new Resource(2L, "image/jpeg", new Date(), "https://picsum.photos/200/200?t=2");
			Resource resource3 = new Resource(3L, "image/jpeg", new Date(), "https://picsum.photos/200/200?t=3");
			resourceRepository.saveAll(List.of(resource1, resource2, resource3));


			Post post1 = new Post("this is body1", account1, resource1, null, null, 0, new Date());
			Post post2 = new Post("this is body2", account3, resource2, null, null, 1, new Date());
			Post post3 = new Post("this is body3", account3, resource3, null, null, 2, new Date());
			postRepository.saveAll(List.of(post1, post2, post3));

			PostGood postGood1 = new PostGood(post2, account1, new Date());
			PostGood postGood2 = new PostGood(post3, account2, new Date());
			PostGood postGood3 = new PostGood(post3, account1, new Date());
			postGoodRepository.saveAll(List.of(postGood1, postGood2, postGood3));

			PostComment postComment1 = new PostComment(post2, account3, new Date(), "This is comment1");
			PostComment postComment2 = new PostComment(post1, account3, new Date(), "This is comment2");
			PostComment postComment3 = new PostComment(post1, account2, new Date(), "This is comment3");
			postCommentRepository.saveAll(List.of(postComment1, postComment2, postComment3));
//			Post post = new Post(1L, )
		};
	}
}
