package ru.dormlive.backend.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dormlive.backend.dto.MessageDTO;
import ru.dormlive.backend.model.ChatRoom;
import ru.dormlive.backend.model.Message;
import ru.dormlive.backend.util.Converter;
import ru.dormlive.backend.util.MessageStatus;

import java.util.Date;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {
    private final ChatRoomService chatRoomService;

    /**
         * Saves a message to a specific chat room after validating the sender's membership.
         *
         * @param messageDto The message to be saved, containing sender and chat room information
         * @return The saved message as a MessageDto with updated metadata
         * @throws RuntimeException if the message sender is not a member of the specified chat room
         */
    @Transactional
    public MessageDTO save(MessageDTO messageDto) {
        Message message = Converter.messageDtoToEntity(messageDto);
        ChatRoom chatRoom = chatRoomService.findChatRoomById(new ObjectId(messageDto.getChatRoom()));

        if(chatRoom.getMembers().stream().noneMatch(e -> e.equals(messageDto.getSender()))) {
            throw new RuntimeException();
        }

        enrich(message);
        message.setId(ObjectId.getSmallestWithDate(new Date()));

        chatRoom.addMessage(message);
        chatRoomService.save(chatRoom);

        return Converter.messageEntityToDto(message, chatRoom.getId());
    }

    private void enrich(Message message){
        message.setTimestamp(new Date());
        message.setStatus(MessageStatus.DELIVERED);
    }

}
