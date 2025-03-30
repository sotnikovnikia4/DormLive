package ru.dormlive.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {
    private Integer id;
    private String nickname;
    private String fio;
    private int group;
    private int dorm;

    private int coins;
}
