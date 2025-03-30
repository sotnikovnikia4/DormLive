package ru.dormlive.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dormlive.backend.dto.UserDTO;
import ru.dormlive.backend.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping
    public UserDTO getUser(@RequestParam("nickname") String nickname) {
        return userService.getUser(nickname);
    }
}
