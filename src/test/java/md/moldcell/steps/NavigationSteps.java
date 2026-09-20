package md.moldcell.steps;

import io.cucumber.java.en.And;
import md.moldcell.driver.DriverSession;
import md.moldcell.pages.AuthMethodPage;
import md.moldcell.pages.ErrorPopup;
import md.moldcell.pages.LanguagePage;
import md.moldcell.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationSteps {
    private LanguagePage languagePage() {
        return new LanguagePage(DriverSession.driver());
    }

    private LoginPage loginPage() {
        return new LoginPage(DriverSession.driver());
    }

    private AuthMethodPage authMethodPage() {
        return new AuthMethodPage(DriverSession.driver());
    }

    private ErrorPopup errorPopup() {
        return new ErrorPopup(DriverSession.driver());
    }

    @And("user clicks on the {} button")
    public void userClicksOnButton(String buttonText) {
        if ("Continue".equals(buttonText)
                || "Continuă".equals(buttonText)
                || "Продолжить".equals(buttonText)) {
            LanguagePage page = languagePage();
            assertEquals(page.normalizeText(buttonText), page.getContinueButtonText(),
                    "Unexpected language page button text.");
            page.continueToLogin();
        } else if ("Login".equals(buttonText)
                || "Logare".equals(buttonText)
                || "Войти".equals(buttonText)) {
            LoginPage page = loginPage();
            assertEquals(buttonText, page.loginButtonText(),
                    "Unexpected login button text.");
            page.clickLoginButton();
        } else if ("Configure later".equals(buttonText)
                || "Configurează mai târziu".equals(buttonText)
                || "Настроить позже".equals(buttonText)) {
            AuthMethodPage page = authMethodPage();
            assertEquals(buttonText, page.laterButtonText(),
                    "Unexpected later button text.");
            page.clickLaterButton(buttonText);
        } else if ("Close".equals(buttonText)
                || "Închide".equals(buttonText)
                || "Закрыть".equals(buttonText)) {
            ErrorPopup page = errorPopup();
            assertEquals(buttonText, page.closeButtonText(),
                    "Unexpected close button text.");
            page.clickClose(buttonText);
        } else {
            throw new IllegalArgumentException("Unsupported button: " + buttonText);
        }
    }
}
