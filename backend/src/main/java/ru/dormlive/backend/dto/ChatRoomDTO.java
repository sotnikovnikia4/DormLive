package ru.dormlive.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO {
    @JsonProperty("id")
    private String _id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("members")
    @NotNull
    @NotEmpty
    @Size(min = 2)
    private List<String> members;

    @JsonProperty("messages")
    private List<MessageDTO> messages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoomDTO that)) return false;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getMembers(), that.getMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getMembers());
    }
    
    public List<String> getMembers() {
        return members == null ? new ArrayList<>() : members;
    }

    public List<MessageDTO> getMessages() {
        return messages == null ? new ArrayList<>() : messages;
    }
}
