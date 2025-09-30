package com.haninghi.nghibookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.haninghi.nghibookstore.domain.AppUser;
import com.haninghi.nghibookstore.domain.AppUserRepository;
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
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository cateRepository,
			AppUserRepository userRepository) {
		Category novel = new Category("Novel");
		cateRepository.save(novel);
		Category comedy = new Category("Comedy");
		cateRepository.save(comedy);
		Category epic = new Category("Epic");
		cateRepository.save(epic);

		return (args) -> {
			bookRepository.save(
					new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, "0-345-39180-2", 15.99,
							comedy));
			bookRepository.save(new Book("Dune", "Frank Herbert", 1965, "978-0441172719", 22.50, epic));
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			userRepository.save(user1);
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"ADMIN");
			userRepository.save(user2);

		};
	}
}
