package ru.dormlive.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dormlive.backend.dto.LoginDTO;
import ru.dormlive.backend.dto.TokenDTO;
import ru.dormlive.backend.dto.UserDTO;
import ru.dormlive.backend.service.AuthService;
import ru.dormlive.backend.util.ErrorMethods;
import ru.dormlive.backend.util.validators.LoginValidator;
import ru.dormlive.backend.util.validators.RegistrationValidator;
import ru.dormlive.backend.util.exception.UserNotRegisteredException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RegistrationValidator registrationValidator;
    private final LoginValidator loginValidator;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("register")
    public TokenDTO register(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        registrationValidator.validate(userDTO, bindingResult);
        if(bindingResult.hasErrors()){
            throw new UserNotRegisteredException(ErrorMethods.formErrorMessage(bindingResult));
        }

        return authService.register(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("login")
    public TokenDTO login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult){
        loginValidator.validate(loginDTO, bindingResult);
        if(bindingResult.hasErrors()){
            throw new UsernameNotFoundException(ErrorMethods.formErrorMessage(bindingResult));
        }

        return authService.login(loginDTO);
    }
}
