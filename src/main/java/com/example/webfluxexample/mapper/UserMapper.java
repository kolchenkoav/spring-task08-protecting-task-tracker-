package com.example.webfluxexample.mapper;

import com.example.webfluxexample.entity.User;
import com.example.webfluxexample.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserModel.class, User.class}) //injectionStrategy = InjectionStrategy.CONSTRUCTOR
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserModel userModel);
    UserModel toModel(User user);

    default List<User> toEntityList(List<UserModel> userModelList) {
        return new ArrayList<>(userModelList.stream()
                .map(this::toEntity).toList());
    }

    default List<UserModel> toModelList(List<User> userList) {
        return new ArrayList<>(userList.stream()
                .map(this::toModel).toList());
    }
}
