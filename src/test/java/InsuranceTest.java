import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

// класс для тестов
public class InsuranceTest extends BaseTest {

    //сами тесты(методы). атрибут @Test
    @Test
    @Ignore
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
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        // Жмем на нопку для перехода в форму отправки заявки. Ждем пока откроется форма
        WebElement sendButton = driver.findElement(By.xpath("//a[contains(text(),'Отправить заявку')]"));
        wait.until(ExpectedConditions.visibilityOf(sendButton)).click();
        WebElement applicationForm = driver.findElement(By.xpath("//b[contains(text(),'Заявка на добровольное')]"));
        wait.until(ExpectedConditions.visibilityOf(applicationForm));
        //Проверяем корректной названия заголовка формы
        Assert.assertEquals("Заявка на добровольное медицинское страхование", applicationForm.getText());
        // Заполняем поля формы и нажимаем отправить
        By surName = By.xpath("//input[@name='LastName']");
        fillFields(surName, "Иванов");

        By name = By.xpath("//input[@name='FirstName']");
        fillFields(name, "Иван");

        WebElement selectRegion = driver.findElement(By.cssSelector("select[name='Region']"));
        Select select = new Select(selectRegion);
        select.selectByValue("77");

        By email = By.xpath("//input[@name='Email']");
        fillFields(email, "1111111");

        By date = By.xpath("//input[contains(@data-bind,'ContactDate')]");
        fillFields(date, "12122020");

        By comment = By.xpath("//textarea[@name='Comment']");
        fillFields(comment, "test");

        WebElement checkbox = driver.findElement(By.xpath("//input[@class='checkbox']"));
        checkbox.click();

        WebElement sendAppButton = driver.findElement(By.xpath("//button[@id='button-m']"));
        sendAppButton.click();

        // проверяем, что появилась ошибка заполнения поле EMAIL
        WebElement validationError = driver.findElement(By.xpath("//span[@class=\"validation-error-text\"][contains(text(),'адрес')]"));
        Assert.assertEquals("Введите адрес электронной почты", validationError.getText());

        // проверяем, чем заполнены остальные поля
        Assert.assertEquals("Иванов", driver.findElement(surName).getAttribute("value"));
        Assert.assertEquals("Иван", driver.findElement(name).getAttribute("value"));
        Assert.assertEquals("12.12.2020", driver.findElement(date).getAttribute("value"));
        Assert.assertEquals("Москва", select.getAllSelectedOptions().get(0).getText());
        Assert.assertEquals("test\n", driver.findElement(comment).getAttribute("value"));
    }
}
