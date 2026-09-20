package md.moldcell.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ErrorPopup extends BasePage {
    private final By title = AppiumBy.id("md.moldcell.selfservice:id/text_view_title");
    private final By closeButton = AppiumBy.id("md.moldcell.selfservice:id/text_view_cancel");

    public ErrorPopup(AndroidDriver driver) {
        super(driver, Duration.ofSeconds(20));
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).isDisplayed();
    }

    public String closeButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(closeButton)).getText();
    }

    public void clickClose(String expectedText) {
        By button = AppiumBy.androidUIAutomator(
                "new UiSelector().resourceId(\"md.moldcell.selfservice:id/text_view_cancel\").text(\""
                        + expectedText.replace("\"", "\\\"") + "\")");
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }
}
