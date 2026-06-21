package com.petcare.api.dto.response;

public record LoginResponse(
        String token,
        String username,
        String role,
        Long veterinarioId
) {
}