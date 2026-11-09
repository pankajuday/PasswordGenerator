package com.pankajuday.validators;

public final class PatternValidator {
    private static final int MINIMUM_PASSWORD_LENGTH = 12;
    private static final int MINIMUM_SECRET_BYTES = 16;
    private static final int MAXIMUM_COUNT = 100;

    private PatternValidator() {
    }

    public static void validatePasswordOptions(int length, int count, boolean uppercase, boolean lowercase, boolean numbers, boolean symbols) {
        if (length < MINIMUM_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password length must be at least " + MINIMUM_PASSWORD_LENGTH + ".");
        }
        validateCount(count);
        if (!uppercase && !lowercase && !numbers && !symbols) {
            throw new IllegalArgumentException("Choose at least one character type.");
        }
    }

    public static void validateSecretOptions(int bytes, int count) {
        if (bytes < MINIMUM_SECRET_BYTES) {
            throw new IllegalArgumentException("Secret size must be at least " + MINIMUM_SECRET_BYTES + " bytes.");
        }
        validateCount(count);
    }

    private static void validateCount(int count) {
        if (count < 1 || count > MAXIMUM_COUNT) {
            throw new IllegalArgumentException("Count must be between 1 and " + MAXIMUM_COUNT + ".");
        }
    }
}
