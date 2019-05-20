package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;

import java.time.Duration;

public class Swipe extends Ios {

    public Swipe(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void toLocator(String elementLocator) {
        findElementBy(getLocator(elementLocator));
    }

    public void toLocator(String elementLocator, Direction direction) {
        findElementBy(getLocator(elementLocator), direction);
    }

    public void to(double xStart, double xEnd, double yStart, double yEnd) {

        Dimension size = iosDriver.manage().window().getSize();

        int x0 = (int) (size.width * xStart);
        int x1 = (int) (size.width * xEnd);
        int y0 = (int) (size.height * yStart);
        int y1 = (int) (size.height * yEnd);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x0, y0)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x1, y1)).release().perform();
    }

    public void toDirection(Direction direction) {
        switch (direction) {
            case UP:
                up();
                break;
            case DOWN:
                down();
                break;
            case LEFT:
                left();
                break;
            case RIGHT:
                right();
                break;
            default:
                throw new NotFoundException("Couldn't find direction");
        }
    }

    public void up() {

        Dimension size = iosDriver.manage().window().getSize();

        int y0 = (int) (size.height * 0.7);
        int y1 = (int) (size.height * 0.3);
        int x = (size.width / 2);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x, y0)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x, y1)).release().perform();
    }

    public void up(double yStart, double yEnd) {

        Dimension size = iosDriver.manage().window().getSize();

        int y0 = (int) (size.height * yStart);
        int y1 = (int) (size.height * yEnd);
        int x = (size.width / 2);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x, y0)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x, y1)).release().perform();
    }

    public void upAtSpecifiedLocator(String elementLocator) {

        By by = getLocator(elementLocator);

        Dimension size = iosDriver.findElement(by).getSize();

        Point point = iosDriver.findElement(by).getCenter();

        int y0 = (int) (size.height * 0.7);
        int y1 = (int) (size.height * 0.3);
        int x = point.getX();

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x, y0)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x, y1)).release().perform();
    }

    public void down() {

        Dimension size = iosDriver.manage().window().getSize();

        int y0 = (int) (size.height * 0.7);
        int y1 = (int) (size.height * 0.3);
        int x = (size.width / 2);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x, y1)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x, y0)).release().perform();
    }

    public void down(double yStart, double yEnd) {

        Dimension size = iosDriver.manage().window().getSize();

        int y0 = (int) (size.height * yEnd);
        int y1 = (int) (size.height * yStart);
        int x = (size.width / 2);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x, y1)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x, y0)).release().perform();
    }

    public void downAtSpecifiedLocator(String elementLocator) {

        By by = getLocator(elementLocator);

        Dimension size = iosDriver.findElement(by).getSize();

        Point point = iosDriver.findElement(by).getCenter();

        int y0 = (int) (size.height * 0.7);
        int y1 = (int) (size.height * 0.3);
        int x = point.getX();

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x, y1)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x, y0)).release().perform();
    }

    public void left() {

        Dimension size = iosDriver.manage().window().getSize();

        int x0 = (int) (size.width * 0.8);
        int x1 = (int) (size.width * 0.2);
        int y = (size.height / 2);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x0, y)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x1, y)).release().perform();
    }

    public void left(double xStart, double xEnd) {

        Dimension size = iosDriver.manage().window().getSize();

        int x0 = (int) (size.width * xStart);
        int x1 = (int) (size.width * xEnd);
        int y = (size.height / 2);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x0, y)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x1, y)).release().perform();
    }

    public void leftAtSpecifiedLocator(String elementLocator) {

        By by = getLocator(elementLocator);

        Dimension size = iosDriver.findElement(by).getSize();

        Point point = iosDriver.findElement(by).getCenter();

        int x0 = (int) (size.width * 0.8);
        int x1 = (int) (size.width * 0.2);
        int y = point.getY();

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x0, y)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x1, y)).release().perform();
    }

    public void right() {

        Dimension size = iosDriver.manage().window().getSize();

        int x0 = (int) (size.width * 0.8);
        int x1 = (int) (size.width * 0.2);
        int y = (size.height / 2);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x1, y)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x0, y)).release().perform();
    }

    public void right(double xStart, double xEnd) {

        Dimension size = iosDriver.manage().window().getSize();

        int x0 = (int) (size.width * xEnd);
        int x1 = (int) (size.width * xStart);
        int y = (size.height / 2);

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x1, y)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x0, y)).release().perform();
    }

    public void rightAtSpecifiedLocator(String elementLocator) {

        By by = getLocator(elementLocator);

        Dimension size = iosDriver.findElement(by).getSize();
        Point point = iosDriver.findElement(by).getCenter();

        int x0 = (int) (size.width * 0.8);
        int x1 = (int) (size.width * 0.2);
        int y = point.getY();

        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x1, y)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1))).moveTo(new PointOption().withCoordinates(x0, y)).release().perform();
    }
}
