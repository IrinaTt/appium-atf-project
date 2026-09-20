package md.moldcell.enums;

import io.appium.java_client.android.AndroidDriver;
import md.moldcell.pages.LanguagePage;
import md.moldcell.pages.LoginPage;

public enum PageName {
    LANGUAGE("language") {
        @Override
        public boolean isDisplayed(AndroidDriver driver) {
            return new LanguagePage(driver).isDisplayed();
        }
    },
    LOGIN("login") {
        @Override
        public boolean isDisplayed(AndroidDriver driver) {
            return new LoginPage(driver).isDisplayed();
        }
    };

    private final String value;

    PageName(String value) {
        this.value = value;
    }

    public abstract boolean isDisplayed(AndroidDriver driver);

    public static PageName fromValue(String value) {
        String normalizedValue = value.trim().toLowerCase();
        for (PageName page : values()) {
            if (page.value.equals(normalizedValue)) {
                return page;
            }
        }
        throw new IllegalArgumentException("Unsupported page: " + value);
    }
}
