package steps;

import pages.YandexHeadphonesPage;
import ru.yandex.qatools.allure.annotations.Step;

public class YandexHeadphonesPageSteps {

    @Step("При поиске наушников выбраны прозводители: {0}")
    public void enterHeadphonesManufacturer(String[] makersList) {
        new YandexHeadphonesPage().setHeadphonesManufacturer(makersList);
    }

}
