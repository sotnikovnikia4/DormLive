package ru.dormlive.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dormlive.backend.model.User;
import ru.dormlive.backend.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = usersRepository.findUserByNicknameIgnoreCase(username);

        if(foundUser.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new UserDetailsImpl(foundUser.get());
    }
}
