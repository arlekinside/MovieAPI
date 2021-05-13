package com.github.arlekinside.movie.services.implementations;

import com.github.arlekinside.movie.exceptions.IncorrectUserException;
import com.github.arlekinside.movie.exceptions.UserNotFoundException;
import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.models.User;
import com.github.arlekinside.movie.repositories.UserRepository;
import com.github.arlekinside.movie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUser(String id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("---Error! No such user in the database---"));
    }

    @Override
    public void updateUser(User user) {
        if(!repository.findById(user.getId()).isPresent()) throw new UserNotFoundException("---No such user in the database---");
        saveUser(user);
    }

    @Override
    public void saveUser(User user) {
        if (user.getId().isEmpty()) throw new IncorrectUserException("---Error! User id is empty---");
        repository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        repository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("---Error! No such user in the database---"));
        repository.deleteById(id);
    }
}
