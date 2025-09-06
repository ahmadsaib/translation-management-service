package com.example.translation.repository;

import com.example.translation.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {

    List<Translation> findByLocale(String locale);

    List<Translation> findByTag(String tag);

    List<Translation> findByKeyContainingIgnoreCaseOrValueContainingIgnoreCase(String key, String value);

    List<Translation> findByLocaleAndTag(String locale, String tag);
}
