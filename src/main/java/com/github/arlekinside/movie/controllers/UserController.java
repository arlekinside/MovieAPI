package com.github.arlekinside.movie.controllers;

import com.github.arlekinside.movie.models.User;
import com.github.arlekinside.movie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) {
        return service.getUser(id);
    }

    @PostMapping("")
    public void setUser(@RequestBody User user) {
        service.saveUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user, @PathVariable long id) {
        if (user.getId().equals(id))
            service.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        service.deleteUser(id);
    }
}
