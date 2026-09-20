package md.moldcell.steps;

import io.cucumber.java.en.And;
import md.moldcell.driver.DriverSession;
import md.moldcell.pages.ErrorPopup;
import md.moldcell.pages.MyAccountEmailPage;
import md.moldcell.pages.MyAccountMobilePage;
import org.junit.jupiter.api.Assertions;

public class AccountSteps {
    private ErrorPopup errorPopup() {
        return new ErrorPopup(DriverSession.driver());
    }

    private MyAccountMobilePage myAccountMobilePage() {
        return new MyAccountMobilePage(DriverSession.driver());
    }

    private MyAccountEmailPage myAccountEmailPage() {
        return new MyAccountEmailPage(DriverSession.driver());
    }

    @And("Ooops popup is displayed")
    public void ooopsPopupIsDisplayed() {
        Assertions.assertTrue(errorPopup().isDisplayed(), "Ooops popup is not displayed.");
    }

    @And("My account mobile page is displayed")
    public void myAccountMobilePageIsDisplayed() {
        Assertions.assertTrue(myAccountMobilePage().isDisplayed(),
                "My account mobile page is not displayed.");
    }

    @And("My account email page is displayed")
    public void myAccountEmailPageIsDisplayed() {
        Assertions.assertTrue(myAccountEmailPage().isDisplayed(),
                "My account email page is not displayed.");
    }
}
