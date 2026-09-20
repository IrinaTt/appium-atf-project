package md.moldcell.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AuthMethodPage extends BasePage {
    private final By laterButton = resourceId("auth_method_later_button");

    public AuthMethodPage(AndroidDriver driver) {
        super(driver, Duration.ofSeconds(20));
    }

    public String laterButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(laterButton)).getText();
    }

    public void clickLaterButton(String expectedText) {
        By button = AppiumBy.androidUIAutomator(
                "new UiSelector().resourceId(\"auth_method_later_button\").text(\""
                        + expectedText.replace("\"", "\\\"") + "\")");
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    private By resourceId(String id) {
        return AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + id + "\")");
    }
}
