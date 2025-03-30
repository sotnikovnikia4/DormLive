package ru.dormlive.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import ru.dormlive.backend.dto.MessageDTO;
import ru.dormlive.backend.service.MessageService;

@Controller
@RequiredArgsConstructor
public class ChatController {

    /**
     * Утилита Spring для отправки сообщений через WebSocket
     */
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Сервис для работы с сообщениями
     */
    private final MessageService messageService;

    /**
     * Метод обработки входящих сообщений
     * @MessageMapping("/chat") - указывает endpoint для WebSocket сообщений
     * @param message - тело сообщения
     * @param user - информация об авторизованном пользователе
     */
    @MessageMapping("/chat")
    public void processMessage(@Payload MessageDTO message, @Header("simpUser") UsernamePasswordAuthenticationToken user) {
        // Устанавливаем отправителя из данных авторизации
        message.setSender(user.getName());
        // Сохраняем сообщение в базу данных
        message = messageService.save(message);
        // Отправляем сообщение всем подписчикам определенной комнаты
        messagingTemplate.convertAndSend(
                "/chatRoom/" + message.getChatRoom() + "/", message);
    }
}
