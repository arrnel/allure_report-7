package com.arrnel.mainLogic.allure;

import com.arrnel.project.sbermegamarket.pages.item.ItemPageActions;
import com.arrnel.project.sbermegamarket.pages.items.ItemsPageActions;
import com.arrnel.project.sbermegamarket.pages.main.MainPageActions;
import com.arrnel.project.yandexMusic.page.search.SearchPage;
import com.arrnel.project.yandexMusic.page.suggestSubscription.SuggestSubscriptionPage;
import com.arrnel.setup.AllureActions;
import com.arrnel.setup.TestBase;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.TimeoutException;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.WebDriverException;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Owner("@arrnel")
@Slf4j
@DisplayName("Параметризованные тесты + Allure")
class AllureParameterizedTests extends TestBase {

    AllureActions allure = new AllureActions();

    @Story("Allure lambdas + CsvSource")
    @DisplayName("Allure lambda steps + CsvSource + SberMegaMarketSearchTest")
    @Description("Тестирование СберМегаМаркет<br/>" +
            "Аллюр степы через лямбды;<br/>" +
            "Приложения через метод allure.getLifeCycle<br/>" +
            "Параметризованный тест с провайдером CsvSource<br/><br/>" +
            "<h1>Нужна хелпа по данному пункту как сделать без слипа!?</h1><br>" +
            "*Тест вводит текст быстрее чем прогружаются скрипты, от этого не находится значение в фильтре. Много что перепробовал, не стал добавлять слип."
    )
    @CsvSource(
            {
                    "Сироп,            Сиропы,            Вкус,                    миндаль", //<--- Система ведет по разному Открывая в одном хроме есть стиль list у строки ввода в фильтр в другом нет. Хз как победить. Редко когда удаётся ввести значение в фильтр
                    "Пылесос,          Пылесосы,          Тип уборки,              паровая",
                    "Nvidia RTX 3070,  видеокарты,        Тип видеопамяти,         GDDR6X", // Попадаются в поиске и GDDR6
                    "Мангал,           Мангалы,           Материал товара,         дерево"
            }
    )
    @ParameterizedTest(name = "Поиск товара {0}, c отображением текста в заголовке {1} и применением фильтра {2} c значением {3}")
    @Flaky
    void test001(String itemName, String headerText, String filterName, String filterValue) {

        MainPageActions mainPage = new MainPageActions();
        ItemsPageActions itemsPage = new ItemsPageActions();
        ItemPageActions itemPage = new ItemPageActions();

        String url = "https://sbermegamarket.ru";

        //Test Steps
        step("Открываем главную страницу СберМегаМаркет", () -> {

            openPage(url);

            step("Закрываем баннер 'Ваш регион'", () -> {
                mainPage.closeBanner();
            });

            allure.allureAttachScreenshotAndPage(
                    "Скриншот главной страницы СберМегаМаркет",
                    "Главная страница СберМегаМаркет"
            );

        });


        step(String.format("Выполнение поиска товара по наименованию '%s'", itemName), () -> {

            mainPage.searchItem(itemName);

            step("Проверка наименования страницы товаров ожидаемому", () -> {
                itemsPage.assertPageTitle(headerText);
            });

            step("Закрыть баннер экспресс-доставки", () -> {
                itemsPage.closeBannerExpressDelivery();
            });

            allure.allureAttachScreenshotAndPage(
                    "Скриншот страницы поиска товаров",
                    "Страница поиска товаров"
            );

        });


        step(String.format("Фильтрация товара по фильтру '%s', и значению '%s'", filterName, filterValue), () -> {

            itemsPage.searchAndSelectFilterValue(filterName, filterValue);

            allure.allureAttachScreenshotAndPage(
                    String.format("Скриншот применения фильтра '%s', и значения '%s'", filterName, filterValue),
                    String.format("Страница с применённым фильтром '%s', и значения '%s'", filterName, filterValue)
            );

        });

        step("Переход на страницу первого товара из найденных", () -> {

            itemsPage.goToNthItem(1);

            allure.allureAttachScreenshotAndPage(
                    "Скриншот страницы товара",
                    "Страница товара"
            );

        });

        //TestAssertion
        step("Проверка наличия характеристики товара", () -> {

            itemPage.assertItemCharacteristic(filterName, filterValue);

            allure.allureAttachScreenshot(String.format("Скриншот наличия у товара характеристики '%s', и значения '%s'", filterName, filterValue));

        });

    }

    @Story("Allure Method Steps + MethodSource + SelenideListener")
    @DisplayName("Поиск по фразе в Yandex.Music")
    @ParameterizedTest(name = "Поиск по фразе {0} в yandex music в категории {1}")
    @MethodSource("methodSource")
    @Description("Тестирование наличия ожидаемого текста в поисковой форме Яндекс.Музыка<br/>" +
            "Аллюр степы через аннотации;<br/>" +
            "Приложения через аннотации<br/>" +
            "Параметризованный тест с провайдером MethodSource<br/><br/>"
    )
    void test002(String text, String category) {

        SelenideLogger.addListener("allure", new AllureSelenide());
        SuggestSubscriptionPage suggestSubscriptionPage = new SuggestSubscriptionPage();
        SearchPage searchPage = new SearchPage();
        String url = "https://music.yandex.ru";

        //Test Steps
        openPage(url);
        suggestSubscriptionPage.close();
        searchPage.search(text);

        //Test Assertion
        searchPage.assertSearch(text, category);

        allure.attachScreenshotAndPage("Форма поиска", "Форма поиска");
    }

    Stream<Arguments> methodSource() {

        return Stream.of(
                Arguments.of("Numb", "Треки"),
                Arguments.of("Люся Чеботина", "Исполнители")
        );

    }

    @Story("ValueSource")
    @ValueSource(strings = {
            "www.google.com",
            "www.rambler.ru"}
    )
    @DisplayName("Тесты ленивой жопки 1")
    @Description("Параметризованный тест с провайдером ValueSource<br/>")
    @ParameterizedTest(name = "Проверка ссылки страницы, {0}")
    void test003(String siteDomain) {

        String url = "https://" + siteDomain;
        openPage(url);
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals(String.format("https://%s/", siteDomain), actualUrl);
        allure.attachScreenshot("Скриншот страницы '" + actualUrl + "'");

    }

    @Story("EnumSource")
    @EnumSource(Site.class)
    @Description("Параметризованный тест с провайдером EnumSource<br/>")
    @DisplayName("Тесты ленивой жопки 2")
    @ParameterizedTest(name = "Проверка ссылки страницы, {0}")
    void test004(Site site) {

        String expectedUrl = getSitesURL(site);
        openPage(expectedUrl);
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
        allure.attachScreenshot("Скриншот страницы '" + actualUrl + "'");

    }

    enum Site {
        GOOGLE, RAMBLER;
    }

    String getSitesURL(Site site) {

        switch (site) {
            case GOOGLE:
                return "https://www.google.com/";
            case RAMBLER:
                return "https://www.rambler.ru/";
            default:
                return null;
        }

    }

    @Step("Открываем страницу '{0}'")
    void openPage(String url) {

        try {
            open(url);
        } catch (TimeoutException ex) {
            clearBrowserCookies();
            clearBrowserLocalStorage();
            open(url);
        } catch (WebDriverException ex) {
            clearBrowserCookies();
            clearBrowserLocalStorage();
            open(url);
        }

//        allure.attachScreenshot("Скриншот страницы '" + url +"'");
//        allure.attachScreenshot("Страница '" + url +"'");

    }

    @AfterEach
    @Step("Закрыть браузер")
    void tearDown() {
        Selenide.closeWindow();
    }

}
