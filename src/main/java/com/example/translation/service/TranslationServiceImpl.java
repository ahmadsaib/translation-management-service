package com.example.translation.service.impl;

import com.example.translation.dto.TranslationRequest;
import com.example.translation.dto.TranslationResponse;
import com.example.translation.model.Translation;
import com.example.translation.repository.TranslationRepository;
import com.example.translation.service.TranslationService;
import com.example.translation.util.TranslationMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository repository;

    public TranslationServiceImpl(TranslationRepository repository) {
        this.repository = repository;
    }

    @Override
    public TranslationResponse create(TranslationRequest request) {
        Translation entity = TranslationMapper.toEntity(request);
        return TranslationMapper.toResponse(repository.save(entity));
    }

    @Override
    public TranslationResponse update(Long id, TranslationRequest request) {
        Translation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Translation not found"));
        entity.setKey(request.key());
        entity.setValue(request.value());
        entity.setLocale(request.locale());
        entity.setTag(request.tag());
        return TranslationMapper.toResponse(repository.save(entity));
    }

    @Override
    public TranslationResponse getById(Long id) {
        Translation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Translation not found"));
        return TranslationMapper.toResponse(entity);

    }

    @Override
    public List<TranslationResponse> getAllTranslations() {
        List<Translation> translations = repository.findAll();
        return translations.stream()
                .map(TranslationMapper::toResponse)
                .toList();
    }


    @Override
    public List<TranslationResponse> search(String tag, String key, String value) {
        List<Translation> results = repository.findAll().stream()
                .filter(t -> tag == null || t.getTag().equalsIgnoreCase(tag))
                .filter(t -> key == null || t.getKey().toLowerCase().contains(key.toLowerCase()))
                .filter(t -> value == null || t.getValue().toLowerCase().contains(value.toLowerCase()))
                .toList();

        return results.stream()
                .map(TranslationMapper::toResponse)
                .toList();
    }


    @Override
    public List<TranslationResponse> getByLocale(String locale) {
        return repository.findByLocale(locale).stream()
                .map(TranslationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void generateSampleData(int count) {
        Random random = new Random();
        List<Translation> sampleData = IntStream.range(0, count)
                .mapToObj(i -> new Translation(
                        "key_" + i,
                        "value_" + i,
                        i % 2 == 0 ? "en" : "fr",
                        i % 3 == 0 ? "web" : "mobile"
                ))
                .toList();

        repository.saveAll(sampleData);
    }

}
