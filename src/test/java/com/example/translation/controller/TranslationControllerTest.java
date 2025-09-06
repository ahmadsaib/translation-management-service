package com.example.translation.controller;

import com.example.translation.dto.TranslationRequest;
import com.example.translation.dto.TranslationResponse;
import com.example.translation.service.TranslationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TranslationController.class)
@AutoConfigureMockMvc(addFilters = false) // 🚫 disables TokenAuthenticationFilter
public class TranslationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TranslationService translationService;

    @Autowired
    private ObjectMapper objectMapper;

    private TranslationResponse sampleResponse;

    @BeforeEach
    public void setup() {
        sampleResponse = new TranslationResponse(
                1L,
                "welcome",
                "Welcome",
                "en",
                "web"
        );
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void testGetTranslationById() throws Exception {
        when(translationService.getById(1L)).thenReturn(sampleResponse);

        mockMvc.perform(get("/api/translations/1")
                        .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("welcome"))
                .andExpect(jsonPath("$.value").value("Welcome"))
                .andExpect(jsonPath("$.locale").value("en"))
                .andExpect(jsonPath("$.tag").value("web"));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void testCreateTranslation() throws Exception {
        TranslationRequest request = new TranslationRequest("hello", "Hello", "en", "mobile");

        when(translationService.create(Mockito.any())).thenReturn(sampleResponse);

        mockMvc.perform(post("/api/translations")
                        .header("Authorization", "Bearer test-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.key").value("welcome"));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void testUpdateTranslation() throws Exception {
        TranslationRequest request = new TranslationRequest("welcome", "Bienvenue", "fr", "web");

        when(translationService.update(Mockito.eq(1L), Mockito.any())).thenReturn(sampleResponse);

        mockMvc.perform(put("/api/translations/1")
                        .header("Authorization", "Bearer test-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("welcome"));
    }



}
