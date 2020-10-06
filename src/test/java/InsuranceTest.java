import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

// класс для тестов
public class InsuranceTest {
    WebDriver driver;
    String baseUrl;
    String TravelInsuranceUrl;

    @Before
    public void beforeTest() {
        // указываем путь до драйверов в системных свойствах
        System.setProperty("webdriver.gecko.driver","drv/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver","drv/chromedriver.exe");
        baseUrl = "https://www.rgs.ru/";
        TravelInsuranceUrl = "http://www.sberbank.ru/ru/person";
        //создаем экземпляр драйвера для Chrome
        driver = new ChromeDriver();
        // устанавливаем неявные ожидания на поиск элементов
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // разворачиваем окно браузера на весь экран
        driver.manage().window().maximize();

    }
//сами тесты(методы). атрибут @Test
    @Test
    public void testInsurance() {
        // переходим по ссылке
        driver.get(baseUrl);
        WebElement menu = driver.findElement(By.xpath("//a[contains(@data-toggle,'dropdown')][contains(text(),'Меню')]"));
        menu.click();
        WebElement dms = driver.findElement(By.xpath("//li/a[contains(text(),'ДМС')]"));
        dms.click();
        //смотрим, что выполнился переход по клику и ссылка изменилась
        String curUrl = driver.getCurrentUrl();
        assert curUrl.contains("dms");

        //ждем пока прогрузится страница, чтобы искать элемент. Явне ожидание
        Wait<WebDriver> wait = new WebDriverWait(driver,5,1000);
        // Жмем на нопку для перехода в форму отправки заявки. Ждем пока откроется форма
        WebElement sendButton = driver.findElement(By.xpath("//a[contains(text(),'Отправить заявку')]"));
        wait.until(ExpectedConditions.visibilityOf(sendButton)).click();
        WebElement applicationForm = driver.findElement(By.xpath("//b[contains(text(),'Заявка на добровольное')]"));
        wait.until(ExpectedConditions.visibilityOf(applicationForm));
        //Проверяем корректной названия заголовка формы
        Assert.assertEquals("Заявка на добровольное медицинское страхование", applicationForm.getText());
        // Заполняем поля формы и нажимаем отправить
        By surName = By.xpath("//input[@name='LastName']");
        fillFields(surName,"Иванов");

        By name = By.xpath("//input[@name='FirstName']");
        fillFields(name,"Иван");

        WebElement selectRegion = driver.findElement(By.cssSelector("select[name='Region']"));
        Select select = new Select(selectRegion);
        select.selectByValue("77");

        By phone = By.xpath("//input[contains(@data-bind,'Phone')]");
        fillFields(phone,"9035554444");

        By date = By.xpath("//input[contains(@data-bind,'ContactDate')]");
        fillFields(date,"12122020");

        By email = By.xpath("//input[@name='Email']");
        fillFields(email,"1111111");

        By comment = By.xpath("//textarea[@name='Comment']");
        fillFields(comment,"test");

        WebElement checkbox = driver.findElement(By.xpath("//input[@class='checkbox']"));
        checkbox.click();

        WebElement sendAppButton = driver.findElement(By.xpath("//button[@id='button-m']"));
        sendAppButton.click();

        // проверяем, что появилась ошибка заполнения поле EMAIL
        WebElement validationError = driver.findElement(By.xpath("//span[@class=\"validation-error-text\"][contains(text(),'адрес')]"));
        Assert.assertEquals("Введите адрес электронной почты", validationError.getText() );

        // проверяем, чем заполнены остальные поля
        Assert.assertEquals("Иванов", driver.findElement(surName).getAttribute("value"));
        Assert.assertEquals("Иван", driver.findElement(name).getAttribute("value"));
        Assert.assertEquals("12122020", driver.findElement(date).getAttribute("value"));
        Assert.assertEquals("9035554444", driver.findElement(phone).getAttribute("value"));
        Assert.assertEquals("test", driver.findElement(comment).getAttribute("value"));
    }

    //метод для заполнения полей
    public void fillFields(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    //    Сценарий №1
    @Test
    public void testTravelInsuranceCertificate(){
        //  1. Перейти на страницу http://www.sberbank.ru/ru/person
        driver.get(TravelInsuranceUrl);

        // 2. Нажать на – Страхование
        WebElement InsuranceReference = driver.findElement(By.xpath("//a[contains(@data-cga_click_service_menu,'Страхование')]"));
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView(true);",InsuranceReference
        );
        InsuranceReference.click();

        //смотрим, что выполнился переход по клику и ссылка изменилась
        assert driver.getCurrentUrl().contains("insuranceprogram");

        //3. Находим блок с названием "Страхование для путешественников"
        WebElement TravellersInsuranceBlock = driver.findElement(By.xpath("//div[contains(@class,'uc-full__block_header')]/a[contains(@href,'travel')]/h3"));

        //4. Проверить наличие на странице заголовка – Страхование для путешественников
        String TravellersInsuranceBlockHeader = TravellersInsuranceBlock.getText();
        Assert.assertEquals("Страхование для путешественников",TravellersInsuranceBlockHeader);

        //5. Нажать на – Оформить Онлайн
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        WebElement getOnlineButton = driver.findElement(By.xpath("//a[contains(@href, 'vzr')]/b[contains(text(), 'Оформить онлайн')]"));
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView(true);",getOnlineButton
        );
        getOnlineButton.click();

        //6. На вкладке – Выбор полиса выбрать Минимальный пакет
        WebElement minSet = driver.findElement(By.cssSelector("online-card-program div.selected"));
        minSet.click();

        //7. Нажать Оформить
        WebElement formButton= driver.findElement(By.xpath("//button[(text()='Оформить')]"));
        formButton.click();

        //смотрим, что выполнился переход по клику и ссылка изменилась
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        assert driver.getCurrentUrl().contains("forming");

        //8. На вкладке Оформить заполнить поля:
        //• Фамилию и Имя, Дату рождения застрахованных
        WebElement surnameField = driver.findElement(By.cssSelector("input#surname_vzr_ins_0"));
        WebElement nameField = driver.findElement(By.cssSelector("input#name_vzr_ins_0"));
        WebElement birthField = driver.findElement(By.cssSelector("input#birthDate_vzr_ins_0"));
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView(true);",surnameField
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
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView(true);",insurerSurnameField
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
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView(true);",passSeriesField
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
        submitButton.click();

        //11. Проверить, что появилось сообщение - Заполнены не все обязательные поля
        WebElement alerBlock = driver.findElement(By.cssSelector("div.alert-form-error"));
        Assert.assertEquals("При заполнении данных произошла ошибка",alerBlock.getText());
        // проверяем количетсво уведомлений по незаполненным полям
        List<WebElement> errorFields = driver.findElements(By.cssSelector("span.form-control__message"));
        assert errorFields.size() == 3;
    }


    @After
    public  void afterTest() {
        driver.quit();
    }
}
