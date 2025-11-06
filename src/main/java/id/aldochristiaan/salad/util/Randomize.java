package id.aldochristiaan.salad.util;

import java.security.SecureRandom;
import java.util.List;

/**
 * Utility class for generating random data.
 * Uses SecureRandom for better randomness.
 */
public class Randomize {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String ALPHANUMERIC = ALPHABET + NUMBERS;
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    private final SecureRandom secureRandom;

    public Randomize() {
        this.secureRandom = new SecureRandom();
    }

    /**
     * Generate random number from 0 to range (exclusive)
     */
    public int number(int range) {
        return secureRandom.nextInt(range);
    }

    /**
     * Generate random number between start (inclusive) and end (exclusive)
     */
    public int numberBetween(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("Start must be less than end");
        }
        return secureRandom.nextInt(end - start) + start;
    }

    /**
     * Generate random email address
     */
    public String email() {
        String localPart = randomAlphabetic(8).toLowerCase();
        String domain = randomAlphabetic(5).toLowerCase();
        return localPart + "@" + domain + ".com";
    }

    /**
     * Generate random email with custom domain
     */
    public String email(String domain) {
        String localPart = randomAlphabetic(8).toLowerCase();
        return localPart + "@" + domain;
    }

    /**
     * Generate random alphabetic string
     */
    public String randomAlphabetic(int length) {
        return randomFromCharset(ALPHABET, length);
    }

    /**
     * Generate random numeric string
     */
    public String randomNumeric(int length) {
        return randomFromCharset(NUMBERS, length);
    }

    /**
     * Generate random alphanumeric string
     */
    public String randomAlphanumeric(int length) {
        return randomFromCharset(ALPHANUMERIC, length);
    }

    /**
     * Generate random password with letters, numbers, and special characters
     */
    public String randomPassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters");
        }
        String allChars = ALPHANUMERIC + SPECIAL_CHARS;
        return randomFromCharset(allChars, length);
    }

    /**
     * Generate random boolean
     */
    public boolean randomBoolean() {
        return secureRandom.nextBoolean();
    }

    /**
     * Pick random element from array
     */
    public <T> T randomElement(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        return array[secureRandom.nextInt(array.length)];
    }

    /**
     * Pick random element from list
     */
    public <T> T randomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }
        return list.get(secureRandom.nextInt(list.size()));
    }

    /**
     * Generate random UUID-like string
     */
    public String randomUUID() {
        return String.format("%s-%s-%s-%s-%s",
                randomAlphanumeric(8),
                randomAlphanumeric(4),
                randomAlphanumeric(4),
                randomAlphanumeric(4),
                randomAlphanumeric(12));
    }

    /**
     * Generate random phone number (format: XXX-XXX-XXXX)
     */
    public String randomPhoneNumber() {
        return String.format("%s-%s-%s",
                randomNumeric(3),
                randomNumeric(3),
                randomNumeric(4));
    }

    /**
     * Generate random string from custom charset
     */
    private String randomFromCharset(String charset, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length must be non-negative");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(charset.length());
            sb.append(charset.charAt(index));
        }
        return sb.toString();
    }
}
