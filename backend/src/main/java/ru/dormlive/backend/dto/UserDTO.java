package ru.dormlive.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull
    private Integer id;
    @NotBlank
    private String nickname;
    @NotBlank
    private String fio;
    @NotNull
    private Integer group;
    @NotNull
    private Integer dorm;
}
