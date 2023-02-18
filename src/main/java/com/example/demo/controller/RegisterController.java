package com.example.demo.controller;

import com.example.demo.controller.model.RegisterRequest;
import com.example.demo.controller.model.RegisterResponse;
import com.example.demo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public RegisterResponse register(@RequestBody @Validated RegisterRequest registerRequest) {
        return registerService.register(registerRequest);
    }
}
