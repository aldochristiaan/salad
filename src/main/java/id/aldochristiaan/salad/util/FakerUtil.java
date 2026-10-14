package id.aldochristiaan.salad.util;

import net.datafaker.Faker;

/**
 * Utility class for generating fake data using Datafaker library.
 * Uses singleton pattern to reuse Faker instance across the framework.
 */
public class FakerUtil {

    private static volatile FakerUtil instance;
    private final Faker faker;

    private FakerUtil() {
        this.faker = new Faker();
    }

    /**
     * Get singleton instance
     */
    public static FakerUtil getInstance() {
        if (instance == null) {
            synchronized (FakerUtil.class) {
                if (instance == null) {
                    instance = new FakerUtil();
                }
            }
        }
        return instance;
    }

    /**
     * Get the Faker instance for custom operations
     */
    public Faker faker() {
        return faker;
    }

    // Personal Information
    public String getFakeName() {
        return faker.name().fullName();
    }

    public String getFakeFirstName() {
        return faker.name().firstName();
    }

    public String getFakeLastName() {
        return faker.name().lastName();
    }

    // Contact Information
    public String getFakeEmail() {
        return faker.internet().emailAddress();
    }

    public String getFakePhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    public String getFakeAddress() {
        return faker.address().fullAddress();
    }

    public String getFakeCity() {
        return faker.address().city();
    }

    public String getFakeZipCode() {
        return faker.address().zipCode();
    }

    // Financial
    public String getFakeAccountNumber() {
        return faker.number().digits(12);
    }

    public String getFakeCreditCardNumber() {
        return faker.finance().creditCard();
    }

    public String getFakeIBAN() {
        return faker.finance().iban();
    }

    // Business
    public String getFakeCompany() {
        return faker.company().name();
    }

    public String getFakeJobTitle() {
        return faker.job().title();
    }

    // Fun
    public String getChuckNorrisFact() {
        return faker.chuckNorris().fact();
    }

    // Numbers
    public String getRandomDigits(int count) {
        return faker.number().digits(count);
    }

    public int getRandomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    /**
     * Reset instance (mainly for testing purposes)
     */
    public static void resetInstance() {
        instance = null;
    }
}
