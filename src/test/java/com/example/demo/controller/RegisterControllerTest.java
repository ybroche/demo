package com.example.demo.controller;

import com.example.demo.controller.model.RegisterResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RegisterServiceImpl;
import com.example.demo.validator.EmailExistValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.example.demo.faker.Faker.person;
import static com.example.demo.faker.Faker.registerRequest;
import static com.example.demo.faker.Faker.registerRequestEmailBadRequest;
import static com.example.demo.faker.Faker.registerRequestNameBadRequest;
import static com.example.demo.faker.Faker.registerRequestPasswordBadRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Register Controller Adapter Test")
@WebMvcTest(RegisterController.class)
@DirtiesContext
class RegisterControllerTest {
    @MockBean
    private RegisterServiceImpl registerServiceImpl;

    @MockBean
    private EmailExistValidator emailExistValidator;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("when register and respond bad request")
    void registerBadRequest() throws Exception {
        var body = objectMapper.writeValueAsString(registerRequest());

        var response = objectMapper.readValue(new ClassPathResource("json/register-response.json").getFile(), RegisterResponse.class);

        when(registerServiceImpl.register(any())).thenReturn(response);

        this.mockMvc.perform(
                        post("/register")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("asdasd"));

        verify(registerServiceImpl, times(1)).register(any());
    }

    @Test
    @DisplayName("when register and email exist respond bad request")
    void registerEmailExistBadRequest() throws Exception {
        var body = objectMapper.writeValueAsString(registerRequest());
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(person()));
        when(emailExistValidator.isValid(any(), any())).thenReturn(true);

        this.mockMvc.perform(
                        post("/register")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.email").value("email exist"));
    }

    @Test
    @DisplayName("when register and name blank respond bad request")
    void registerNameBadRequest() throws Exception {
        var body = objectMapper.writeValueAsString(registerRequestNameBadRequest());

        this.mockMvc.perform(
                        post("/register")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.name").value("must not be blank"));
    }

    @Test
    @DisplayName("when register and password wrong format respond bad request")
    void registerPasswordBadRequest() throws Exception {
        var body = objectMapper.writeValueAsString(registerRequestPasswordBadRequest());

        this.mockMvc.perform(
                        post("/register")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.password").value("wrong format, at least one uppercase letter, two numbers and lowercase letters"));
    }

    @Test
    @DisplayName("when register and email wrong format respond bad request")
    void registerEmailBadRequest() throws Exception {
        var body = objectMapper.writeValueAsString(registerRequestEmailBadRequest());

        this.mockMvc.perform(
                        post("/register")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.email").value("wrong format"));
    }
}
