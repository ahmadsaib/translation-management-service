package com.example.translation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TranslationRequest(
        @NotBlank String key,
        @NotBlank String value,
        @NotBlank String locale,
        String tag
) {
}
