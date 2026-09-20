package md.moldcell.steps;

import io.cucumber.java.en.Then;
import md.moldcell.driver.DriverSession;
import md.moldcell.enums.PageName;
import org.junit.jupiter.api.Assertions;

public class CommonSteps {

    @Then("the {} page is displayed")
    public void pageIsDisplayed(String pageValue) {
        PageName pageName = PageName.fromValue(pageValue);
        Assertions.assertTrue(
                pageName.isDisplayed(DriverSession.driver()),
                pageValue + " page is not displayed."
        );
    }
}
