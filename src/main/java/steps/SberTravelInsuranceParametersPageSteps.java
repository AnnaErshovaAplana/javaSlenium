package steps;

import pages.SberTravelInsuranceParametersPage;
import ru.yandex.qatools.allure.annotations.Step;

public class SberTravelInsuranceParametersPageSteps extends SberBaseSteps {

    @Step("Выбран тип пакета страхования путешественников: {0}")
    public void choosePackageType(String name) {
        new SberTravelInsuranceParametersPage(driver).choosePackageType(name);
    }

    @Step("Выполнен переход к форме полиса страхования путешественников выбранного пакета")
    public void clickFormButton() {
        new SberTravelInsuranceParametersPage(driver).clickFormButton();
    }

}
