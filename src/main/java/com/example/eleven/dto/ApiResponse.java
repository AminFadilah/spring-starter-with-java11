package com.example.eleven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int status;      // HTTP status code
    private String message;  // message info
    private T data;          // payload
}
