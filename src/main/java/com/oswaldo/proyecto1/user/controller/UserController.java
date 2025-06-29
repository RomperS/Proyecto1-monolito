package com.oswaldo.proyecto1.user.controller;

import com.oswaldo.proyecto1.user.dto.UserRequest;
import com.oswaldo.proyecto1.user.dto.UserResponse;
import com.oswaldo.proyecto1.user.model.User;
import com.oswaldo.proyecto1.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/search")
    public List<UserResponse> findByUsernameContaining(@RequestParam String username) {
        return service.findByUsernameContaining(username);
    }

    @GetMapping("/username/{username}")
    public UserResponse findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @GetMapping("/name/{name}")
    public List<UserResponse> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PostMapping
    public UserResponse save(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public UserResponse update(@RequestBody UserRequest request, @PathVariable Integer id) {
        return service.update(request, id);
    }

    @PatchMapping("/{id}")
    public UserResponse partialUpdate(@RequestBody UserRequest request, @PathVariable Integer id) {
        return service.partialUpdate(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
