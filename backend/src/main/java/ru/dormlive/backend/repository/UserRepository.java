package ru.dormlive.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.dormlive.backend.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer>, QuerydslPredicateExecutor<User> {
    Optional<User> findUserByNicknameIgnoreCase(String nickname);
}
