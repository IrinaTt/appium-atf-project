package md.moldcell.hooks;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import md.moldcell.driver.DriverSession;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class CucumberHooks {
    @Before
    public void setUp() {
        DriverSession.start();
        dismissNotificationPermissionIfPresent();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.attach(DriverSession.driver().getScreenshotAs(OutputType.BYTES), "image/png", "Failure screenshot");
            scenario.attach(DriverSession.driver().getPageSource().getBytes(StandardCharsets.UTF_8), "application/xml", "Failure page source");
        }
        DriverSession.stop();
    }

    private void dismissNotificationPermissionIfPresent() {
        try {
            new WebDriverWait(DriverSession.driver(), Duration.ofSeconds(5)).until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"Don’t allow\")"))).click();
        } catch (Exception firstAttempt) {
            try {
                new WebDriverWait(DriverSession.driver(), Duration.ofSeconds(2)).until(
                        ExpectedConditions.elementToBeClickable(
                                AppiumBy.androidUIAutomator("new UiSelector().text(\"Don't allow\")"))).click();
            } catch (Exception ignored) { }
        }
    }
}
