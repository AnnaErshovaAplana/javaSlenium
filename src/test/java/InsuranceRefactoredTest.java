import pages.RGSDMSPage;
import pages.RGSMainPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RGSSendAppPage;

public class InsuranceRefactoredTest extends BaseTest {

    @Test
    public void newInsuranceTest() {
        // переходим по ссылке
        driver.get(baseUrl);
        //создаем экземпляр страниц, чтобы работать с ее методами
        RGSMainPage mainPage = new RGSMainPage(driver);
        mainPage.selectInMenu("ДМС");

        String curUrl = driver.getCurrentUrl();
        assert curUrl.contains("dms");

        // Жмем на нопку для перехода в форму отправки заявки. Ждем пока откроется форма
        RGSDMSPage DMSPage = new RGSDMSPage(driver);
        DMSPage.clickButton("Отправить заявку");

        //Проверяем корректной названия заголовка формы
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        WebElement applicationForm = driver.findElement(By.xpath("//b[contains(text(),'Заявка на добровольное')]"));
        wait.until(ExpectedConditions.visibilityOf(applicationForm));

        Assert.assertEquals("Заявка на добровольное медицинское страхование", applicationForm.getText());
        // Заполняем поля формы и нажимаем отправить
        RGSSendAppPage SendAppPage = new RGSSendAppPage(driver);

        SendAppPage.fillFields("Фамилия", "Иванов");
        SendAppPage.fillFields("Имя", "Иван");
        SendAppPage.fillFields("Регион", "Москва");
        SendAppPage.fillFields("Эл. почта", "1111111");
        SendAppPage.fillFields("Дата контакта", "12122020");
        SendAppPage.fillFields("Комментарии", "test");

        WebElement checkbox = driver.findElement(By.xpath("//input[@class='checkbox']"));
        checkbox.click();

        WebElement sendAppButton = driver.findElement(By.xpath("//button[@id='button-m']"));
        sendAppButton.click();

        // проверяем, что появилась ошибка заполнения поле EMAIL
        WebElement validationError = driver.findElement(By.xpath("//span[@class=\"validation-error-text\"][contains(text(),'адрес')]"));
        Assert.assertEquals("Введите адрес электронной почты", validationError.getText());

        // проверяем, чем заполнены остальные поля
        Assert.assertEquals("Иванов", SendAppPage.getFillField("Фамилия"));
        Assert.assertEquals("Иван", SendAppPage.getFillField("Имя"));
        Assert.assertEquals("12.12.2020", SendAppPage.getFillField("Дата контакта"));
        Assert.assertEquals("Москва", SendAppPage.getFillField("Регион"));
        Assert.assertEquals("test\n", SendAppPage.getFillField("Комментарии"));
    }
}
