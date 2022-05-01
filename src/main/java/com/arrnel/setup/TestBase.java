package com.arrnel.setup;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TestBase {
    enum TestRunningMode {
        CHROME_LOCAL, CHROME_LOCAL_DOCKER
    }

    static TestRunningMode testRunningMode = TestRunningMode.CHROME_LOCAL;
//    static TestRunningMode testRunningMode = TestRunningMode.CHROME_LOCAL_DOCKER;

    @BeforeAll
    public static void setup() {

        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;
        Configuration.pageLoadTimeout = 10000;

        if (testRunningMode == TestRunningMode.CHROME_LOCAL){
            localChromeWebDriver();
        }else if(testRunningMode == TestRunningMode.CHROME_LOCAL_DOCKER){
            localDockerWebDriver();
        } else throw new IllegalArgumentException("Некорректный тип окружения");

    }

    static void localChromeWebDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();

        Configuration.browser = "chrome";
        Configuration.browserVersion = "101.0";

//        Configuration.headless = true;
        System.setProperty("chromeoptions.args", "--disable-notifications"); //Отключает уведомления в chrome
//        options.addArguments("--disable-gpu");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

    }

    static void localDockerWebDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "99.0";
//        Configuration.headless = true;

        System.setProperty("chromeoptions.args", "--disable-notifications"); //Отключает уведомления в chrome
//        options.addArguments("--disable-gpu");

        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("enableLog", true);
        hashmap.put("enableVNC", true);
        hashmap.put("enableVideo", true);
        hashmap.put("videoCodec", "mpeg4");

        capabilities.setVersion("chromium_tc");
        capabilities.setCapability("selenoid:options", hashmap);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

    }

    protected static Browser browser() {
        return new Browser(Configuration.browser, Configuration.headless);
    }

}
