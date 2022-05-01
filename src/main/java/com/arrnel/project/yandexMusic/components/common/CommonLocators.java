package com.arrnel.project.yandexMusic.components.common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CommonLocators {

    public SelenideElement logosWrapper() {
        return $("span[class='d-logo d-logo__ru']").as("Обертка логотипов");
    }
    public SelenideElement yandexLogo() {
        return logosWrapper().$("d-logo__ya").as("Логотип Яндекс");
    }
    public SelenideElement yandexMusicLogo() {
        return logosWrapper().$("d-logo__ya-sub").as("Логотип Музыка");
    }
}
