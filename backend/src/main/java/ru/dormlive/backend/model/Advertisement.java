package ru.dormlive.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("advertisement")
public class Advertisement {
    public static final String SALE_TYPE = "sale";
    public static final String SEARCH_TYPE = "search";
    public static final String EVENT_TYPE = "event";

    private ObjectId id;
    private String title;
    private String category;
    private LocalDateTime publishedAt;
    private String description;
    private String place;

    private List<String> usersWithLikes = new ArrayList<>();

    private String author;

    private Integer dorm;
    private Boolean actual;
}