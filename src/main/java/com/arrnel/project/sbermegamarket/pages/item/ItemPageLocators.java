package com.arrnel.project.sbermegamarket.pages.item;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ItemPageLocators {

    public SelenideElement allCharacteristicsLink() {
        return $(byText("Все характеристики")).as("Ссылка 'Все характеристики'");
    }

    public SelenideElement characteristicValue(String characteristicName){
        return $x("//div[text()='" + characteristicName + " ']/following-sibling::div[text()]").as("Значение характеристики '" + characteristicName + "'");
    }

    public SelenideElement helperBannerCloseButton(){
//        return $x("//div[@class='onboarding__header']").as("Кнопка закрытия баннера бонусов для новичка");
        return $("svg[class=onboarding__close-icon] > use").as("Кнопка закрытия баннера бонусов для новичка");
    }

}
