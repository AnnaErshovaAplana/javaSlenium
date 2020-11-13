package steps;

import pages.SberMainPage;
import ru.yandex.qatools.allure.annotations.Step;

public class SberMainPageSteps extends SberBaseSteps {
    // в отчете вместо 0 отобразится параметор метода с индексом 0, т.е. menuItem
    @Step("Выбран пункт в нижнем меню {0}")
    public void selectInBottomMenu(String menuItem) {
        new SberMainPage(driver).selectInBottomMenu(menuItem);
    }

    @Step("Выбран пункт Страхование в нижнем меню")
    public void selectInsuranceBottomMenu() {
        new SberMainPage(driver).selectInsuranceBottomMenu();
    }

}
