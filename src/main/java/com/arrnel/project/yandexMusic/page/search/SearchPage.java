package com.arrnel.project.yandexMusic.page.search;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {

    SearchPageLocators locator = new SearchPageLocators();


    @Step("Поиск по фразе '{text}'")
    public SearchPage search(String text) {

        locator.buttonSearch().shouldBe(Condition.visible).click();
        locator.inputSearch().shouldBe(Condition.visible).setValue(text);

        return this;

    }

    @Step("Проверка наличия '{text}' в категории '{category}'")
    public SearchPage assertSearch(String text, String category) {
        locator.searchCategoryCollection(category).get(0).as("Первый элемент в категории " + category + " формы поиска").shouldHave(Condition.text(text));
        return this;
    }

}
