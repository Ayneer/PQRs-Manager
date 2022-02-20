package com.btg.pactual.pqrs.service;

import com.btg.pactual.pqrs.ecxeption.cutomException.UserNotFoundException;
import com.btg.pactual.pqrs.model.User;
import com.btg.pactual.pqrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getFirstUser() {
        return userRepository.findAll().get(0);
    }

    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
           throw new UserNotFoundException("User does not exist");
        }
        return user.get();
    }
}
