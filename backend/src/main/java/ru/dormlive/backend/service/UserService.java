package ru.dormlive.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dormlive.backend.dto.UserDTO;
import ru.dormlive.backend.model.User;
import ru.dormlive.backend.repository.UserRepository;
import ru.dormlive.backend.util.Converter;
import ru.dormlive.backend.util.exception.NotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByNickname(String nickname){
        return userRepository.findUserByNicknameIgnoreCase(nickname);
    }

    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    public UserDTO getUser(String nickname) {
        Optional<User> user = findUserByNickname(nickname);

        if(user.isEmpty()){
            throw new NotFoundException("User with nickname not found");
        }

        return Converter.convertToUserDTO(user.get());
    }
}
