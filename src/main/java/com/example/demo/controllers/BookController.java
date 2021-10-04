package com.example.demo.controllers;

import com.example.demo.models.Book;
import com.example.demo.services.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping({"/", "/books"})
    public List<Book> getAllBooks() {
        return this.bookServices.findAll();
    }

    @GetMapping("/book")
    public List<Book> getBookById(@RequestParam int id) throws InterruptedException {
        System.out.println(id);
        var books = this.bookServices.findAll();
        if (id == 1) {
            Thread.sleep(5000);
        }
        return books.stream().filter(book -> {
            return book.getName().contains(String.valueOf(id));
        }).collect(Collectors.toList());
    }

    @GetMapping("/book/:id")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById33(@PathVariable String id) throws InterruptedException {
        System.out.println(id);
        if ("1".equals(id)) {
            Thread.sleep(7000);
        }
        return this.bookServices.findById(Integer.parseInt(id));
    }

    @GetMapping("/books/id")
    public Book getBookById2() throws InterruptedException {
        System.out.println(11111);
        return this.bookServices.findById(1);
    }

    @PostMapping("/book")
    public void saveBook(@Validated @RequestBody Book book, BindingResult result) throws Exception{
        this.bookServices.saveBook(book);
    }
}
