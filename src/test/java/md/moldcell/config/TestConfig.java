package md.moldcell.config;

import java.net.MalformedURLException;
import java.net.URL;

public final class TestConfig {
    private TestConfig() { }

    public static String get(String name, String defaultValue) {
        String property = System.getProperty(name);
        if (property != null && !property.isBlank()) return property;
        String environment = System.getenv(name);
        return environment == null || environment.isBlank() ? defaultValue : environment;
    }

    public static String required(String name) {
        String value = get(name, "");
        if (value.isBlank()) {
            throw new IllegalStateException(
                    "Required secret is not configured: " + name
                            + ". Set it as an environment variable or JVM property.");
        }
        return value;
    }

    public static String resolveSecret(String value) {
        if (value == null || !value.startsWith("${") || !value.endsWith("}")) {
            return value;
        }
        return required(value.substring(2, value.length() - 1));
    }

    public static URL appiumServerUrl() {
        try {
            return new URL(get("APPIUM_SERVER_URL", "http://127.0.0.1:4723"));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid APPIUM_SERVER_URL", e);
        }
    }

    public static String deviceName() { return get("ANDROID_DEVICE_NAME", "Pixel 7"); }
    public static String udid() { return get("ANDROID_UDID", "emulator-5554"); }
    public static String platformVersion() { return get("ANDROID_PLATFORM_VERSION", "13"); }
    public static String appPackage() { return get("MY_MOLDCELL_PACKAGE", "md.moldcell.selfservice"); }
    public static String appActivity() { return get("MY_MOLDCELL_ACTIVITY", ".screens.splash.SplashActivity"); }
}
