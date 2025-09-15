package com.haninghi.nghibookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.haninghi.nghibookstore.domain.Book;
import com.haninghi.nghibookstore.domain.BookRepository;
import com.haninghi.nghibookstore.domain.Category;
import com.haninghi.nghibookstore.domain.CategoryRepository;

@SpringBootApplication
public class NghibookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(NghibookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository cateRepository) {
		Category comedy = new Category("Comedy");
		cateRepository.save(comedy);
		Category epic =  new Category("Epic");
		cateRepository.save(epic);
		return (args) -> {
			bookRepository.save(
					new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, "0-345-39180-2", 15.99, comedy));
			bookRepository.save(new Book("Dune", "Frank Herbert", 1965, "978-0441172719", 22.50, epic ));
		};
	}
}
