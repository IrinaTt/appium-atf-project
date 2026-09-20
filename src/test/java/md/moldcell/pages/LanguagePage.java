package md.moldcell.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.text.Normalizer;

public class LanguagePage extends BasePage {
    private final By screen = AppiumBy.id("md.moldcell.selfservice:id/activity_welcome");
    private final By title = AppiumBy.id("md.moldcell.selfservice:id/txt_title");
    private final By continueButton = AppiumBy.id("md.moldcell.selfservice:id/btn_change_language");

    public LanguagePage(AndroidDriver driver) { super(driver, Duration.ofSeconds(25)); }
    public boolean isDisplayed() { return wait.until(ExpectedConditions.visibilityOfElementLocated(screen)).isDisplayed(); }
    public String getTextMessage() {
        return normalizeText(wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText());
    }

    public String normalizeText(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("[\u2018\u2019\u201B\u2032\u02BC\u0060\u00B4]", "'")
                .replaceAll("[\\s\\u00A0]+", " ")
                .trim();
    }
    public String getContinueButtonText() {
        return normalizeText(wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton)).getText());
    }
    public void selectLanguage(String language) {
        String languageCode = switch (language) {
            case "Română" -> "ro";
            case "Русский" -> "ru";
            case "English" -> "en";
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
        By languageOption = AppiumBy.androidUIAutomator(
                "new UiSelector().resourceId(\"md.moldcell.selfservice:id/radio_btn_"
                        + languageCode + "\")");
        wait.until(ExpectedConditions.elementToBeClickable(languageOption)).click();
    }
    public void continueToLogin() { wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click(); }
}
