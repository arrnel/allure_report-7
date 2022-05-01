package com.arrnel.project.sbermegamarket.pages.main;

import com.arrnel.project.sbermegamarket.components.common.Common;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class MainPageActions {

    MainPageLocators locator = new MainPageLocators();
    Common common = new Common();

    public MainPageActions searchItem(String itemName) {

        step(String.format("Ввод текста %s в строку поиска", itemName), () -> {
            locator.searchInput().shouldBe(visible).setValue(itemName);
        });

        step("Нажать на кнопку \"Найти\"", () -> {
            locator.searchButton().shouldBe(visible).click();
        });

        return this;

    }

    public MainPageActions closeBanner() {
        try {
            locator.siteLogo().shouldBe(visible);
            locator.closeBanner().shouldBe(visible).click();
        }catch(ElementNotFound ex){}

        return this;

    }


}
