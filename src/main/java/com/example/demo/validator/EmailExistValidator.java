package com.example.demo.validator;

import com.example.demo.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailExistValidator implements
        ConstraintValidator<EmailExist, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(EmailExist contactNumber) {
        //empty
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        var result = userRepository.findByEmail(value);
        return result.isEmpty();
    }
}
