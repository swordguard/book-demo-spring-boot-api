package com.example.demo;

import com.example.demo.bookrepository.BookRepository;
import com.example.demo.models.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JianApplication {

	public static void main(String[] args) {
		SpringApplication.run(JianApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository) {
		return (args -> {
			bookRepository.save(new Book("Jack1", "Bauer"));
			bookRepository.save(new Book("Chloe1", "O'Brian"));
			bookRepository.save(new Book("Chloe2", "O'Brian2"));
			bookRepository.save(new Book("Chloe3", "O'Brian3"));

		});
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/book").allowedOrigins("*");
			}
		};
	}
}
