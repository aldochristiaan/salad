package android.test;

import android.AndroidFactory;
import android.TestListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestListener.class)
public class AndroidTest extends AndroidFactory {

    @Test
    public void testA() {
        android.homePage().isOnMainPage();
        android.homePage().tapOnFAB();
        android.homePage().tapMoreOptions();
        android.homePage().openDrawer();
        android.homePage().validateDrawer();
        android.homePage().closeDrawer();
        android.homePage().goToPages();
        android.homePage().failedMethod();
    }

    @Test
    public void testB() {
        android.homePage().isOnMainPage();
        android.homePage().tapOnFAB();
        android.homePage().tapMoreOptions();
        android.homePage().openDrawer();
        android.homePage().validateDrawer();
        android.homePage().closeDrawer();
        android.homePage().goToPages();
        android.homePage().debugElementUsingFlash();
    }
}
