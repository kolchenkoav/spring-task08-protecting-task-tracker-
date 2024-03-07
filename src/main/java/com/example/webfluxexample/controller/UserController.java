package com.example.webfluxexample.controller;

import com.example.webfluxexample.model.UserModel;
import com.example.webfluxexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Flux<UserModel> getAllTasks() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserModel>> getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/by-name")
    public Mono<ResponseEntity<UserModel>> getByUsername(@RequestParam String name) {
        return userService.findByUsername(name);

    }

    @PostMapping
    public Mono<ResponseEntity<UserModel>> createUser(@RequestBody UserModel userModel) {
        return userService.save(userModel).log();

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserModel>> updateUser(@PathVariable String id,
                                                      @RequestBody UserModel userModel) {
        return userService.update(id, userModel);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return userService.deleteById(id);
    }
}
