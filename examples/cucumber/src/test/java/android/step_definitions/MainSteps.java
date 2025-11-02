package android.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static android.AndroidFactory.android;

public class MainSteps {

    @Given("user is on main page")
    public void userIsOnMainPage() {
        android.mainPage().isOnMainPage();
    }

    @Then("user is doing validation")
    public void userIsDoingValidation() {
        android.mainPage().tapOnFAB();
        android.mainPage().tapMoreOptions();
        android.mainPage().openDrawer();
        android.mainPage().validateDrawer();
        android.mainPage().closeDrawer();
        android.mainPage().goToPages();
    }

    @Then("user is facing an error")
    public void userIsFacingAnError() {
        android.mainPage().goToPages();
        android.mainPage().failedMethod();
    }
}
