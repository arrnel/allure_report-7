package com.arrnel.project.sbermegamarket.components.common;

import static com.codeborne.selenide.Condition.exist;

public class Common {
    CommonLocators locator = new CommonLocators();

    public Common waitLoadingMaskNotExist() {
        locator.searchMask().shouldBe(exist);
        locator.searchMask().shouldNotBe(exist);
        return this;
    }

}
