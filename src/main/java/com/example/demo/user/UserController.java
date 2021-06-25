package com.example.demo.user;

import com.example.demo.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final List<User> users = Arrays.asList(
      new User(1, "James Bond"),
      new User(2, "Maria Jones"),
      new User(3, "Anna Smith")
    );

    @GetMapping(path = "{UserId}")
    public User getUser(@PathVariable("UserId") Integer UserId) {
        return users.stream()
                .filter(User -> UserId.equals(User.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "User " + UserId + " does not exists"
                ));
    }
}
