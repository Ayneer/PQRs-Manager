package com.btg.pactual.pqrs.controller;

import com.btg.pactual.pqrs.model.User;
import com.btg.pactual.pqrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    User create(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("first")
    User getFirstUser() {
        return userService.getFirstUser();
    }

}
