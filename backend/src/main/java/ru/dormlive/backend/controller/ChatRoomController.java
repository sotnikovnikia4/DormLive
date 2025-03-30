package ru.dormlive.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.dormlive.backend.dto.ChatRoomDTO;
import ru.dormlive.backend.service.ChatRoomService;
import ru.dormlive.backend.util.ErrorMethods;
import ru.dormlive.backend.util.exception.ChatRoomNotCreatedException;

import java.util.List;

/**
 * Класс для управления комнатами сообщений(беседами) (создание и т.д.)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatRoom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoomDTO> createChatRoom(@RequestBody @Validated ChatRoomDTO chatRoomDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ChatRoomNotCreatedException(ErrorMethods.formErrorMessage(bindingResult));
        }

        ChatRoomDTO createdChatRoomDto = chatRoomService.createChatRoom(chatRoomDto);
        log.info("Request to create chat room: {}", createdChatRoomDto);
        return ResponseEntity.ok().body(createdChatRoomDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoomDTO> getChatRoom(@PathVariable String id) {
        return ResponseEntity.ok().body(chatRoomService.findChatRoomDtoById(id));
    }

    @GetMapping()
    public ResponseEntity<List<ChatRoomDTO>> getAllChatRooms() {
        return ResponseEntity.ok().body(chatRoomService.findAllChatRooms());
    }
}
