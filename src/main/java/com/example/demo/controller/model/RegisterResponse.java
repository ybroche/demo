package com.example.demo.controller.model;

import com.example.demo.domain.Person;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class RegisterResponse {

    String name;
    String email;
    String uuid;
    LocalDateTime created;
    LocalDateTime modified;
    LocalDateTime lastLogin;
    Boolean isActive;


    public static RegisterResponse of(Person person) {
        return RegisterResponse.builder()
                .name(person.getName())
                .email(person.getEmail())
                .uuid(person.getUuid())
                .created(person.getCreated())
                .modified(person.getModified())
                .lastLogin(person.getLastLogin())
                .isActive(person.getIsActive())
                .build();
    }
}
