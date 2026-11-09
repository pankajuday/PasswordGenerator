package com.pankajuday.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PasswordPolicyResourceTest {
    @Test
    void findsForbiddenWordsWithoutCaseSensitivity() {
        DictionaryValidator validator = new DictionaryValidator();

        assertTrue(validator.containsKnownWord("myPasswordValue"));
        assertTrue(validator.containsKnownWord("PREFIXqWeRtYsUFFIX"));
        assertFalse(validator.containsKnownWord("x9!Z_r-42"));
    }

    @Test
    void replacesConfiguredCharacters() {
        SubstitutionNormalizer normalizer = new SubstitutionNormalizer();

        assertEquals("@370", normalizer.replaceKnownWordCharacters("AETO"));
    }
}
