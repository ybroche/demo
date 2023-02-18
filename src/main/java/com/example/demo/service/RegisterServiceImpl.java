package com.example.demo.service;

import com.example.demo.controller.model.RegisterRequest;
import com.example.demo.controller.model.RegisterResponse;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        var person = userRepository.saveAndFlush(registerRequest.toDomain());
        phoneRepository.saveAll(registerRequest.getPhones().stream().map(phoneRequest -> phoneRequest.toDomain(person)).toList());
        return RegisterResponse.of(person);
    }
}
