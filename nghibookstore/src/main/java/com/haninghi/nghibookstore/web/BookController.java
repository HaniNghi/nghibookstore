package com.haninghi.nghibookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.haninghi.nghibookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    public BookController(BookRepository repository){
        this.repository = repository;
    }


    @GetMapping("/")
    public String getMainScreen() {
        return "index";
    }

    @RequestMapping(value = {"/", "/booklist"})
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }


}
