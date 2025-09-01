package com.haninghi.nghibookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/")
    public String getMainScreen() {
        return "index";
    }

}
