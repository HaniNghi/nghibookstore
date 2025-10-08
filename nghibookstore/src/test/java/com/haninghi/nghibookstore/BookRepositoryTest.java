package com.haninghi.nghibookstore;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.haninghi.nghibookstore.domain.Book;
import com.haninghi.nghibookstore.domain.BookRepository;
import com.haninghi.nghibookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByAuthorShouldReturnAuthor() {
        List<Book> books = bookRepository.findByAuthor("Douglas Adams");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("The Hitchhiker's Guide to the Galaxy");
    }

    @Test
    public void createNewBook() {
        Book book = new Book("Paul Trembley", "A Head Full of Ghosts", 2015, "ISBN434345621394", 16.30,
                categoryRepository.findByName("Novel").get(0));
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

}
