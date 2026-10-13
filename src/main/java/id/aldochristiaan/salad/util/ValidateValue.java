package id.aldochristiaan.salad.util;

import java.util.Objects;

/**
 * Validation helper that throws {@link AssertionError} on mismatch.
 * <p>
 * Historically this class delegated to {@code org.junit.Assert}, which forced a JUnit 4
 * runtime dependency onto every consumer of the Salad library. It now raises
 * {@code AssertionError} directly. Since JUnit's {@code AssertionFailedError} extends
 * {@code AssertionError}, test runners (JUnit 4/5, TestNG) still report these as
 * ordinary assertion failures, so existing behaviour is preserved.
 */
public class ValidateValue {

    private static void fail(String message) {
        throw new AssertionError(message);
    }

    public void equals(String expected, String actual) {
        equals(expected, actual, "Expected: " + expected + ", but got: " + actual);
    }

    public void equals(String expected, String actual, String message) {
        if (!Objects.equals(expected, actual)) {
            fail(message);
        }
    }

    public void equals(boolean expected, boolean actual) {
        equals(expected, actual, "Expected: " + expected + ", but got: " + actual);
    }

    public void equals(boolean expected, boolean actual, String message) {
        if (expected != actual) {
            fail(message);
        }
    }

    public void equals(int expected, int actual) {
        equals(expected, actual, "Expected: " + expected + ", but got: " + actual);
    }

    public void equals(int expected, int actual, String message) {
        if (expected != actual) {
            fail(message);
        }
    }

    public void equals(long expected, long actual) {
        equals(expected, actual, "Expected: " + expected + ", but got: " + actual);
    }

    public void equals(long expected, long actual, String message) {
        if (expected != actual) {
            fail(message);
        }
    }

    public void equals(double expected, double actual, double delta) {
        equals(expected, actual, delta, "Expected: " + expected + ", but got: " + actual);
    }

    public void equals(double expected, double actual, double delta, String message) {
        if (Double.compare(expected, actual) != 0 && Math.abs(expected - actual) > delta) {
            fail(message);
        }
    }

    public void notEquals(String expected, String actual) {
        notEquals(expected, actual, "Expected not to be: " + expected + ", but got: " + actual);
    }

    public void notEquals(String expected, String actual, String message) {
        if (Objects.equals(expected, actual)) {
            fail(message);
        }
    }

    public void notEquals(boolean expected, boolean actual) {
        notEquals(expected, actual, "Expected not to be: " + expected + ", but got: " + actual);
    }

    public void notEquals(boolean expected, boolean actual, String message) {
        if (expected == actual) {
            fail(message);
        }
    }

    public void notEquals(int expected, int actual) {
        notEquals(expected, actual, "Expected not to be: " + expected + ", but got: " + actual);
    }

    public void notEquals(int expected, int actual, String message) {
        if (expected == actual) {
            fail(message);
        }
    }

    public void notEquals(long expected, long actual) {
        notEquals(expected, actual, "Expected not to be: " + expected + ", but got: " + actual);
    }

    public void notEquals(long expected, long actual, String message) {
        if (expected == actual) {
            fail(message);
        }
    }

    public void notEquals(double expected, double actual, double delta) {
        notEquals(expected, actual, delta, "Expected not to be: " + expected + ", but got: " + actual);
    }

    public void notEquals(double expected, double actual, double delta, String message) {
        if (Double.compare(expected, actual) == 0 || Math.abs(expected - actual) <= delta) {
            fail(message);
        }
    }

    public void equalsTrue(boolean condition) {
        equalsTrue(condition, "Expected condition to be true, but it was false");
    }

    public void equalsTrue(boolean condition, String message) {
        if (!condition) {
            fail(message);
        }
    }

    public void equalsFalse(boolean condition) {
        equalsFalse(condition, "Expected condition to be false, but it was true");
    }

    public void equalsFalse(boolean condition, String message) {
        if (condition) {
            fail(message);
        }
    }

    public void contains(String expected, String actual) {
        contains(expected, actual, expected + " was not found in: " + actual);
    }

    public void contains(String expected, String actual, String message) {
        if (actual == null || !actual.contains(expected)) {
            fail(message);
        }
    }

    public boolean isContains(String expected, String actual) {
        return actual != null && actual.contains(expected);
    }
}
