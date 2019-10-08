package android.test;

import android.AndroidFactory;
import org.junit.jupiter.api.Test;

public class AndroidTest extends AndroidFactory {

    @Test
    public void loginTest(){
        android.homePage().isOnMainPage();
        android.homePage().tapOnFAB();
        android.homePage().tapMoreOptions();
        android.homePage().openDrawer();
        android.homePage().validateDrawer();
        android.homePage().closeDrawer();
        android.homePage().goToPages();
    }
}
