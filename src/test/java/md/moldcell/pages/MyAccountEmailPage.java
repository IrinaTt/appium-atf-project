package md.moldcell.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MyAccountEmailPage extends BasePage {

    public MyAccountEmailPage(AndroidDriver driver) {
        super(driver, Duration.ofSeconds(20));
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("md.moldcell.selfservice:id/main_container"))).isDisplayed();
    }
}
