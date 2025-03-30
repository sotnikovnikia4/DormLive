package ru.dormlive.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dormlive.backend.util.MessageStatus;

import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("status")
    private MessageStatus status;

    @JsonProperty("chat_room")
    private String chatRoom;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageDTO that)) return false;
        if(timestamp != null && that.getTimestamp() != null){
            if(!timestamp.equals(((MessageDTO) o).getTimestamp())) return false;
        }
        return
                Objects.equals(getContent(), that.getContent()) &&
                Objects.equals(getSender(), that.getSender()) &&
                Objects.equals(getChatRoom(), that.getChatRoom()
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), getSender(), getTimestamp(), getChatRoom());
    }
}
