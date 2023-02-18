package com.example.demo.service;

import com.example.demo.repository.PhoneRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.demo.faker.Faker.person;
import static com.example.demo.faker.Faker.registerRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Register Service Test")
@ExtendWith(MockitoExtension.class)
class RegisterServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PhoneRepository phoneRepository;

    @InjectMocks
    RegisterServiceImpl registerService;


    @Test
    @DisplayName("get person ok")
    void getPersonOK() {

        when(userRepository.saveAndFlush(any())).thenReturn(person());

        var result = registerService.register(registerRequest());

        assertThat(result.getName()).isNotNull().isEqualTo("asdasd");
    }
}
