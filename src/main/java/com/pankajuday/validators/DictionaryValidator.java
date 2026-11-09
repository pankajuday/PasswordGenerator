package com.pankajuday.validators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class DictionaryValidator {
    private static final String DICTIONARY_RESOURCE = "/forbidden_words.txt";
    private final List<String> forbiddenWords;

    public DictionaryValidator() {
        forbiddenWords = loadWords();
    }

    public boolean containsKnownWord(String value) {
        String candidate = value.toLowerCase(java.util.Locale.ROOT);
        return forbiddenWords.stream().anyMatch(candidate::contains);
    }

    private List<String> loadWords() {
        InputStream resource = DictionaryValidator.class.getResourceAsStream(DICTIONARY_RESOURCE);
        if (resource == null) {
            throw new IllegalStateException("Missing resource: " + DICTIONARY_RESOURCE);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))) {
            return reader.lines().map(String::trim).filter(word -> !word.isEmpty()).map(word -> word.toLowerCase(java.util.Locale.ROOT)).toList();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load forbidden words.", exception);
        }
    }
}
