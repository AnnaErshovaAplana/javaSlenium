package steps;

import org.junit.Assert;
import pages.SberInsuranceTypesPage;
import pages.SberTravelInsuranceFormPage;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class SberTravelInsuranceFormPageSteps {

    @Step("поле {0} заполняется значением {1}")
    public void fillInsuredField(String field, String value) {
        new SberTravelInsuranceFormPage().fillInsuredFields(field, value);
    }

    @Step("поле - {0}, значение - {1}")
    public void fillInsurerMainField(String name, String value) {
        new SberTravelInsuranceFormPage().fillInsurerMainFields(name, value);
    }

    @Step("поле - {0}, значение - {1}")
    public void fillInsurerPassField(String name, String value) {
        new SberTravelInsuranceFormPage().fillInsurerPassFields(name, value);
    }

    @Step("Заполнены данные застрахованных: ")
    public void fillInsuredFields(HashMap<String, String> fields) {
        fields.forEach((k, v) -> fillInsuredField(k, v));
    }

    @Step("Заполнены персональные данные страхователя: ")
    public void fillInsurerMainFields(HashMap<String, String> fields) {
        fields.forEach((k, v) -> fillInsurerMainField(k, v));
    }

    @Step("Заполнены данные паспорта страхователя: ")
    public void fillInsurerPassFields(HashMap<String, String> fields) {
        fields.forEach((k, v) -> fillInsurerPassField(k, v));
    }

    @Step("Заполненная форма отправлена")
    public void clickSubmitButton() {
        new SberTravelInsuranceFormPage().clickSubmitButton();
    }

    @Step("Проверка на сообщение об ошибке пройдена")
    public void checkIfAlertsPresent(int emptyFields) {
        new SberTravelInsuranceFormPage().checkIfAlertsPresent(emptyFields);
    }

    @Step("заполняютя поля застрахованных")
    public void checkFillInsuredFields(HashMap<String, String> fields) {
        fields.forEach((k, v) -> checkFillInsuredField(k, v));
    }

    @Step("заполняютя поля страхователя")
    public void checkFillInsurerFields(HashMap<String, String> fields) {
        fields.forEach((k, v) -> checkFillInsurerField(k, v));
    }

    @Step("заполняютя поля паспортных данных страхователя")
    public void checkFillInsurerPassFields(HashMap<String, String> fields) {
        fields.forEach((k, v) -> checkFillInsurerPassField(k, v));
    }

    @Step("поле {0} застрахованных заполнено значением {1}")
    public void checkFillInsuredField(String field, String value) {
        String actual = new SberTravelInsuranceFormPage().getFillInsuredField(field);
        assertTrue(String.format("Значение поля [%s] равно [%s]. Ожидалось - [%s]", field, actual, value),
                actual.equals(value));
    }

    @Step("поле {0} страхователя заполнено значением {1}")
    public void checkFillInsurerField(String field, String value) {
        String actual = new SberTravelInsuranceFormPage().getFillInsurerField(field);
        assertTrue(String.format("Значение поля [%s] равно [%s]. Ожидалось - [%s]", field, actual, value),
                actual.equals(value));
    }

    @Step("поле {0} паспортных данных страхователя заполнено значением {1}")
    public void checkFillInsurerPassField(String field, String value) {
        String actual = new SberTravelInsuranceFormPage().getFillInsurerPassField(field);
        assertTrue(String.format("Значение поля [%s] равно [%s]. Ожидалось - [%s]", field, actual, value),
                actual.equals(value));
    }

}
