package com.github.arlekinside.movie.services;

import com.github.arlekinside.movie.models.User;
import com.github.arlekinside.movie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(String id);

    void updateUser(User user, String id);

    void saveUser(User user);

    void deleteUser(String id);
}
