package ru.dormlive.backend.util.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dormlive.backend.dto.LoginDTO;

@Component
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LoginDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //проверяет подпись
    }
}
