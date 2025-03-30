package ru.dormlive.backend.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dormlive.backend.dto.ChatRoomDTO;
import ru.dormlive.backend.model.ChatRoom;
import ru.dormlive.backend.repository.ChatRoomRepository;
import ru.dormlive.backend.security.UserDetailsImpl;
import ru.dormlive.backend.util.Converter;
import ru.dormlive.backend.util.exception.ChatRoomNotCreatedException;
import ru.dormlive.backend.util.exception.ChatRoomNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDto) {
        // Конвертируем DTO в Entity
        ChatRoom chatRoom = Converter.chatRoomDtoToEntity(chatRoomDto);

        // Генерируем и устанавливаем заголовок чат-комнаты
        generateAndSetTitle(chatRoom);

        // Сохраняем чат-комнату в базу данных
        chatRoom = chatRoomRepository.save(chatRoom);

        ChatRoomDTO createdChatRoomDTO;
        try {
            // Конвертируем сохраненную Entity обратно в DTO
            createdChatRoomDTO = Converter.chatRoomEntityToDto(chatRoom);
        }catch (RuntimeException e) {
            // В случае ошибки конвертации выбрасываем исключение о неудачном создании чат-комнаты
            throw new ChatRoomNotCreatedException(e.getMessage());
        }
        // Возвращаем созданную чат-комнату в формате DTO
        return createdChatRoomDTO;
    }

    @Transactional
    public ChatRoom save(ChatRoom chatRoom){
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findChatRoomById(ObjectId id) throws ChatRoomNotFoundException {
        return chatRoomRepository.findChatRoomEntityById(id).orElseThrow(ChatRoomNotFoundException::new);
    }
    public List<ChatRoomDTO> findAllChatRooms() {
        String authenticatedUserId = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getNickname();
        List<ChatRoom> chatRoomEntities = chatRoomRepository.findChatRoomEntitiesByMembersContainingIgnoreCase(authenticatedUserId);
        if(chatRoomEntities == null || chatRoomEntities.isEmpty()){
            throw new ChatRoomNotFoundException();
        }
        return chatRoomEntities.stream().map(Converter::chatRoomEntityToDto).toList();
    }

    public ChatRoomDTO findChatRoomDtoById(String id) throws ChatRoomNotFoundException {
        ObjectId objectId = new ObjectId(id);
        return Converter.chatRoomEntityToDto(findChatRoomById(objectId));
    }

    private void generateAndSetTitle(ChatRoom chatRoom) {
        if(chatRoom.getTitle() == null || chatRoom.getTitle().isBlank()){
            chatRoom.setTitle(chatRoom.getMembers().stream().collect(
                    StringBuilder::new,
                    StringBuilder::append,
                    StringBuilder::append //Todo: generate better name
            ).toString());
        }
    }

}
