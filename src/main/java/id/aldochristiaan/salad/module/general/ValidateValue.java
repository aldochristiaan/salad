package id.aldochristiaan.salad.module.general;

import org.junit.Assert;

public class ValidateValue {

    public void equals(String expected, String actual) {
        Assert.assertEquals("Actual value : " + actual + " is different with expected : " + expected + " !", expected, actual);
    }

    public void equals(String expected, String actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void equals(boolean expected, boolean actual) {
        Assert.assertEquals("Actual value : " + actual + " is different with expected : " + expected + " !", expected, actual);
    }

    public void equals(boolean expected, boolean actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void equals(int expected, int actual) {
        Assert.assertEquals("Actual value : " + actual + " is different with expected : " + expected + " !", expected, actual);
    }

    public void equals(int expected, int actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void equals(long expected, long actual) {
        Assert.assertEquals("Actual value : " + actual + " is different with expected : " + expected + " !", expected, actual);
    }

    public void equals(long expected, long actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void equals(double expected, double actual, double delta) {
        Assert.assertEquals("Actual value : " + actual + " is different with expected : " + expected + " !", expected, actual, delta);
    }

    public void equals(double expected, double actual, double delta, String message) {
        Assert.assertEquals(message, expected, actual, delta);
    }

    public void notEquals(String expected, String actual) {
        Assert.assertNotEquals("Actual value : " + actual + " is equals with expected : " + expected + " !", expected, actual);
    }

    public void notEquals(String expected, String actual, String message) {
        Assert.assertNotEquals(message, expected, actual);
    }

    public void notEquals(boolean expected, boolean actual) {
        Assert.assertNotEquals("Actual value : " + actual + " is equals with expected : " + expected + " !", expected, actual);
    }

    public void notEquals(boolean expected, boolean actual, String message) {
        Assert.assertNotEquals(message, expected, actual);
    }

    public void notEquals(int expected, int actual) {
        Assert.assertNotEquals("Actual value : " + actual + " is equals with expected : " + expected + " !", expected, actual);
    }

    public void notEquals(int expected, int actual, String message) {
        Assert.assertNotEquals(message, expected, actual);
    }

    public void notEquals(long expected, long actual) {
        Assert.assertNotEquals("Actual value : " + actual + " is equals with expected : " + expected + " !", expected, actual);
    }

    public void notEquals(long expected, long actual, String message) {
        Assert.assertNotEquals(message, expected, actual);
    }

    public void notEquals(double expected, double actual, double delta) {
        Assert.assertNotEquals("Actual value : " + actual + " is equals with expected : " + expected + " !", expected, actual, delta);
    }

    public void notEquals(double expected, double actual, double delta, String message) {
        Assert.assertNotEquals(message, expected, actual, delta);
    }

    public void equalsTrue(boolean condition) {
        Assert.assertTrue("This condition is false!", condition);
    }

    public void equalsTrue(boolean condition, String message) {
        Assert.assertTrue(message, condition);
    }

    public void equalsFalse(boolean condition) {
        Assert.assertFalse("This condition is true!", condition);
    }

    public void equalsFalse(boolean condition, String message) {
        Assert.assertFalse(message, condition);
    }

    public void contains(String actual, String expected) {
        Assert.assertTrue(expected + "is not contained in " + actual, actual.contains(expected));
    }

    public void contains(String actual, String expected, String message) {
        Assert.assertTrue(message, actual.contains(expected));
    }

    public boolean isContains(String actual, String expected) {
        return actual.contains(expected);
    }
}
