package com.haninghi.nghibookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.haninghi.nghibookstore.domain.Book;
import com.haninghi.nghibookstore.domain.BookRepository;

@SpringBootApplication
public class NghibookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(NghibookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository) {
		return (args) -> {
			bookRepository.save(
					new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, "0-345-39180-2", 15.99));
			bookRepository.save(new Book("Dune", "Frank Herbert", 1965, "978-0441172719", 22.50));
		};
	}
}
