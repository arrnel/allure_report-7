package com.arrnel.project.yandexMusic.page.suggestSubscription;

import com.arrnel.project.yandexMusic.components.common.Common;
import com.arrnel.setup.AllureActions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class SuggestSubscriptionPage {

    Common common = new Common();
    SuggestSubscriptionPageLocators locator = new SuggestSubscriptionPageLocators();
    AllureActions allure = new AllureActions();

    public SuggestSubscriptionPage checkLogo() {
        common.checkLogo();
        return this;
    }
    
    @Step("Закрыть форму предложения оформления подписки")
    public void close() {
        allure.attachScreenshotAndPage("Скриншот страницы предложения подписки", "Страница предложения подписки");

        locator.buttonCloseSuggestSubscriptionForm().shouldBe(Condition.visible).click();

        allure.attachScreenshot("Скриншот главной страницы");
        allure.attachPage("Главная страница");
    }

    public void attachScreenshotAndPage(String screenshotDescription, String pageDescription) {
        allure.attachScreenshot("Скриншот страницы предложения подписки");
        allure.attachPage("Страница предложения подписки");
    }


}
