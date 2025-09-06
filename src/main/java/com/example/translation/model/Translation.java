package com.example.translation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "translations")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "translation_key")
    private String key;
    @Column(name = "translation_value")
    private String value;
    private String locale;
    private String tag;

    public Translation() {}

    public Translation(Long id, String key, String value, String locale, String tag) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.locale = locale;
        this.tag = tag;
    }

    public Translation(String key, String value, String locale, String tag) {
        this.key = key;
        this.value = value;
        this.locale = locale;
        this.tag = tag;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getLocale() { return locale; }
    public void setLocale(String locale) { this.locale = locale; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
}
