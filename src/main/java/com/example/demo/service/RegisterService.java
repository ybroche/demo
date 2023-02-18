package com.example.demo.service;

import com.example.demo.controller.model.RegisterRequest;
import com.example.demo.controller.model.RegisterResponse;

public interface RegisterService {
    RegisterResponse register(RegisterRequest registerRequest);
}
