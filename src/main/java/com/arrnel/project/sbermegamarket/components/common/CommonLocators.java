package com.arrnel.project.sbermegamarket.components.common;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CommonLocators {
    public SelenideElement searchMask() {
        return $("div[class='r loading']");
    }
}
