package com.onlineshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

    @RequestMapping("/")
    public String showMenu() {
        System.out.println("HAIL HITLER!!!");
        return "list-products";
    }
}
