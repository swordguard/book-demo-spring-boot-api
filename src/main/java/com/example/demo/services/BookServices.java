package com.example.demo.services;

import com.example.demo.bookrepository.BookRepository;
import com.example.demo.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServices {

    private final BookRepository bookRepository;


    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }
    public Book findById(int id) {
        return this.bookRepository.findById(id);
    }

    public void saveBook(Book book) {
        this.bookRepository.save(book);
    }
}
