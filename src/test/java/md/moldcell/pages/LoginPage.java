package md.moldcell.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final By screen = resourceId("login_screen");
    private final By usernameInput = resourceId("login_input");
    private final By passwordInput = resourceId("password_input");
    private final By loginButtonText = resourceId("login_button_text");
    private final By firstHeader = resourceId("login_title_first");
    private final By secondHeader = resourceId("login_title_second");
    private final By description = resourceId("login_description");
    private final By forgotPasswordButton = resourceId("forgot_password_button");
    private final By registrationButton = resourceId("create_account_button");
    private final By usernameError = resourceId("login_input_error_message");
    private final By passwordError = resourceId("password_input_error_message");

    public LoginPage(AndroidDriver driver) {
        super(driver, Duration.ofSeconds(20));
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(screen)).isDisplayed();
    }

    public String headerText() {
        return normalize(wait.until(ExpectedConditions.visibilityOfElementLocated(firstHeader)).getText()
                + " " + wait.until(ExpectedConditions.visibilityOfElementLocated(secondHeader)).getText());
    }

    public String descriptionText() {
        return text(description);
    }

    public String forgotPasswordText() {
        return text(forgotPasswordButton);
    }

    public String registrationText() {
        return text(registrationButton);
    }

    public boolean isForgotPasswordEnabled() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordButton)).isEnabled();
    }

    public boolean isRegistrationEnabled() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registrationButton)).isEnabled();
    }

    public void enterUsername(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(value);
    }

    public void enterPassword(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(value);
    }

    public String loginButtonText() {
        return text(loginButtonText);
    }

    public String usernameErrorText() {
        return text(usernameError);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonText)).click();
    }

    public String passwordErrorText() {
        return text(passwordError);
    }

    public boolean isUsernameErrorDisplayed() {
        return isDisplayed(usernameError);
    }

    public boolean isPasswordErrorDisplayed() {
        return isDisplayed(passwordError);
    }

    private String text(By locator) {
        return normalize(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText());
    }

    public String normalize(String value) {
        return value
                .replace('ё', 'е')
                .replace('Ё', 'Е')
                .replaceAll("\\s+", " ")
                .trim();
    }

    private boolean isDisplayed(By locator) {
        return driver.findElements(locator).stream()
                .anyMatch(element -> element.isDisplayed());
    }

    private By resourceId(String id) {
        return AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + id + "\")");
    }
}
