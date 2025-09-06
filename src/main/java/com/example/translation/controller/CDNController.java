package com.example.translation.controller;

import com.example.translation.dto.TranslationResponse;
import com.example.translation.service.TranslationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "CDN-Compatible Translation API")
@RestController
@RequestMapping("/cdn/translations")
@RequiredArgsConstructor
public class CDNController {

    @Autowired
    private TranslationService translationService;

    @GetMapping("/{locale}")
    public ResponseEntity<List<TranslationResponse>> getTranslationsByLocale(@PathVariable String locale) {
        System.out.println("locale"+locale);
        List<TranslationResponse> translations = translationService.getByLocale(locale);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic())
                .body(translations);
    }
}
