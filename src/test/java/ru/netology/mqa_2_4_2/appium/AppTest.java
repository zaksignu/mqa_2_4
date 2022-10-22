package ru.netology.mqa_2_4_2.appium;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AppTest {

    private AppiumDriver driver;

    private static final String testText = "123";
    private static final String expectedText = "Hello UiAutomator!";


    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "psst");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", ".MainActivity");

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        AppPage appPage = new AppPage(driver);
        Assertions.assertTrue(appPage.textField.isDisplayed());
        Assertions.assertTrue(appPage.textToBeChange.isDisplayed());
        String expected = appPage.textToBeChange.getText();
        appPage.textField.click();
        appPage.textField.clear();
        Assertions.assertTrue(appPage.changeItButton.isDisplayed());
        appPage.changeItButton.click();
        String actual = appPage.textToBeChange.getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void sampleTest2() throws InterruptedException {
        AppPage appPage = new AppPage(driver);
        Assertions.assertTrue(appPage.textField.isDisplayed());
        appPage.textField.click();
        appPage.textField.sendKeys(testText);
        Assertions.assertTrue(appPage.newActivityButton.isDisplayed());
        appPage.newActivityButton.click();
        Assertions.assertTrue(appPage.textInNewActivity.isDisplayed());
        Assertions.assertEquals(testText, appPage.textInNewActivity.getText());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
