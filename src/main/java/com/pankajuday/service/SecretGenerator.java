package com.pankajuday.service;

import java.security.SecureRandom;
import java.util.Base64;

public final class SecretGenerator {
    private final SecureRandom random = new SecureRandom();

    public String generate(int bytes, Format format) {
        byte[] secret = new byte[bytes];
        random.nextBytes(secret);
        return switch (format) {
            case BASE64URL -> Base64.getUrlEncoder().withoutPadding().encodeToString(secret);
            case HEX -> toHex(secret);
        };
    }

    private String toHex(byte[] bytes) {
        StringBuilder value = new StringBuilder(bytes.length * 2);
        for (byte currentByte : bytes) {
            value.append(String.format("%02x", currentByte));
        }
        return value.toString();
    }

    public enum Format {
        BASE64URL,
        HEX
    }
}
