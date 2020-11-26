package steps;

import pages.YandexElectronicsPage;
import ru.yandex.qatools.allure.annotations.Step;

public class YandexElectronicsPageSteps {

    @Step("В разделе Электроника выбрана категория {0}")
    public void selectProductCategory(String name) {
        new YandexElectronicsPage().chooseElectronicsCategory(name);
    }
}
