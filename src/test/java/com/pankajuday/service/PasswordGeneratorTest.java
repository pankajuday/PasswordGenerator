package com.pankajuday.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.pankajuday.validators.DictionaryValidator;
import org.junit.jupiter.api.Test;

class PasswordGeneratorTest {
    @Test
    void createsPasswordOfRequestedLengthWithoutKnownWords() {
        String password = new PasswordGenerator().generate(24, true, true, true, true, true);

        assertEquals(24, password.length());
        assertFalse(new DictionaryValidator().containsKnownWord(password));
    }

    @Test
    void respectsDigitsOnlyCharacterSelection() {
        String password = new PasswordGenerator().generate(16, false, false, true, false, false);

        assertEquals(16, password.length());
        assertTrue(password.matches("[0-9]+"));
    }
}
