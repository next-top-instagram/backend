package com.spring.instagram.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
//http://localhost:8082/book
    @GetMapping(path = "book")
    public List<Book> bookList() {
        return this.bookRepository.findAll();
    }
}
