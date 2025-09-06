package com.example.translation.util;

import com.example.translation.model.Translation;
import com.example.translation.repository.TranslationRepository;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class DataGenerator {

    private final TranslationRepository repository;

    public DataGenerator(TranslationRepository repository) {
        this.repository = repository;
    }

    public void generateSampleData(int count) {
        Random random = new Random();
        String[] locales = {"en", "fr", "es", "de", "ar"};
        String[] tags = {"web", "mobile", "admin", "email", "user"};

        IntStream.range(0, count).forEach(i -> {
            Translation t = new Translation();
            t.setKey("key_" + UUID.randomUUID());
            t.setValue("value_" + UUID.randomUUID());
            t.setLocale(locales[random.nextInt(locales.length)]);
            t.setTag(tags[random.nextInt(tags.length)]);
            repository.save(t);
        });
    }
}
