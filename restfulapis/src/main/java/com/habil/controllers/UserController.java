package com.habil.controllers;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @GetMapping
    public String getUsers()
    {
        return "List of users";
    }

    @GetMapping("/welcome")
    public String welcomeBack()
    {
        return "Welcome back";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id)
    {
        return "User " + id + "details: ...";
    }

    @GetMapping("/search")
    public String searchUser(@RequestParam String name)
    {
        return "User " + name + " details ...";
    }

    @PostMapping
    public String createUser(@RequestBody User user)
    {
        return user.getName() + ": " + user.getPassword();
    }
}
