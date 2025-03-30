package ru.dormlive.backend.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dormlive.backend.model.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, ObjectId> {
    Optional<ChatRoom> findChatRoomEntityById(ObjectId id);
    List<ChatRoom> findChatRoomEntitiesByMembersContainingIgnoreCase(String member);
}
