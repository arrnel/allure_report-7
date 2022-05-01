package com.arrnel.project.sbermegamarket.pages.items;

import com.arrnel.project.sbermegamarket.components.filter.Filter;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class ItemsPageActions {

    ItemsPageLocators locator = new ItemsPageLocators();

    /**
     * Проверка на соответствие наименования страницы ожидаемому
     *
     * @param headerText Наименование страницы
     * @return
     */
    public ItemsPageActions assertPageTitle(String headerText) {

        locator.pageTitle().shouldBe(visible).scrollIntoView(false).shouldHave(text(headerText));

        return this;

    }

    public ItemsPageActions searchAndSelectFilterValue(String filterName, String filterValue) {

        Filter filter = new Filter();

        step(String.format("Развернуть фильтр '%s'", filterName), () -> {
            filter.setExpandedFilter(filterName);
        });

        step(String.format("Ввести значение '%s' в поле ввода фильтра '%s'", filterValue, filterName), () -> {
            filter.searchFilterValue(filterName, filterValue);
        });

        step(String.format("Выбрать чекбокс '%s' среди найденных в фильтре '%s'", filterValue, filterName), () -> {
            filter.selectFilterCheckboxByValue(filterName, filterValue);
        });

        return this;

    }

    /**
     * Возвращает карточку товара
     *
     * @param i номер карточки товара (начинается с 0)
     * @return
     */
    public ItemsPageActions goToNthItem(int i) {

        SelenideElement item = locator.itemsCollection().get(i);

        step("Нажать на наименование (" + i + " -ого) товара, среди отфильтрованных", () -> {
            item.$("a[itemprop=url]").as("Ссылка с наименованием товара").shouldBe(visible).scrollIntoView(false).click();
        });

        return this;

    }

    public ItemsPageActions closeBannerExpressDelivery() {

        try {
            locator.bannerExpressDelivery().shouldBe(visible, Duration.ofSeconds(3));
            locator.buttonCloseExpressDelivery().shouldBe(visible).click();
        }catch (ElementNotFound ex){}

        return this;

    }

}
