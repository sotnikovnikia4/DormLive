package ru.dormlive.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO {
    private String id;
    private String title;
    private String category;
    private LocalDateTime publishedAt;
    private String description;
    private String place;
    private String author;
    private Boolean actual;
    private Integer countLikes;
}