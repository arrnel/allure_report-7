package com.arrnel.project.sbermegamarket.pages.item;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class ItemPageActions {

    ItemPageLocators locator = new ItemPageLocators();

    public ItemPageActions assertItemCharacteristic(String characteristicName, String characteristicValue) {

        clickCharacteristics();
        assertCharacteristic(characteristicName, characteristicValue);

        return this;

    }

    ItemPageActions assertCharacteristic(String characteristicName, String characteristicValue) {

        step(String.format("Проверяем отображение характеристики '%s' и её значения '%s'", characteristicName, characteristicValue), () -> {
            locator.characteristicValue(characteristicName).shouldHave(text(characteristicValue));
        });

        return this;
    }

    ItemPageActions clickCharacteristics() {

        step("Нажимаем на кнопку 'Все характеристики'", () -> {
            locator.allCharacteristicsLink().shouldBe(visible).scrollIntoView(false).click();
        });

        return this;

    }


}
