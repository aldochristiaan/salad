package id.aldochristiaan.salad.util;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerUtil {

    public Faker faker() {
        return new Faker();
    }

    public Faker faker(String language) {
        return new Faker(new Locale(language));
    }

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
