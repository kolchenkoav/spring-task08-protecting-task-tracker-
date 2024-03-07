package com.example.webfluxexample.model;

import com.example.webfluxexample.entity.TaskStatus;
import com.example.webfluxexample.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private TaskStatus status;

    private String authorId;
    private String assigneeId;
    private Set<String> observerIds;

    private User author;
    private User assignee;
    private Set<User> observers;

}
