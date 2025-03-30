package ru.dormlive.backend.util;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import ru.dormlive.backend.dto.*;
import ru.dormlive.backend.model.Advertisement;
import ru.dormlive.backend.model.ChatRoom;
import ru.dormlive.backend.model.Message;
import ru.dormlive.backend.model.User;

@RequiredArgsConstructor
public class Converter {

    public static ChatRoom chatRoomDtoToEntity(ChatRoomDTO dto) throws RuntimeException {
        return ChatRoom.builder()
                .id(dto.get_id() == null ? null : new ObjectId(dto.get_id()))
                .title(dto.getTitle())
                .members(dto.getMembers())
                .messages(dto.getMessages().stream().map(Converter::messageDtoToEntity).toList())
                .build();
    }

    public static ChatRoomDTO chatRoomEntityToDto(ChatRoom entity) {
        return ChatRoomDTO.builder()
                ._id(entity.getId().toString())
                .title(entity.getTitle())
                .members(entity.getMembers())
                .messages(entity.getMessages().stream().map(e -> Converter.messageEntityToDto(e, entity.getId())).toList())
                .build();
    }
    public static Message messageDtoToEntity(MessageDTO dto) {
        return Message.builder()
                .id(dto.getId() == null ? null : new ObjectId(dto.getId()))
                .content(dto.getContent())
                .sender(dto.getSender())
                .timestamp(dto.getTimestamp())
                .status(dto.getStatus())
                .build();
    }

    public static MessageDTO messageEntityToDto(Message entity, ObjectId chatRoomId) {
        return MessageDTO.builder()
                .id(entity.getId().toString())
                .content(entity.getContent())
                .sender(entity.getSender())
                .timestamp(entity.getTimestamp())
                .status(entity.getStatus())
                .chatRoom(chatRoomId.toString())
                .build();
    }

    public static AdDTO convertToAdDTO(Advertisement ad) {
        return AdDTO.builder()
                .id(ad.getId().toString())
                .title(ad.getTitle())
                .category(ad.getCategory())
                .description(ad.getDescription())
                .publishedAt(ad.getPublishedAt())
                .place(ad.getPlace())
                .author(ad.getAuthor())
                .actual(ad.getActual())
                .countLikes(ad.getUsersWithLikes().size())
                .build();
    }

    public static RateDTO convertToRateDTO(User user){
        return RateDTO.builder()
                .nickname(user.getNickname())
                .coins(user.getCoins())
                .build();
    }

    public static UserDTO convertToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .fio(user.getFio())
                .group(user.getGroup())
                .dorm(user.getDorm())
                .build();
    }
}
