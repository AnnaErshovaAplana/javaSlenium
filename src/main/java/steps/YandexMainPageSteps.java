package steps;

import pages.YandexMainPage;
import ru.yandex.qatools.allure.annotations.Step;

public class YandexMainPageSteps {

    @Step("Выбран сервис {0}")
    public void selectService(String service) {
        new YandexMainPage().goToService(service);
    }

}
