package com.example.translation.service;

import com.example.translation.dto.TranslationRequest;
import com.example.translation.dto.TranslationResponse;

import java.util.List;

public interface TranslationService {
    TranslationResponse create(TranslationRequest request);

    TranslationResponse update(Long id, TranslationRequest request);

    List<TranslationResponse> getAllTranslations();

    TranslationResponse getById(Long id);

    List<TranslationResponse> search(String tag, String key, String value);

    List<TranslationResponse> getByLocale(String locale);

    void generateSampleData(int count);
}
