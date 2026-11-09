package com.pankajuday.service;

import com.pankajuday.validators.DictionaryValidator;
import com.pankajuday.validators.SubstitutionNormalizer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}:,.?";
    private static final int MAXIMUM_GENERATION_ATTEMPTS = 64;
    private final SecureRandom random = new SecureRandom();
    private final DictionaryValidator dictionaryValidator = new DictionaryValidator();
    private final SubstitutionNormalizer substitutionNormalizer = new SubstitutionNormalizer();

    public String generate(int length, boolean uppercase, boolean lowercase, boolean numbers, boolean symbols, boolean excludeAmbiguous) {
        List<String> characterGroups = selectedGroups(uppercase, lowercase, numbers, symbols, excludeAmbiguous);
        for (int attempt = 0; attempt < MAXIMUM_GENERATION_ATTEMPTS; attempt++) {
            String password = generateCandidate(length, characterGroups);
            if (!dictionaryValidator.containsKnownWord(password)) {
                return password;
            }
            String substituted = substitutionNormalizer.replaceKnownWordCharacters(password);
            if (!dictionaryValidator.containsKnownWord(substituted)) {
                return substituted;
            }
        }
        throw new IllegalStateException("Unable to generate a password that satisfies the dictionary policy.");
    }

    private List<String> selectedGroups(boolean uppercase, boolean lowercase, boolean numbers, boolean symbols, boolean excludeAmbiguous) {
        List<String> groups = new ArrayList<>();
        addGroup(groups, UPPERCASE, uppercase, excludeAmbiguous);
        addGroup(groups, LOWERCASE, lowercase, excludeAmbiguous);
        addGroup(groups, NUMBERS, numbers, excludeAmbiguous);
        addGroup(groups, SYMBOLS, symbols, excludeAmbiguous);
        return groups;
    }

    private void addGroup(List<String> groups, String characters, boolean selected, boolean excludeAmbiguous) {
        if (!selected) {
            return;
        }
        String group = excludeAmbiguous ? characters.replaceAll("[O0oIl1|]", "") : characters;
        if (!group.isEmpty()) {
            groups.add(group);
        }
    }

    private String generateCandidate(int length, List<String> groups) {
        String allCharacters = String.join("", groups);
        List<Character> characters = new ArrayList<>(length);
        for (String group : groups) {
            characters.add(randomCharacter(group));
        }
        while (characters.size() < length) {
            characters.add(randomCharacter(allCharacters));
        }
        Collections.shuffle(characters, random);
        StringBuilder password = new StringBuilder(length);
        characters.forEach(password::append);
        return password.toString();
    }

    private char randomCharacter(String characters) {
        return characters.charAt(random.nextInt(characters.length()));
    }
}
