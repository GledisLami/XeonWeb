package com.Xeon.XeonWeb.controllers;
import com.Xeon.XeonWeb.entities.User;
import com.Xeon.XeonWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User " + user.getUsername() + " saved succesfully";
    }

    @PutMapping("/update")
    public String updateUser(@RequestParam(required = false) Integer id,
                             @RequestParam(required = false) String username,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) String role) {
        userService.updateUser(id, username, password, role);
        return "User " + username + " updated succesfully";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return "User with id " + id + " deleted succesfully";
    }
}
