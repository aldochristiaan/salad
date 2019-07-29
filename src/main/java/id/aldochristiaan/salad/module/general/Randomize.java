package id.aldochristiaan.salad.module.general;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.security.SecureRandom;

public class Randomize {

    public int number(int range) {
        return new SecureRandom().nextInt(range);
    }

    public int numberBetween(int start, int end) {
        return RandomUtils.nextInt(start, end);
    }

    public String email() {
        String randomString = RandomStringUtils.randomAlphabetic(8);
        return (randomString + "@" + randomString + ".com").toLowerCase();
    }
}
