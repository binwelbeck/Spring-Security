package com.example.demo.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")
public class UserManagementController {

    private static final List<User> users = Arrays.asList(
            new User(1, "James Bond"),
            new User(2, "Maria Jones"),
            new User(3, "Anna Smith")
    );

//    hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<User> getAllUsers() {
        System.out.println("getAllUsers");
        return users;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void registerNewUser(@RequestBody User User) {
        System.out.println("registerNewUser");
        System.out.println(User);
    }

    @DeleteMapping(path = "{UserId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUser(@PathVariable("UserId") Integer UserId) {
        System.out.println("deleteUser");
        System.out.println(UserId);
    }

    @PutMapping(path = "{UserId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateUser(@PathVariable("UserId") Integer UserId, @RequestBody User User) {
        System.out.println("updateUser");
        System.out.println(String.format("%s %s", UserId, User));
    }
}
