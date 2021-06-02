package com.github.arlekinside.movie.servicesTests;

import com.github.arlekinside.movie.models.User;
import com.github.arlekinside.movie.repositories.UserRepository;
import com.github.arlekinside.movie.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Order(3)
public class UserServiceTests {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    private User user;

    @BeforeEach
    public void init(){
        user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setId(1L);
        user.setStatus("COMMAND");
        repository.save(user);
    }

    @Test
    public void whenGetAllUsers_thenContainsTestUser(){
        Assertions.assertTrue(service.getAllUsers().contains(user));
    }

    @Test
    public void whenGetUser_thenReturnCorrectUser(){
        Assertions.assertEquals(user, service.getUser(user.getId()));
    }

    @Test
    public void whenUpdateUser_returnUpdatedUser(){
        user.setUsername("username0");
        service.updateUser(user);
        whenGetUser_thenReturnCorrectUser();
    }

    @Test
    public void whenSaveUser_thenReturnCorrectUser(){
        User u = new User();
        u.setUsername("username");
        u.setFirstName("firstName");
        u.setLastName("lastName");
        u.setId(2L);
        u.setStatus("COMMAND");
        service.saveUser(u);
        Assertions.assertEquals(u, service.getUser(u.getId()));
        repository.delete(u);
    }

    @Test
    public void whenDeleteUser_thenGetAllUsersDoesntContainTestUser(){
        User u = new User();
        u.setUsername("username");
        u.setFirstName("firstName");
        u.setLastName("lastName");
        u.setId(2L);
        u.setStatus("COMMAND");
        repository.save(u);
        service.deleteUser(u.getId());
        Assertions.assertFalse(service.getAllUsers().contains(u));
    }

    @AfterEach
    public void finish(){
        repository.delete(user);
    }
}
