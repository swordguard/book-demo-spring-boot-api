package com.example.demo.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @NotBlank
    private String name;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        Book b = (Book) o;
        return name.equals(b.name) && author.equals(b.author);
    }

    @Size(min = 2, max = 10)
    private String author;

}
