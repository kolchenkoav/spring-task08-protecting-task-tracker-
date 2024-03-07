package com.example.webfluxexample.repository;

import com.example.webfluxexample.entity.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {
}
