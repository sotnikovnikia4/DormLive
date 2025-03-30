package ru.dormlive.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dormlive.backend.dto.LoginDTO;
import ru.dormlive.backend.dto.TokenDTO;
import ru.dormlive.backend.dto.UserDTO;
import ru.dormlive.backend.model.User;
import ru.dormlive.backend.repository.UserRepository;
import ru.dormlive.backend.security.JWTUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public TokenDTO register(UserDTO userDTO){
        User user = User.builder()
                .id(userDTO.getId())
                .dorm(userDTO.getDorm())
                .fio(userDTO.getFio())
                .nickname(userDTO.getNickname())
                .group(userDTO.getGroup())
                .build();

        userRepository.save(user);

        return TokenDTO.builder()
                .token(jwtUtil.generateToken(user))
                .build();
    }

    public TokenDTO login(LoginDTO loginDTO){
        Optional<User> found = userRepository.findById(loginDTO.getId());

        if(found.isEmpty()){
            throw new UsernameNotFoundException("Not registered");
        }

        found.get().setGroup(loginDTO.getGroup());
        userRepository.save(found.get());

        return TokenDTO.builder()
                .token(jwtUtil.generateToken(found.get()))
                .build();
    }
}
