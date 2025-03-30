package ru.dormlive.backend.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dormlive.backend.util.exception.ChatRoomNotCreatedException;
import ru.dormlive.backend.util.exception.ExceptionMessage;
import ru.dormlive.backend.util.exception.NotFoundException;
import ru.dormlive.backend.util.exception.UserNotRegisteredException;

import java.util.Date;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionMessage> handleException(ValidationException e){
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChatRoomNotCreatedException.class)
    public ResponseEntity<ExceptionMessage> handleException(ChatRoomNotCreatedException e){
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleException(NotFoundException e){
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.NOT_FOUND.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleException(UsernameNotFoundException e){
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotRegisteredException.class)
    public ResponseEntity<ExceptionMessage> handleException(UserNotRegisteredException e){
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionMessage> handleException(BadCredentialsException e){
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ExceptionMessage> handleException(JWTVerificationException e){
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<ExceptionMessage> handleException(AuthenticationServiceException e){
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).timestamp(new Date()).build();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @SneakyThrows
    public void accessDenied(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ExceptionMessage message = ExceptionMessage.builder().message(e.getMessage()).status(HttpServletResponse.SC_FORBIDDEN).timestamp(new Date()).build();

        response.setStatus(message.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(message));
    }

    @SneakyThrows
    public void unauthorized(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        ExceptionMessage message = ExceptionMessage.builder().message("Authorization required").status(HttpServletResponse.SC_UNAUTHORIZED).timestamp(new Date()).build();

        response.setStatus(message.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(message));
    }
}
