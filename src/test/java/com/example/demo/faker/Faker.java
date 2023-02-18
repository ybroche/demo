package com.example.demo.faker;

import com.example.demo.controller.model.PhoneRequest;
import com.example.demo.controller.model.RegisterRequest;
import com.example.demo.domain.Person;

import java.util.List;

public class Faker {
    public static RegisterRequest registerRequest() {
        return RegisterRequest.builder()
                .email("asdasdasd@gmail.com")
                .name("asdasdasd")
                .password("asdasda22AAAsd")
                .phones(List.of(phoneRequest()))
                .build();
    }

    public static PhoneRequest phoneRequest() {
        return PhoneRequest.builder()
                .number(123123123)
                .cityCode((byte) 1)
                .countryCode((byte) (12))
                .build();
    }

    public static RegisterRequest registerRequestNameBadRequest() {
        return RegisterRequest.builder()
                .email("asdasdasd@gmail.com")
                .name("")
                .password("asdasda22AAAsd")
                .phones(List.of(phoneRequest()))
                .build();
    }

    public static RegisterRequest registerRequestPasswordBadRequest() {
        return RegisterRequest.builder()
                .email("asdasdasd@gmail.com")
                .name("asdasdasdasd")
                .password("asdasdaAAAsd")
                .phones(List.of(phoneRequest()))
                .build();
    }

    public static RegisterRequest registerRequestEmailBadRequest() {
        return RegisterRequest.builder()
                .email("asdasdasdcom")
                .name("asdasdasdasd")
                .password("asdasdaAAAsd")
                .phones(List.of(phoneRequest()))
                .build();
    }

    public static Person person() {
        return new Person("asdasd", "asdasdasd@gmail.com", "asdasd");
    }
}
