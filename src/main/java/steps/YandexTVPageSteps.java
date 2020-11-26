package steps;

import pages.YandexBasePage;
import pages.YandexTVPage;
import ru.yandex.qatools.allure.annotations.Step;

public class YandexTVPageSteps {

    @Step("При поиске ТВ выбраны прозводители: {0}")
    public void enterTVManufacturer(String[] makersList) {
        new YandexTVPage().setTVManufacturer(makersList);
    }
}
