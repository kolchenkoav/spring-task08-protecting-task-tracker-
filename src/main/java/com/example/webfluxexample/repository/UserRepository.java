package com.example.webfluxexample.repository;

import com.example.webfluxexample.entity.User;
import java.util.Set;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String name);
    Flux<User> findAllById(Set<String> stringSet);
}
