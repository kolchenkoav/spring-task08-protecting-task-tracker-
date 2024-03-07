package com.example.webfluxexample.mapper;

import com.example.webfluxexample.entity.Task;
import com.example.webfluxexample.model.TaskModel;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@DecoratedWith(TaskMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TaskModel.class, Task.class})
public interface TaskMapper {

    Task toEntity(TaskModel taskModel);

    TaskModel toModel(Task task);

    default List<Task> toEntityList(List<TaskModel> taskModelList) {
        return new ArrayList<>(taskModelList.stream()
                .map(this::toEntity).toList());
    }

    default List<TaskModel> toModelList(List<Task> taskList) {
        return new ArrayList<>(taskList.stream()
                .map(this::toModel).toList());
    }
}

