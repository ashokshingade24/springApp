package com.example.userservice.controller;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }
    @GetMapping public List<User> getUsers() { return service.findAll(); }
    @GetMapping("/{id}") public User getUser(@PathVariable Long id) {
        User user = service.findById(id);
        if (user == null) throw new UserNotFoundException();
        return user;
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) { return service.create(user); }
    @ResponseStatus(HttpStatus.NOT_FOUND) static class UserNotFoundException extends RuntimeException {}
}
