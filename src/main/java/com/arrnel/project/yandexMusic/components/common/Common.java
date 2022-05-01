package com.arrnel.project.yandexMusic.components.common;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class Common {

    CommonLocators locator = new CommonLocators();

    public Common checkLogo() {

        locator.yandexLogo().shouldBe(Condition.visible).shouldBe(Condition.visible);
        locator.yandexMusicLogo().shouldBe(Condition.visible).shouldBe(Condition.visible);

        return this;
    }

}
