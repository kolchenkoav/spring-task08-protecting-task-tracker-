package com.example.webfluxexample.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    private String id;
    private String username;
    private String email;
}
