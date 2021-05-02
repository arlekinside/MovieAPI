package com.github.arlekinside.movie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("hello")
    public @ResponseBody String
    hello(@RequestParam(name = "name", defaultValue = "World") String name){
        return "<html><title>Hello</title><body><p>Hello," + name + "</p></body></html>";
    }
}
