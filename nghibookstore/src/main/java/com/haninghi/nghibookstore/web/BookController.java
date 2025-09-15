package com.haninghi.nghibookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.haninghi.nghibookstore.domain.Book;
import com.haninghi.nghibookstore.domain.BookRepository;
import com.haninghi.nghibookstore.domain.CategoryRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository cateRepository;

    public BookController(BookRepository repository, CategoryRepository cateRepository) {
        this.repository = repository;
        this.cateRepository = cateRepository;
    }

    @GetMapping("/")
    public String getMainScreen() {
        return "index";
    }

    @RequestMapping(value = { "/", "/booklist" })
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value = { "/addbook" })
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("category", cateRepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.POST)
    public String save(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    @RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", repository.findById(bookId));
        return "editbook";
    }

    @RequestMapping(value = { "/update" }, method = RequestMethod.POST)
    public String update(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }
}
