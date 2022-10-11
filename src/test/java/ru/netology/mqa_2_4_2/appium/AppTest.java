package ru.netology.mqa_2_4_2.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
//import junit.framework.TestCase;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AppTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "psst");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", ".MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el3.sendKeys(" ");
        MobileElement el4 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        String actual = el4.getText();
        String expected = "Hello UiAutomator!";
        Assertions.assertEquals(expected,actual );
    }

    @Test
    public void sampleTest2() throws InterruptedException {
        String textToSet = "123";
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        el1.sendKeys(textToSet);
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.TextView");
        Assertions.assertTrue(el3.isDisplayed());
        MobileElement el4 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        String actual = el4.getText();
        Assertions.assertEquals(textToSet,actual );
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
