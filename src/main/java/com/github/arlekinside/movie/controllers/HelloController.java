package com.github.arlekinside.movie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("hello")
    public @ResponseBody String
    hello(@RequestParam(name = "name", defaultValue = "World") String name){
        return "<html><title>Hello</title><body><p>Hello," + name + "</p></body></html>";
    }
}
