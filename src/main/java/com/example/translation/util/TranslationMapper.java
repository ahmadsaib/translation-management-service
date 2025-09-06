package com.example.translation.util;

import com.example.translation.dto.TranslationRequest;
import com.example.translation.dto.TranslationResponse;
import com.example.translation.model.Translation;

public class TranslationMapper {

    // Map from Request DTO to Entity
    public static Translation toEntity(TranslationRequest request) {
        Translation translation = new Translation();
        translation.setKey(request.key());
        translation.setValue(request.value());
        translation.setLocale(request.locale());
        translation.setTag(request.tag());
        return translation;
    }

    // Map from Entity to Response DTO
    public static TranslationResponse toResponse(Translation entity) {
        return new TranslationResponse(
                entity.getId(),
                entity.getKey(),
                entity.getValue(),
                entity.getLocale(),
                entity.getTag()
        );
    }
}

