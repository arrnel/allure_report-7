package com.arrnel.project.sbermegamarket.pages.item;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ItemPageLocators {

    public SelenideElement allCharacteristicsLink() {
        return $x("//div[@class='pdp-tab-selector tab-links-component']//a[@class ='tab-selector-link' and text()='Характеристики']").as("Верхняя вкладка характеристик товара");
    }

    public SelenideElement characteristicValue(String characteristicName){
        return $x("//div[text()='" + characteristicName + " ']/following-sibling::div[text()]").as("Значение характеристики '" + characteristicName + "'");
    }

}
