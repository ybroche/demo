package com.example.demo.controller.model;

import com.example.demo.domain.Person;
import com.example.demo.validator.EmailExist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class RegisterRequest {

    @NotBlank
    String name;

    @EmailExist
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "wrong format")
    String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2,})\\S{8,}$", message = "wrong format, at least one uppercase letter, two numbers and lowercase letters")
    String password;

    @NotNull
    List<PhoneRequest> phones;

    public Person toDomain() {
        return new Person(name, email, password);
    }
}
