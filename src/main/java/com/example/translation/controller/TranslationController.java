package com.example.translation.controller;

import com.example.translation.dto.TranslationRequest;
import com.example.translation.dto.TranslationResponse;
import com.example.translation.service.TranslationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Translation API")
@RestController
@RequestMapping("/api/translations")
@RequiredArgsConstructor
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping
    public ResponseEntity<TranslationResponse> createTranslation(@Valid @RequestBody TranslationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED) // 🔥 this sets 201 Created
                .body(translationService.create(request));
    }



    @PutMapping("/{id}")
    public ResponseEntity<TranslationResponse> updateTranslation(
            @PathVariable Long id,
            @Valid @RequestBody TranslationRequest request) {
        return ResponseEntity.ok(translationService.update(id, request));
    }



    @GetMapping("/{id}")
    public ResponseEntity<TranslationResponse> getTranslation(@PathVariable Long id) {
        try {
            TranslationResponse response = translationService.getById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build(); // return 404 Not Found if not present
        }
    }


    @GetMapping
    public ResponseEntity<List<TranslationResponse>> searchTranslations(
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String value) {
        return ResponseEntity.ok(translationService.search(tag, key, value));
    }

    @GetMapping("/export")
    public ResponseEntity<List<TranslationResponse>> exportTranslations() {
        return ResponseEntity.ok(translationService.getAllTranslations());
    }


    @PostMapping("/generate-sample")
    public ResponseEntity<?> generateSample(@RequestParam int count) {
        try {
            translationService.generateSampleData(count);
            return ResponseEntity.ok("Generated " + count + " translations.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
        }
    }
}
