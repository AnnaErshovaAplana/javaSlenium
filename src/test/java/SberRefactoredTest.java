import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SberInsuranceTypesPage;
import pages.SberMainPage;
import pages.SberTravelInsuranceFormPage;
import pages.SberTravelInsuranceParametersPage;

import java.util.concurrent.TimeUnit;

public class SberRefactoredTest extends BaseTest {
    Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

    //    Сценарий №1
    @Test
    @Ignore
    public void testRefactoredTravelInsuranceCertificate() {
        //  1. Перейти на страницу http://www.sberbank.ru/ru/person
        driver.get(TravelInsuranceUrl);

        // 2. Нажать на – Страхование
        SberMainPage mainPage = new SberMainPage();
        mainPage.selectInBottomMenu("Страхование");

        //смотрим, что выполнился переход по клику и ссылка изменилась
        assert driver.getCurrentUrl().contains("insuranceprogram");

        //3. Находим блок с названием "Страхование для путешественников"
        // 4. Проверить наличие на странице заголовка – Страхование для путешественников
        SberInsuranceTypesPage insuranceTypesPage = new SberInsuranceTypesPage();
        String TravellersInsuranceBlockHeader = insuranceTypesPage.getInsuranceTypeVisibleName("Страхование для путешественников");
        Assert.assertEquals("Страхование для путешественников", TravellersInsuranceBlockHeader);

        //5. Нажать на – Оформить Онлайн
        insuranceTypesPage.clickGetOnlineButtonForInsuranceType("Страхование для путешественников");

        //6. На вкладке – Выбор полиса выбрать Спортивный пакет
        SberTravelInsuranceParametersPage travelInsuranceTypeParametersPage = new SberTravelInsuranceParametersPage();
        travelInsuranceTypeParametersPage.choosePackageType("Спортивный");

        //7. Нажать Оформить
        travelInsuranceTypeParametersPage.clickFormButton();

        //смотрим, что выполнился переход по клику и ссылка изменилась
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wait.equals(driver.getCurrentUrl().contains("forming"));

        //8. На вкладке Оформить заполнить поля:
        //• Фамилию и Имя, Дату рождения застрахованных
        SberTravelInsuranceFormPage travelInsuranceFormPage = new SberTravelInsuranceFormPage();
        travelInsuranceFormPage.fillInsuredFields("Фамилия", "Иванов");
        travelInsuranceFormPage.fillInsuredFields("Имя", "Иван");
        travelInsuranceFormPage.fillInsuredFields("Дата рождения", "01011990");

        //• Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол
        travelInsuranceFormPage.fillInsurerMainFields("Фамилия", "Петров");
        travelInsuranceFormPage.fillInsurerMainFields("Имя", "Иван");
        travelInsuranceFormPage.fillInsurerMainFields("Отчество", "Иванович");
        travelInsuranceFormPage.fillInsurerMainFields("Дата рождения", "01011990");
        travelInsuranceFormPage.fillInsurerMainFields("Пол", "Женский");

        //• Паспортные данные
        travelInsuranceFormPage.fillInsurerPassFields("Серия", "4515");
        travelInsuranceFormPage.fillInsurerPassFields("Номер", "758545");
        travelInsuranceFormPage.fillInsurerPassFields("Дата выдачи", "01011990");
        travelInsuranceFormPage.fillInsurerPassFields("Кем выдан", "МВД блабла");

        //• Контактные данные не заполняем

        // 9. Проверить, что все поля заполнены правильно
        travelInsuranceFormPage.checkFillField("Иванов", travelInsuranceFormPage.getElementByName("Фамилия застрахованных"));
        travelInsuranceFormPage.checkFillField("Иван", travelInsuranceFormPage.getElementByName("Имя застрахованных"));
        travelInsuranceFormPage.checkFillField("01.01.1990", travelInsuranceFormPage.getElementByName("Дата рождения застрахованных"));
        travelInsuranceFormPage.checkFillField("Петров", travelInsuranceFormPage.getElementByName("Фамилия страхователя"));
        travelInsuranceFormPage.checkFillField("Иван", travelInsuranceFormPage.getElementByName("Имя страхователя"));
        travelInsuranceFormPage.checkFillField("Иванович", travelInsuranceFormPage.getElementByName("Отчество страхователя"));
        travelInsuranceFormPage.checkFillField("01.01.1990", travelInsuranceFormPage.getElementByName("Дата рождения страхователя"));
        travelInsuranceFormPage.checkFillField("4515", travelInsuranceFormPage.getElementByName("Серия"));
        travelInsuranceFormPage.checkFillField("758545", travelInsuranceFormPage.getElementByName("Номер"));
        travelInsuranceFormPage.checkFillField("01.01.2010", travelInsuranceFormPage.getElementByName("Дата выдачи"));
        travelInsuranceFormPage.checkFillField("МВД блабла", travelInsuranceFormPage.getElementByName("Кем выдан"));

        //10. Нажать продолжить
        travelInsuranceFormPage.clickSubmitButton();

        //11. Проверить, что появилось сообщение - Заполнены не все обязательные поля
        travelInsuranceFormPage.checkIfAlertsPresent(3);
    }
}
