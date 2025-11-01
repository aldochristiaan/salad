package id.aldochristiaan.salad.util;

import java.security.SecureRandom;

public class Randomize {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final SecureRandom secureRandom = new SecureRandom();

    public int number(int range) {
        return secureRandom.nextInt(range);
    }

    public int numberBetween(int start, int end) {
        return secureRandom.nextInt(end - start) + start;
    }

    public String email() {
        String localPart = randomAlphabetic(8).toLowerCase();
        String domain = randomAlphabetic(5).toLowerCase();
        return localPart + "@" + domain + ".com";
    }

    public String randomAlphabetic(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }
}
