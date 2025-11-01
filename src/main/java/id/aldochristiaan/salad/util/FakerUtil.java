package id.aldochristiaan.salad.util;

import net.datafaker.Faker;

import java.util.Locale;

public class FakerUtil {

    private final Faker defaultFaker;

    public FakerUtil() {
        this.defaultFaker = new Faker();
    }

    public Faker faker() {
        return defaultFaker;
    }

    public String getFakeName() {
        return defaultFaker.name().fullName();
    }

    public String getFakeAccountNumber() {
        return defaultFaker.number().digits(12);
    }

    public String getFakeAddress() {
        return defaultFaker.address().fullAddress();
    }

    public String getChuckNorrisFact() {
        return defaultFaker.chuckNorris().fact();
    }

    public String getFakeEmail() {
        return defaultFaker.internet().emailAddress();
    }

    public String getFakePhoneNumber() {
        return defaultFaker.phoneNumber().cellPhone();
    }

    public String getFakeCompany() {
        return defaultFaker.company().name();
    }

    public String getFakeJobTitle() {
        return defaultFaker.job().title();
    }

    public String getFakeCreditCardNumber() {
        return defaultFaker.finance().creditCard();
    }
}
