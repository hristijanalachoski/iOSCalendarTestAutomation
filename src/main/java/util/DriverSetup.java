package util;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import io.qameta.allure.Step;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import pages.*;

@Listeners({ITestListenerUtility.class})
public class DriverSetup extends ConfigReader {

    protected static IOSDriver driver;
    protected AppiumServerManager appiumServerManager = new AppiumServerManager();

    protected Helpers helpers;

    protected CalendarHomePage calendarHomePage;
    protected NewEventPage newEventPage;

    @Step("Appium server is started")
    @BeforeSuite
    public void startAppiumDriver() {
        appiumServerManager.startAppiumServer();
    }

    @Step("Driver is started")
    @BeforeMethod
    public void setUp() {

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(getProperty("device.name"))
                .setPlatformVersion(getProperty("platform.version"))
                .setBundleId(getProperty("bundle.id"))
                .setNoReset(false)
                .setAutoAcceptAlerts(true);

        try {
            driver = new IOSDriver(new URI(GlobalVariables.appiumLocalUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        helpers = new Helpers();

        calendarHomePage = new CalendarHomePage(driver);
        newEventPage = new NewEventPage(driver);
    }

    @Step("Driver is closed")
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Step("Appium server is stopped")
    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        appiumServerManager.stopAppiumServer();
    }
}
