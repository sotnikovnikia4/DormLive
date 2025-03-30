package ru.dormlive.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("chatroom")
public class ChatRoom {
    @Id
    private ObjectId id;
    private String title;
    private List<String> members;
    private List<Message> messages;
    private String imgPath;

    public void addMessage(Message message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoom chatRoom)) return false;
        return Objects.equals(getTitle(), chatRoom.getTitle()) && Objects.equals(getMembers(), chatRoom.getMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getMembers());
    }

    public List<String> getMembers() {
        return members == null ? new ArrayList<>() : members;
    }

    public List<Message> getMessages() {
        return messages == null ? new ArrayList<>() : messages;
    }
}
