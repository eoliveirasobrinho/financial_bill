package com.itgen.financialit.adapters.in.rest.dto;

import java.time.LocalDateTime;

public record ApiError(
    String message,
    LocalDateTime timestamp
) {
    public static ApiError of(String message) {

        return new ApiError(message, LocalDateTime.now());
    }
    
}
