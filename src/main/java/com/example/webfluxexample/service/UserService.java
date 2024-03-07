package com.example.webfluxexample.service;

import com.example.webfluxexample.entity.User;
import com.example.webfluxexample.mapper.UserMapper;
import com.example.webfluxexample.model.UserModel;
import com.example.webfluxexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Flux<UserModel> findAll() {
        return userRepository.findAll().map(userMapper::toModel);
    }

    public Mono<ResponseEntity<UserModel>> findById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<UserModel>> findByUsername(String name) {
        return userRepository.findByUsername(name)
                .map(userMapper::toModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<UserModel>> save(UserModel userModel) {
        userModel.setId(UUID.randomUUID().toString());
        Mono<User> user = userRepository.save(userMapper.toEntity(userModel));

        return user.map(userMapper::toModel).cast(UserModel.class)
                //.doOnSuccess(userUpdatesPublisher::publish)
                .map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<UserModel>> update(String id, UserModel userModel) {
        return userRepository.findById(id).flatMap(userForUpdate -> {
            User user = userMapper.toEntity(userModel);

            if (StringUtils.hasText(user.getUsername())) {
                userForUpdate.setUsername(user.getUsername());
            }
            if (StringUtils.hasText(user.getEmail())) {
                userForUpdate.setEmail(user.getEmail());
            }

            return userRepository.save(userForUpdate).map(userMapper::toModel)
                    .map(ResponseEntity::ok)
                    .defaultIfEmpty(ResponseEntity.notFound().build());
        });
    }

    public Mono<ResponseEntity<Void>> deleteById(String id) {
        return userRepository.deleteById(id).log()
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
