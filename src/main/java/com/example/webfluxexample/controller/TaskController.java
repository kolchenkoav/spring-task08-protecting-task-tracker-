package com.example.webfluxexample.controller;

import com.example.webfluxexample.model.TaskModel;;
import com.example.webfluxexample.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public Flux<TaskModel> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskModel>> getTaskById(@PathVariable String id) {
        return taskService.findById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<TaskModel>> createTask(@RequestBody TaskModel taskModel) {
        return taskService.save(taskModel).log();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TaskModel>> updateTask(@PathVariable String id,
                                                      @RequestBody TaskModel taskModel) {
        return taskService.update(id, taskModel);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTask(@PathVariable String id) {
        return taskService.deleteById(id).log();
    }
}
