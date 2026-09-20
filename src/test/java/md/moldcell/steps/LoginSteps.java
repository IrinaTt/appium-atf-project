package md.moldcell.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import md.moldcell.config.TestConfig;
import md.moldcell.driver.DriverSession;
import md.moldcell.pages.LoginPage;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
    private LoginPage loginPage() {
        return new LoginPage(DriverSession.driver());
    }

    @Then("header contains value {}")
    public void headerContainsValue(String expectedHeader) {
        Assertions.assertTrue(loginPage().headerText().contains(expectedHeader),
                "Login header does not contain: " + expectedHeader);
    }

    @Then("message text has value {}")
    public void messageTextHasValue(String expectedMessage) {
        LoginPage page = loginPage();
        assertEquals(page.normalize(expectedMessage), page.descriptionText(),
                "Unexpected login message.");
    }

    @Then("forgot password button has text value {} and is enabled")
    public void forgotPasswordButtonHasTextAndIsEnabled(String expectedText) {
        Assertions.assertAll(
                () -> assertEquals(expectedText, loginPage().forgotPasswordText()),
                () -> Assertions.assertTrue(loginPage().isForgotPasswordEnabled(),
                        "Forgot password button is disabled."));
    }

    @Then("registration button has text value {} and is enabled")
    public void registrationButtonHasTextAndIsEnabled(String expectedText) {
        Assertions.assertAll(
                () -> assertEquals(expectedText, loginPage().registrationText()),
                () -> Assertions.assertTrue(loginPage().isRegistrationEnabled(),
                        "Registration button is disabled."));
    }

    @When("user populates mandatory fields")
    public void userPopulatesMandatoryFields(DataTable dataTable) {
        for (List<String> row : dataTable.asLists(String.class)) {
            if (row.size() != 2) {
                throw new IllegalArgumentException("Each mandatory field row must contain a name and a value.");
            }
            switch (row.get(0)) {
                case "userName" -> loginPage().enterUsername(resolveFieldValue(row.get(1)));
                case "password" -> loginPage().enterPassword(resolveFieldValue(row.get(1)));
                default -> throw new IllegalArgumentException("Unsupported field: " + row.get(0));
            }
        }
    }

    @Then("the Username error message is {}")
    public void usernameErrorMessageIs(String expectedError) {
        LoginPage page = loginPage();
        if ("[none]".equals(expectedError)) {
            Assertions.assertFalse(page.isUsernameErrorDisplayed(),
                    "Username error message should not be displayed.");
            return;
        }
        assertEquals(expectedError, page.usernameErrorText(),
                "Unexpected Username error message.");
    }

    @Then("the Password error message is {}")
    public void passwordErrorMessageIs(String expectedError) {
        LoginPage page = loginPage();
        if ("[none]".equals(expectedError)) {
            Assertions.assertFalse(page.isPasswordErrorDisplayed(),
                    "Password error message should not be displayed.");
            return;
        }
        assertEquals(expectedError, page.passwordErrorText(),
                "Unexpected Password error message.");
    }

    private String resolveFieldValue(String value) {
        return switch (value) {
            case "[empty]" -> "";
            case "[spaces]" -> "   ";
            default -> TestConfig.resolveSecret(value);
        };
    }
}
