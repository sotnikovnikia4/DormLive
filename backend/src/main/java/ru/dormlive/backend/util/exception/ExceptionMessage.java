package ru.dormlive.backend.util.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExceptionMessage {
    private String message;
    private Date timestamp;
    private Integer status;
}
