import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomeTask2 {


    @Test
    void test() {
        open("https://alfabank.ru/make-money/");
        $("[data-test-id=\"filter\"]").$(byText("Депозиты")).parent().click();
        $("#more-buttons").$(byText("Архивные счета и депозиты")).click();
        $("[data-test-id=tabs-list]").$(byText("Депозиты")).click();
        $$("[data-widget-name=\"CatalogCard\"]").shouldHaveSize(5);
    }
}
