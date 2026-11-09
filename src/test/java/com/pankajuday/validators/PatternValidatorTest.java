package com.pankajuday.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PatternValidatorTest {
    @Test
    void acceptsValidPasswordOptions() {
        assertDoesNotThrow(() -> PatternValidator.validatePasswordOptions(12, 1, true, true, true, true));
    }

    @Test
    void rejectsShortPassword() {
        assertThrows(IllegalArgumentException.class, () -> PatternValidator.validatePasswordOptions(11, 1, true, true, true, true));
    }

    @Test
    void rejectsPasswordWithoutCharacterGroups() {
        assertThrows(IllegalArgumentException.class, () -> PatternValidator.validatePasswordOptions(12, 1, false, false, false, false));
    }

    @Test
    void rejectsInvalidSecretSizeAndCount() {
        assertThrows(IllegalArgumentException.class, () -> PatternValidator.validateSecretOptions(15, 1));
        assertThrows(IllegalArgumentException.class, () -> PatternValidator.validateSecretOptions(16, 101));
    }
}
