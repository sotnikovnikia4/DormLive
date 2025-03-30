package ru.dormlive.backend.util.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatRoomNotCreatedException extends RuntimeException {
    public ChatRoomNotCreatedException(String message) {
        super(message);
        log.error(message);
    }
}
