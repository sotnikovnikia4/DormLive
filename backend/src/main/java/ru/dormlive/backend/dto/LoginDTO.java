package ru.dormlive.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String fio;
    @NotNull
    private Integer group;

    private String sign;
}
