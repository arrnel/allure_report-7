package com.arrnel.project.yandexMusic.page.search;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class SearchPageLocators {

    public SelenideElement buttonSearch() {
        return $(".d-search__button").as("Кнопка поиска");
    }

    public SelenideElement inputSearch() {
        return $(".deco-input").as("Поле поиска");
    }

    public ElementsCollection searchCategoryCollection(String category) {
        return $$x("//div[contains(@class,'d-suggest-items__title') and text()='" + category + "']/following-sibling::div").as("Коллекция в категории '" + category + "' на форме поиска");
    }

}
