package tests;

import config.SearchConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    SearchConfig searchConfig = ConfigFactory.create(SearchConfig.class);

    @Test
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchConfig.searchQuery());
        });
        step("Verify content found", () ->
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0)));
    }
    @Test
    void openSearchResultTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchConfig.searchQuery());
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

        step("Open search result", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).
                    find(text(searchConfig.searchQuery())).click();
        });

        step("Check error on screen", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_text"))
                    .shouldHave(text("An error occurred"));
        });
    }
}