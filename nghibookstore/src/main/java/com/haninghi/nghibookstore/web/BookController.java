package com.haninghi.nghibookstore.web;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.haninghi.nghibookstore.domain.Book;
import com.haninghi.nghibookstore.domain.BookRepository;
import com.haninghi.nghibookstore.domain.CategoryRepository;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class BookController {
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository cateRepository;

    public BookController(BookRepository repository, CategoryRepository cateRepository) {
        this.bookRepository = repository;
        this.cateRepository = cateRepository;
    }

    @GetMapping("/")
    public String getMainScreen() {
        return "index";
    }

    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = { "/", "/booklist" })
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Book getBook(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId)
            .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("Book with id %d not found", bookId)
        ));
    }

    public String requestMethodName(@RequestParam String param) {
        return new String();
    }

    @RequestMapping(value = { "/addbook" })
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", cateRepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.POST)
    public String save(Book book) {
        bookRepository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:../booklist";
    }

    @RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute("categories", cateRepository.findAll());
        return "editbook";
    }

    @RequestMapping(value = { "/update" }, method = RequestMethod.POST)
    public String update(Book book) {
        bookRepository.save(book);
        return "redirect:booklist";
    }
}
