package id.aldochristiaan.salad.module.general;

import org.apache.commons.lang3.RandomUtils;

import java.security.SecureRandom;

public class Randomize {

    public int number(int range) {
        return new SecureRandom().nextInt(range);
    }

    public int numberBetween(int start, int end) {
        return RandomUtils.nextInt(start, end);
    }
}
