package com.arrnel.project.sbermegamarket.pages.main;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPageLocators {

    public SelenideElement searchButton() {
        return $("button[type=submit][class^=header]").as("Кнопка \"Найти\"");
    }

    public SelenideElement searchInput() {
        return $("input[type=search]").as("Строка поиска товаров");
    }

    public SelenideElement siteLogo() {
        return $("div[class='header-logo desktop-only']").as("Логотип");
    }

    public SelenideElement closeBanner() {
        return $("button[class=close-button]").as("Кнопка \"Закрыть баннер\" местоположения");
    }




}
