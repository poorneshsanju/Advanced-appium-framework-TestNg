package com.automation.TestBase;

import com.automation.ServerManager.AppiumServerManager;
import com.automation.capabilities.CapabilitiesManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TestBase {
    public static AppiumDriver driver;
//    public ServerManager serverManager = new ServerManager();
//    public CapabilitiesManager capabilitiesManager = new CapabilitiesManager();
//
//    AdbManager adbManager = new AdbManager();
//    public AppiumDriverLocalService service;
//    DesiredCapabilities capabilities = new DesiredCapabilities();
//
//    public PropertyFileHandler deviceProps = new PropertyFileHandler(FilePathUtils.DEVICE_CONFIG_PATH);

//    @BeforeSuite(alwaysRun = true)
//    public void readExcel() throws IOException {
//        try {
//            new ExcelToJson();
//        } catch (NullPointerException exception) {
//            System.out.println("Some columns in excel sheet might be empty");
//            Log.error("Null pointer exception while reading excel sheet");
//            throw new RuntimeException("Null pointer exception while reading excel sheet");
//        }
//    }

    @BeforeMethod
    protected void beforeSuite() {
        AppiumServerManager.startServer();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        CapabilitiesManager capabilitiesManager = new CapabilitiesManager();
        DesiredCapabilities capabilities = capabilitiesManager.getCapabilities("Android");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

    }

    @AfterTest()
    public void closeServer() {
        AppiumServerManager.stopServer();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownClass() {
        AppiumServerManager.stopServer();
    }
//
//    @BeforeTest(alwaysRun = true)
//    public void startTests() {
//        System.out.println("Test Case is starting");
//    }
//
//    public void setMethodName(String methodName) {
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"" + methodName + "\" }}");
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void printTestStatus(ITestResult result) {
//        if (deviceProps.getProperty("Mode").equals("RUN_ON_BROWSERSTACK")) {
//            if (result.getStatus() == ITestResult.SUCCESS) {
//                JavascriptExecutor jse = (JavascriptExecutor) driver;
//                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Script successfull!\"}}");
//            } else if (result.getStatus() == ITestResult.FAILURE) {
//                JavascriptExecutor jse = (JavascriptExecutor) driver;
//                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Test Script failed!\"}}");
//            } else if (result.getStatus() == ITestResult.SKIP) {
//                JavascriptExecutor jse = (JavascriptExecutor) driver;
//                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"error\", \"reason\": \"Test Script Skipped!\"}}");
//            }
//        }
//        driver.quit();
//    }
}