package com.pankajuday.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Base64;
import org.junit.jupiter.api.Test;

class SecretGeneratorTest {
    @Test
    void createsUrlSafeBase64SecretWithRequestedEntropy() {
        String secret = new SecretGenerator().generate(32, SecretGenerator.Format.BASE64URL);

        assertTrue(secret.matches("[A-Za-z0-9_-]+"));
        assertEquals(32, Base64.getUrlDecoder().decode(secret).length);
    }

    @Test
    void createsLowercaseHexSecretWithRequestedLength() {
        String secret = new SecretGenerator().generate(32, SecretGenerator.Format.HEX);

        assertEquals(64, secret.length());
        assertTrue(secret.matches("[0-9a-f]+"));
    }
}
