package com.arrnel.project.sbermegamarket.components.filter;

import com.arrnel.project.sbermegamarket.components.common.Common;
import com.arrnel.setup.AllureActions;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagName;
import static io.qameta.allure.Allure.step;

public class Filter {

    FilterLocators locator = new FilterLocators();
    Common common = new Common();
    AllureActions allure = new AllureActions();

    /**
     * Развернуть фильтр
     *
     * @param filterName
     * @return
     */
    public Filter setExpandedFilter(String filterName) {

        if (locator.filter(filterName).$(byTagName("div")).as("Элемент со статусом сворачивания фильтра \"" + filterName + "\"").has(not(cssClass("expanded")))) {
            locator.filter(filterName).$(byTagName("svg")).as("Кнопка сворачивания элемента").shouldBe(visible).click();
        }

        return this;
    }

    /**
     * Развернуть фильтр
     *
     * @param filterName
     * @return
     */
    public Filter expandFilter(String filterName) {
        if (locator.filterTrigger(filterName).exists()) {
            locator.filterTrigger(filterName).shouldBe(visible).click();
        }
        return this;
    }


    /***
     * Выбор значения в фильтре товаров
     * @param filterName Наименование фильтра
     * @param filterValue Чекбокс фильтра
     * @return
     */
    public Filter selectFilterCheckboxByValue(String filterName, String filterValue) {

        locator.filter(filterName).$x(".//span[text()='" + filterValue + "']/ancestor::label/span")
                .as(String.format("Чекбокс \"%s\" фильтра \"%s\"", filterValue, filterName)).shouldBe(and("Clickable", visible, enabled)).scrollIntoView(false).click();

        common.waitLoadingMaskNotExist();
        return this;
    }


    /***
     * Ввод в текста в фильтр товаров
     * @param filterName Наименование фильтра
     * @param filterValue Чекбокс фильтра
     * @return
     */
    public Filter searchFilterValue(String filterName, String filterValue){

        locator.filter(filterName).scrollIntoView(false);

        if (locator.filterValuesCollection(filterName).size() > 9) {

//            locator.phoneNumber().shouldBe(visible).scrollIntoView(false);
//            locator.filterClear(filterName).shouldBe(visible).scrollIntoView(false).click();
//
//            expandFilter(filterName);
//            expandFilter(filterName);

            for(int i = 1; i <= 5; i++){
                if(!locator.filterSearchInput(filterName).isDisplayed()){
                    step("Попытка ввода текста в фильтр: " + i, () -> {
                        locator.filterSearchInput(filterName).click();
                        locator.filterSearchInput(filterName).clear();
                        locator.filterSearchInput(filterName).sendKeys(filterValue);
                    });
                }
                else break;
            }
        }

        allure.allureAttachScreenshotAndPage(
                String.format("Скриншот поиска в фильтре '%s' и значения %s", filterName, filterValue),
                String.format("Страница поиска в фильтре '%s' и значения '%s'", filterName, filterValue)
        );

        return this;
    }


}
