import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SberTest extends BaseTest {
    Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

    //    Сценарий №1
    @Test
    public void testTravelInsuranceCertificate() {
        //  1. Перейти на страницу http://www.sberbank.ru/ru/person
        driver.get(TravelInsuranceUrl);

        // 2. Нажать на – Страхование
        WebElement InsuranceReference = driver.findElement(By.xpath("//a[contains(@data-cga_click_service_menu,'Страхование')]"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", InsuranceReference
        );
        InsuranceReference.click();

        //смотрим, что выполнился переход по клику и ссылка изменилась
        assert driver.getCurrentUrl().contains("insuranceprogram");

        //3. Находим блок с названием "Страхование для путешественников"
        WebElement TravellersInsuranceBlock = driver.findElement(By.xpath("//div[contains(@class,'uc-full__block_header')]/a[contains(@href,'travel')]/h3"));

        //4. Проверить наличие на странице заголовка – Страхование для путешественников
        String TravellersInsuranceBlockHeader = TravellersInsuranceBlock.getText();
        Assert.assertEquals("Страхование для путешественников", TravellersInsuranceBlockHeader);

        //5. Нажать на – Оформить Онлайн
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebElement getOnlineButton = driver.findElement(By.xpath("//a[contains(@href, 'vzr')]/b[contains(text(), 'Оформить онлайн')]"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", getOnlineButton
        );
        wait.until(ExpectedConditions.visibilityOf(getOnlineButton)).click();

        //6. На вкладке – Выбор полиса выбрать Минимальный пакет
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebElement minSet = driver.findElement(By.cssSelector("online-card-program div.selected"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", minSet
        );
        wait.until(ExpectedConditions.visibilityOf(minSet)).click();

        //7. Нажать Оформить
        WebElement formButton = driver.findElement(By.xpath("//button[(text()='Оформить')]"));
        wait.until(ExpectedConditions.visibilityOf(formButton)).click();

        //смотрим, что выполнился переход по клику и ссылка изменилась
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //assert driver.getCurrentUrl().contains("forming");

        //8. На вкладке Оформить заполнить поля:
        //• Фамилию и Имя, Дату рождения застрахованных
        WebElement surnameField = driver.findElement(By.cssSelector("input#surname_vzr_ins_0"));
        WebElement nameField = driver.findElement(By.cssSelector("input#name_vzr_ins_0"));
        WebElement birthField = driver.findElement(By.cssSelector("input#birthDate_vzr_ins_0"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", surnameField
        );
        surnameField.sendKeys("Иванов");
        nameField.sendKeys("Иван");
        birthField.sendKeys("01011990", Keys.ENTER);

        //• Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол
        WebElement insurerSurnameField = driver.findElement(By.cssSelector("input#person_lastName"));
        WebElement insurerNameField = driver.findElement(By.cssSelector("input#person_firstName"));
        WebElement insurerSecondNameField = driver.findElement(By.cssSelector("input#person_middleName"));
        WebElement insurerBirthField = driver.findElement(By.cssSelector("input#person_birthDate"));
        WebElement insurerSexField = driver.findElement(By.xpath("//label[text()='Женский']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", insurerSurnameField
        );
        insurerSurnameField.sendKeys("Петров");
        insurerNameField.sendKeys("Иван");
        insurerSecondNameField.sendKeys("Иванович");
        insurerBirthField.sendKeys("01011990", Keys.TAB);
        insurerSexField.click();

        //• Паспортные данные
        WebElement passSeriesField = driver.findElement(By.cssSelector("input#passportSeries"));
        WebElement passNoField = driver.findElement(By.cssSelector("input#passportNumber"));
        WebElement passIssueDateField = driver.findElement(By.cssSelector("input#documentDate"));
        WebElement passWhoIssueField = driver.findElement(By.cssSelector("input#documentIssue"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", passSeriesField
        );
        passSeriesField.sendKeys("4515");
        passNoField.sendKeys("758545");
        passIssueDateField.sendKeys("01011990", Keys.TAB);
        passWhoIssueField.sendKeys("МВД блабла");

        //• Контактные данные не заполняем

        //9. Проверить, что все поля заполнены правильно
        Assert.assertEquals("Иванов", surnameField.getAttribute("value"));
        Assert.assertEquals("Иван", nameField.getAttribute("value"));
        Assert.assertEquals("01.01.1990", birthField.getAttribute("value"));
        Assert.assertEquals("Петров", insurerSurnameField.getAttribute("value"));
        Assert.assertEquals("Иван", insurerNameField.getAttribute("value"));
        Assert.assertEquals("Иванович", insurerSecondNameField.getAttribute("value"));
        Assert.assertEquals("01.01.1990", insurerBirthField.getAttribute("value"));
        Assert.assertEquals("4515", passSeriesField.getAttribute("value"));
        Assert.assertEquals("758545", passNoField.getAttribute("value"));
        Assert.assertEquals("01.01.2010", passIssueDateField.getAttribute("value"));
        Assert.assertEquals("МВД блабла", passWhoIssueField.getAttribute("value"));

        //10. Нажать продолжить
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        wait.until(ExpectedConditions.visibilityOf(submitButton)).click();

        //11. Проверить, что появилось сообщение - Заполнены не все обязательные поля
        WebElement alerBlock = driver.findElement(By.cssSelector("div.alert-form-error"));
        Assert.assertEquals("При заполнении данных произошла ошибка", alerBlock.getText());
        // проверяем количетсво уведомлений по незаполненным полям
        List<WebElement> errorFields = driver.findElements(By.cssSelector("span.form-control__message"));
        assert errorFields.size() == 3;
    }
}
