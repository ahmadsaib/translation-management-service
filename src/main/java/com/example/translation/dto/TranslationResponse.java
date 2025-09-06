package com.example.translation.dto;

public record TranslationResponse(
        Long id,
        String key,
        String value,
        String locale,
        String tag
) {
}
