package com.arrnel.project.sbermegamarket.components.filter;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FilterLocators {

    public SelenideElement filter(String filterName){
        return $x("//div[contains(@class,'filter-element-wrapper') and .//div[text()='" + filterName + "']]").as("Фильтр '" + filterName + "'");
    }

    public SelenideElement filterTrigger(String filterName){
        return filter(filterName).$x(".//div[@class='filter-trigger']").as("Триггер фильтра '" + filterName + "'");
    }

    public SelenideElement filterSearchInput(String filterName){
        return filter(filterName).$x(".//input[contains(@class,'input')]").as("Поисковая строка фильтра '" + filterName + "'");
    }

    public ElementsCollection filterValuesCollection(String filterName){
       return filter(filterName).$$("div[class='virtual-scroll__item']").as("Коллекция значений фильтра '" + filterName + "'");
    }

    public SelenideElement filterClear(String filterName) {
        return filter(filterName).$("button[class='filter-search__clear']").as("Очистить фильтр '" + filterName + "'");
    }

    public SelenideElement phoneNumber() {
        return $(byText("Есть вопросы? Звоните:")).as("Футер с текстом номера телефона");
    }

    public SelenideElement loadingAnimation() {
        return $("spinner-block").as("Анимация загрузки");
    }
}
