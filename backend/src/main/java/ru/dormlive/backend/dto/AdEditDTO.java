package ru.dormlive.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdEditDTO {
    @NotNull
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String category;
    @NotBlank
    private String description;
    @NotBlank
    private String place;
}