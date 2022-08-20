package com.spring.instagram.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/book")
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
//http://localhost:8082/book
    @GetMapping
    public List<Book> bookList() {
        return this.bookRepository.findAll();
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable Long id) {
        Optional<Book> bookOptional = this.bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        }
//        https://stackoverflow.com/a/17385946
        return null;
    }

    @Transactional
    @PostMapping
    public String createBook(@RequestBody Book book) {
        try {
            this.bookRepository.save(book);
            return "OK";
        } catch (Exception err) {
            return "Fail";
        }
    }

    @Transactional
    @PutMapping
    public String updateBook(Book book) {
        try {
            Optional<Book> bookOptional = this.bookRepository.findById(book.getId());
            if (bookOptional.isPresent()) {
                Book updateBook = bookOptional.get();
                updateBook.setAuthor(book.getAuthor());
                updateBook.setTitle(book.getTitle());
                updateBook.setPublishTime(book.getPublishTime());

            } else {
                return "Fail";
            }
        } catch (Exception err) {
            return "Fail";
        }
        return "OK";
    }

    @DeleteMapping(path = "{id}")
    public String deleteBook(@PathVariable Long id) {
        try {
            Optional<Book> bookOptional = this.bookRepository.findById(id);
            if (bookOptional.isPresent()) {
                this.bookRepository.delete(bookOptional.get());
            } else {
                return "Fail";
            }
        } catch(Exception err) {
            return "Fail";
        }
        return "OK";
    }
}
