package ru.dormlive.backend.util.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatRoomNotFoundException extends NotFoundException {
    public ChatRoomNotFoundException(String message) {
        super(message);
        log.error(message);
    }
    public ChatRoomNotFoundException(){
        this("Chat room not found");
    }
}
