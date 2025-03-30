package ru.dormlive.backend.util.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dormlive.backend.dto.UserDTO;
import ru.dormlive.backend.model.User;
import ru.dormlive.backend.service.UserService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegistrationValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userToCheck = (UserDTO) target;

        if(userToCheck.getNickname() != null){
            Optional<User> userWithSameNickname = userService.findUserByNickname(userToCheck.getNickname());

            if(userWithSameNickname.isPresent()){
                errors.rejectValue("nickname", "", "User with this nickname exists!");
            }
        }

        Optional<User> userWithSameId = userService.findUserById(userToCheck.getId());
        if(userWithSameId .isPresent()){
            errors.rejectValue("id", "", "User with this id exists!");
        }
    }
}
