package com.arrnel.project.yandexMusic.page.suggestSubscription;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SuggestSubscriptionPageLocators {

    public SelenideElement buttonCloseSuggestSubscriptionForm() {
        return $(".js-close").as("Кнопка формы предложения оформления подписки");
    }

}
