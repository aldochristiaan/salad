package id.aldochristiaan.salad.util;

import com.github.javafaker.Faker;

public class FakerUtil {

    public String getFakeName() {
        return new Faker().name().fullName();
    }

    public String getFakeAccountNumber() {
        return new Faker().number().digits(12);
    }

    public String getFakeAddress() {
        return new Faker().address().fullAddress();
    }

    public String getChuckNorrisFact() {
        return new Faker().chuckNorris().fact();
    }
}
