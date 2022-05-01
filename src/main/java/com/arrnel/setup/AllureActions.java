package com.arrnel.setup;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

public class AllureActions {

    public void attachScreenshotAndPage(String screenshotDescription, String pageDescription) {
        attachScreenshot(screenshotDescription);
        attachPage(pageDescription);
    }

    @Attachment(value="{screenshotName}", type="image/png", fileExtension = "png")
    public byte[] attachScreenshot(String screenshotName){
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value="{pageName}", type="text/html", fileExtension = "html")
    public byte[] attachPage(String pageName){
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }


    public void allureAttachScreenshotAndPage(String screenshotDescription, String pageDescription){
        allureAttachScreenshot(screenshotDescription);
        allureAttachPage(pageDescription);
    }

    public void allureAttachPage(String pageDescription) {
        Allure.getLifecycle().addAttachment(
                pageDescription,
                "text/html",
                "html",
                WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8));
    }

    public void allureAttachScreenshot(String screenshotDescription) {
        Allure.getLifecycle().addAttachment(
                screenshotDescription,
                "image/png",
                "png",
                ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES));
    }

}
