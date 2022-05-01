package com.arrnel.project.sbermegamarket.pages.items;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ItemsPageLocators {

    SelenideElement pageTitle() {
        return $("h1[class^=catalog]").as("Заголовок страницы товаров");
    }

    public ElementsCollection itemsCollection() {
        return $$("div[itemprop='itemListElement']").as("Коллекция карточeк товаров");
    }

    public SelenideElement searchMask() {
        return $("div[class='r loading']");
    }

    public SelenideElement bannerExpressDelivery() {
        return $x("div[@class='v-step onboarding__step' and .//h3[text()='Появилась экспресс-доставка']]").as("Баннер экспресс-доставки");
    }

    public SelenideElement buttonCloseExpressDelivery() {
        return $x("//button[text()='Хорошо')]").as("Кнопка 'Хорошо' баннера экспресс-доставки");
    }

}
