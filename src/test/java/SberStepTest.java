import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import steps.*;

import java.util.HashMap;

public class SberStepTest extends SberBaseSteps {
    SberMainPageSteps sberMainPageSteps = new SberMainPageSteps();
    SberInsuranceTypesPageSteps sberInsuranceTypesPageSteps = new SberInsuranceTypesPageSteps();
    SberTravelInsuranceFormPageSteps sberTravelInsuranceFormPageSteps = new SberTravelInsuranceFormPageSteps();
    SberTravelInsuranceParametersPageSteps sberTravelInsuranceParametersPageSteps = new SberTravelInsuranceParametersPageSteps();

    HashMap<String, String> testData1 = new HashMap<>();
    HashMap<String, String> testData2 = new HashMap<>();
    HashMap<String, String> testData3 = new HashMap<>();

    @Ignore
    @Title("Заведомо падающий тест")
    @Test
    public void TestFail() {
        testData1.put("Фамилия", "Иванов");
        testData1.put("Имя", "Иван");
        testData1.put("Дата рождения", "01011990");

        testData2.put("Фамилия", "Петров");
        testData2.put("Имя", "Иван");
        testData2.put("Отчество", "Иванович");
        testData2.put("Дата рождения", "01011990");

        testData3.put("Серия", "4515");
        testData3.put("Номер", "758545");
        testData3.put("Дата выдачи", "01012010");
        testData3.put("Кем выдан", "МВД блабла");

        // Перейти на страницу http://www.sberbank.ru/ru/person
        driver.get(TravelInsuranceUrl);

        // Нажать на – Страхование
        sberMainPageSteps.selectInsuranceBottomMenu();

        // Нажать на – Оформить Онлайн
        sberInsuranceTypesPageSteps.clickGetOnlineButtonForInsuranceType();

        // На вкладке – Выбор полиса выбрать Спортивный пакет
        sberTravelInsuranceParametersPageSteps.choosePackageType("Спортивный");

        // Нажать Оформить
        sberTravelInsuranceParametersPageSteps.clickFormButton();

        //На вкладке Оформить заполнить поля
        sberTravelInsuranceFormPageSteps.fillInsuredFields(testData1);
        sberTravelInsuranceFormPageSteps.fillInsurerMainFields(testData2);
        sberTravelInsuranceFormPageSteps.fillInsurerPassFields(testData3);

        //  Проверить, что все поля заполнены правильно
        sberTravelInsuranceFormPageSteps.checkFillInsuredFields(testData1);
        sberTravelInsuranceFormPageSteps.checkFillInsurerFields(testData2);
        sberTravelInsuranceFormPageSteps.checkFillInsurerPassFields(testData3);

        //Нажать продолжить
        sberTravelInsuranceFormPageSteps.clickSubmitButton();

        // Проверить, что появилось сообщение - Заполнены не все обязательные поля
        sberTravelInsuranceFormPageSteps.checkIfAlertsPresent(3);
    }


    @Title("Заявка на полис страхования путешественников. Не заполнены обязательные поля формы")
    @Test
    public void Test() {
        testData1.put("Фамилия", "Иванов");
        testData1.put("Имя", "Иван");
        testData1.put("Дата рождения", "01.01.1990");

        testData2.put("Фамилия", "Петров");
        testData2.put("Имя", "Иван");
        testData2.put("Отчество", "Иванович");
        testData2.put("Дата рождения", "01.01.1990");

        testData3.put("Серия", "4515");
        testData3.put("Номер", "758545");
        testData3.put("Дата выдачи", "01.01.2010");
        testData3.put("Кем выдан", "МВД блабла");

        // Перейти на страницу http://www.sberbank.ru/ru/person
        driver.get(TravelInsuranceUrl);

        // Нажать на – Страхование
        sberMainPageSteps.selectInsuranceBottomMenu();

        // Находим блок с названием "Страхование для путешественников"
        // Проверить наличие на странице заголовка – Страхование для путешественников
        sberInsuranceTypesPageSteps.checkPageTitle("Страхование для путешественников");

        // Нажать на – Оформить Онлайн
        sberInsuranceTypesPageSteps.clickGetOnlineButtonForInsuranceType();

        // На вкладке – Выбор полиса выбрать Спортивный пакет
        sberTravelInsuranceParametersPageSteps.choosePackageType("Спортивный");

        // Нажать Оформить
        sberTravelInsuranceParametersPageSteps.clickFormButton();

        //На вкладке Оформить заполнить поля
        sberTravelInsuranceFormPageSteps.fillInsuredFields(testData1);
        sberTravelInsuranceFormPageSteps.fillInsurerMainFields(testData2);
        sberTravelInsuranceFormPageSteps.fillInsurerPassFields(testData3);

        //  Проверить, что все поля заполнены правильно
        sberTravelInsuranceFormPageSteps.checkFillInsuredFields(testData1);
        sberTravelInsuranceFormPageSteps.checkFillInsurerFields(testData2);
        sberTravelInsuranceFormPageSteps.checkFillInsurerPassFields(testData3);

        //Нажать продолжить
        sberTravelInsuranceFormPageSteps.clickSubmitButton();

        // Проверить, что появилось сообщение - Заполнены не все обязательные поля
        sberTravelInsuranceFormPageSteps.checkIfAlertsPresent(3);

    }
}
