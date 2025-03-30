package ru.dormlive.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import ru.dormlive.backend.util.MessageStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    private ObjectId id;
    private String content;
    private String sender;
    private Date timestamp;
    private MessageStatus status;

    private List<String> usersWithLikes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message that)) return false;
        return
                Objects.equals(getContent(), that.getContent())
                && Objects.equals(getSender(), that.getSender())
                && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), getSender(), getTimestamp());
    }
}
