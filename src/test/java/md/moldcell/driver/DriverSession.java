package md.moldcell.driver;

import io.appium.java_client.android.AndroidDriver;

public final class DriverSession {
    private static AndroidDriver driver;
    private DriverSession() { }
    public static void start() { driver = DriverFactory.createDriver(); }
    public static AndroidDriver driver() {
        if (driver == null) throw new IllegalStateException("Appium driver has not been started");
        return driver;
    }
    public static void stop() { if (driver != null) { driver.quit(); driver = null; } }
}
