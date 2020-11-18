package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;


public class SberCucumberSteps {

    SberMainPageSteps sberMainPageSteps = new SberMainPageSteps();
    SberInsuranceTypesPageSteps sberInsuranceTypesPageSteps = new SberInsuranceTypesPageSteps();
    SberTravelInsuranceFormPageSteps sberTravelInsuranceFormPageSteps = new SberTravelInsuranceFormPageSteps();
    SberTravelInsuranceParametersPageSteps sberTravelInsuranceParametersPageSteps = new SberTravelInsuranceParametersPageSteps();

    @When("^Нажать на – Страхование")
    public void selectInsurance() {
        sberMainPageSteps.selectInsuranceBottomMenu();
    }

    @When("^Находим блок с названием:\"(.*)\"$")
    public void checkTitle(String name) {
        sberInsuranceTypesPageSteps.checkPageTitle(name);
    }

    @When("^Нажать на – Оформить Онлайн")
    public void clickGetOnline() {
        sberInsuranceTypesPageSteps.clickGetOnlineButtonForInsuranceType();
    }

    @When("^Выбор полиса\"(.*)\"$")
    public void choosePackage(String name) {
        sberTravelInsuranceParametersPageSteps.choosePackageType(name);
    }

    @When("^Нажать на – Оформить")
    public void form() {
        sberTravelInsuranceParametersPageSteps.clickFormButton();
    }

    @When("^Заполняем общие данные застрахованных")
    public void fillInsured(DataTable fields) {
        fields.asMap(String.class, String.class).forEach(
                (key, value) -> sberTravelInsuranceFormPageSteps.fillInsuredField(key, value));
    }

    @When("^Заполняем общие данные страхователя")
    public void fillInsurer(DataTable fields) {
        fields.asMap(String.class, String.class).forEach(
                (key, value) -> sberTravelInsuranceFormPageSteps.fillInsurerMainField(key, value));
    }

    @When("^Заполняем данные паспорта страхователя")
    public void fillInsurerPass(DataTable fields) {
        fields.asMap(String.class, String.class).forEach(
                (key, value) -> sberTravelInsuranceFormPageSteps.fillInsurerPassField(key, value));
    }

    @When("^Проверяем заполненные общие данные застрахованных")
    public void checkFillInsured(DataTable fields) {
        fields.asMap(String.class, String.class).forEach(
                (key, value) -> sberTravelInsuranceFormPageSteps.checkFillInsuredField(key, value));
    }

    @When("^Проверяем заполненные общие данные страхователя")
    public void checkFillInsurer(DataTable fields) {
        fields.asMap(String.class, String.class).forEach(
                (key, value) -> sberTravelInsuranceFormPageSteps.checkFillInsurerField(key, value));
    }

    @When("^Проверяем заполненные данные паспорта страхователя")
    public void checkFillInsurerPass(DataTable fields) {
        fields.asMap(String.class, String.class).forEach(
                (key, value) -> sberTravelInsuranceFormPageSteps.checkFillInsurerPassField(key, value));
    }

    @When("^Нажать на – Продолжить")
    public void clickContinueButton() {
        sberTravelInsuranceFormPageSteps.clickSubmitButton();
    }

    @When("^появилось сообщение - Заполнены не все обязательные поля")
    public void checkIfAlertsPresent(int count) {
        sberTravelInsuranceFormPageSteps.checkIfAlertsPresent(count);
    }
}
