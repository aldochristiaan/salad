package id.aldochristiaan.salad.util;

import org.junit.Assert;

public class ValidateValue {

    public void equals(String expected, String actual) {
        Assert.assertEquals("Expected: " + expected + ", but got: " + actual, expected, actual);
    }

    public void equals(String expected, String actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void equals(boolean expected, boolean actual) {
        Assert.assertEquals("Expected: " + expected + ", but got: " + actual, expected, actual);
    }

    public void equals(boolean expected, boolean actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void equals(int expected, int actual) {
        Assert.assertEquals("Expected: " + expected + ", but got: " + actual, expected, actual);
    }

    public void equals(int expected, int actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void equals(long expected, long actual) {
        Assert.assertEquals("Expected: " + expected + ", but got: " + actual, expected, actual);
    }

    public void equals(long expected, long actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void equals(double expected, double actual, double delta) {
        Assert.assertEquals("Expected: " + expected + ", but got: " + actual, expected, actual, delta);
    }

    public void equals(double expected, double actual, double delta, String message) {
        Assert.assertEquals(message, expected, actual, delta);
    }

    public void notEquals(String expected, String actual) {
        Assert.assertNotEquals("Expected not to be: " + expected + ", but got: " + actual, expected, actual);
    }

    public void notEquals(String expected, String actual, String message) {
        Assert.assertNotEquals(message, expected, actual);
    }

    public void notEquals(boolean expected, boolean actual) {
        Assert.assertNotEquals("Expected not to be: " + expected + ", but got: " + actual, expected, actual);
    }

    public void notEquals(boolean expected, boolean actual, String message) {
        Assert.assertNotEquals(message, expected, actual);
    }

    public void notEquals(int expected, int actual) {
        Assert.assertNotEquals("Expected not to be: " + expected + ", but got: " + actual, expected, actual);
    }

    public void notEquals(int expected, int actual, String message) {
        Assert.assertNotEquals(message, expected, actual);
    }

    public void notEquals(long expected, long actual) {
        Assert.assertNotEquals("Expected not to be: " + expected + ", but got: " + actual, expected, actual);
    }

    public void notEquals(long expected, long actual, String message) {
        Assert.assertNotEquals(message, expected, actual);
    }

    public void notEquals(double expected, double actual, double delta) {
        Assert.assertNotEquals("Expected not to be: " + expected + ", but got: " + actual, expected, actual, delta);
    }

    public void notEquals(double expected, double actual, double delta, String message) {
        Assert.assertNotEquals(message, expected, actual, delta);
    }

    public void equalsTrue(boolean condition) {
        Assert.assertTrue("Expected condition to be true, but it was false", condition);
    }

    public void equalsTrue(boolean condition, String message) {
        Assert.assertTrue(message, condition);
    }

    public void equalsFalse(boolean condition) {
        Assert.assertFalse("Expected condition to be false, but it was true", condition);
    }

    public void equalsFalse(boolean condition, String message) {
        Assert.assertFalse(message, condition);
    }

    public void contains(String expected, String actual) {
        Assert.assertTrue(expected + " was not found in: " + actual, actual.contains(expected));
    }

    public void contains(String expected, String actual, String message) {
        Assert.assertTrue(message, actual.contains(expected));
    }

    public boolean isContains(String expected, String actual) {
        return actual.contains(expected);
    }
}
