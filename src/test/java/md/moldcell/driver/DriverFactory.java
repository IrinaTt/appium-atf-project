package md.moldcell.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import md.moldcell.config.TestConfig;

import java.time.Duration;

public final class DriverFactory {
    private DriverFactory() { }

    public static AndroidDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(TestConfig.deviceName())
                .setUdid(TestConfig.udid())
                .setPlatformVersion(TestConfig.platformVersion())
                .setAppPackage(TestConfig.appPackage())
                .setAppActivity(TestConfig.appActivity())
                .setNoReset(false)
                .setNewCommandTimeout(Duration.ofSeconds(120));
        return new AndroidDriver(TestConfig.appiumServerUrl(), options);
    }
}
