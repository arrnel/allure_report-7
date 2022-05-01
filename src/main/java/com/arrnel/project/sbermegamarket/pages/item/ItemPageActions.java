package com.arrnel.project.sbermegamarket.pages.item;

import io.qameta.allure.Allure;
import org.openqa.selenium.ElementClickInterceptedException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

public class ItemPageActions {

    ItemPageLocators locator = new ItemPageLocators();

    public ItemPageActions assertItemCharacteristic(String characteristicName, String characteristicValue) {

        try {
            clickButtonAllCharacteristics();
        } catch (Exception ex) {
            closeBonusBanner().clickButtonAllCharacteristics();
        }

        assertCharacteristic(characteristicName, characteristicValue);

        return this;

    }

    ItemPageActions assertCharacteristic(String characteristicName, String characteristicValue) {
        step(String.format("Проверяем отображение характеристики '%s' и её значения '%s'", characteristicName, characteristicValue), () -> {
            locator.characteristicValue(characteristicName).shouldHave(text(characteristicValue));
        });
        return this;
    }

    ItemPageActions clickButtonAllCharacteristics() {
        step("Нажимаем на кнопку 'Все характеристики'", () -> {
            locator.allCharacteristicsLink().shouldBe(visible).scrollIntoView(false).click();
        });
        return this;
    }

    ItemPageActions closeBonusBanner() {
        step("Закрываем баннер с информацией о бонусах", () -> {
            locator.helperBannerCloseButton().shouldBe(visible).click();
        });
        return this;
    }


}
