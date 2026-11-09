package com.pankajuday.validators;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public final class SubstitutionNormalizer {
    private static final String SUBSTITUTIONS_RESOURCE = "/substitutions.properties";
    private final Map<Character, Character> substitutions;

    public SubstitutionNormalizer() {
        substitutions = loadSubstitutions();
    }

    public String replaceKnownWordCharacters(String value) {
        StringBuilder result = new StringBuilder(value.length());
        for (char character : value.toCharArray()) {
            Character replacement = substitutions.get(Character.toUpperCase(character));
            result.append(replacement == null ? character : replacement);
        }
        return result.toString();
    }

    private Map<Character, Character> loadSubstitutions() {
        Properties properties = new Properties();
        try (InputStream resource = SubstitutionNormalizer.class.getResourceAsStream(SUBSTITUTIONS_RESOURCE)) {
            if (resource == null) {
                throw new IllegalStateException("Missing resource: " + SUBSTITUTIONS_RESOURCE);
            }
            properties.load(resource);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load substitutions.", exception);
        }
        Map<Character, Character> result = new HashMap<>();
        properties.forEach((replacement, original) -> result.put(original.toString().toUpperCase(Locale.ROOT).charAt(0), replacement.toString().charAt(0)));
        return Map.copyOf(result);
    }
}
