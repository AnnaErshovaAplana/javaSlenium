package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.SberBaseSteps;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SberTravelInsuranceFormPage extends SberBasePage {
    @FindBy(xpath = "//*[contains(@class,'step-element active')]//a[text()='Оформление']")
    WebElement formingStepActive;

    @FindBy(css = "input#surname_vzr_ins_0")
    WebElement surname;

    @FindBy(css = "input#name_vzr_ins_0")
    WebElement name;

    @FindBy(css = "input#birthDate_vzr_ins_0")
    WebElement birth;

    @FindBy(css = "input#person_lastName")
    WebElement insurerSurname;

    @FindBy(css = "input#person_firstName")
    WebElement insurerName;

    @FindBy(css = "input#person_middleName")
    WebElement insurerSecondName;

    @FindBy(css = "input#person_birthDate")
    WebElement insurerBirth;

    @FindBy(xpath = "//label[text()='Женский']")
    WebElement insurerSexFemale;

    @FindBy(css = "input#passportSeries")
    WebElement insurerPassSeries;

    @FindBy(css = "input#passportNumber")
    WebElement insurerPassNo;

    @FindBy(css = "input#documentDate")
    WebElement insurerPassIssueDate;

    @FindBy(css = "input#documentIssue")
    WebElement insurerPassWhoIssue;

    @FindBy(css = "button[type='submit']")
    WebElement submitButton;

    @FindBy(css = "div.alert-form-error")
    WebElement alertBlock;

    @FindAll(@FindBy(css = "span.form-control__message"))
    List<WebElement> errorFields;

    public SberTravelInsuranceFormPage() {
        PageFactory.initElements(SberBaseSteps.getDriver(), this);
        (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(formingStepActive));
    }



    public WebElement getElementByName(String elementName) {
        switch (elementName) {
            case "Фамилия застрахованных":
                return surname;
            case "Имя застрахованных":
                return name;
            case "Дата рождения застрахованных":
                return birth;
            case "Фамилия страхователя":
                return insurerSurname;
            case "Имя страхователя":
                return insurerName;
            case "Отчество страхователя":
                return insurerSecondName;
            case "Дата рождения страхователя":
                return insurerBirth;
            case "Серия":
                return insurerPassSeries;
            case "Номер":
                return insurerPassNo;
            case "Дата выдачи":
                return insurerPassIssueDate;
            case "Кем выдан":
                return insurerPassWhoIssue;
            default:
                throw new AssertionError("Элемента с именем '" + name + "' не существует");
        }
    }

    /**
     * получить данные из заполненного поля застрахованных
     * @param fieldName
     * @return
     */
    public String getFillInsuredField(String fieldName){
        switch (fieldName){
            case  "Фамилия":
                return surname.getAttribute("value");
            case  "Имя":
                return name.getAttribute("value");
            case  "Дата рождения":
                return birth.getAttribute("value");
        }
        throw new AssertionError("Поле не объявлено на странице");
    }

    /**
     * получить данные из заполненного поля страхователя
     * @param fieldName
     * @return
     */
    public String getFillInsurerField(String fieldName){
        switch (fieldName){
            case  "Фамилия":
                return insurerSurname.getAttribute("value");
            case  "Имя":
                return insurerName.getAttribute("value");
            case  "Отчество":
                return insurerSecondName.getAttribute("value");
            case  "Дата рождения":
                return insurerBirth.getAttribute("value");
        }
        throw new AssertionError("Поле не объявлено на странице");
    }

    /**
     * получить данные из заполненного поля паспортных данных страхователя
     * @param fieldName
     * @return
     */
    public String getFillInsurerPassField(String fieldName){
        switch (fieldName){
            case  "Серия":
                return insurerPassSeries.getAttribute("value");
            case  "Номер":
                return insurerPassNo.getAttribute("value");
            case  "Дата выдачи":
                return insurerPassIssueDate.getAttribute("value");
            case  "Кем выдан":
                return insurerPassWhoIssue.getAttribute("value");
        }
        throw new AssertionError("Поле не объявлено на странице");
    }

    /**
     * заполнить данные застрахованных
     *
     * @param fieldName
     * @param value
     */
    public void fillInsuredFields(String fieldName, String value) {
        switch (fieldName) {
            case "Фамилия":
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", surname);
                fillField(surname, value);
                break;
            case "Имя":
                fillField(name, value);
                break;
            case "Дата рождения":
                fillField(birth, value);
                break;
            default:
                throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }
    }

    /**
     * заполнить данные страхователя
     *
     * @param fieldName
     * @param value
     */
    public void fillInsurerMainFields(String fieldName, String value) {
        switch (fieldName) {
            case "Фамилия":
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", insurerSurname);
                fillField(insurerSurname, value);
                break;
            case "Имя":
                fillField(insurerName, value);
                break;
            case "Отчество":
                fillField(insurerSecondName, value);
                break;
            case "Дата рождения":
                fillField(insurerBirth, value);
                insurerBirth.sendKeys(Keys.TAB);
                break;
            case "Пол":
                insurerSexFemale.click();
                break;
            default:
                throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }
    }

    /**
     * заполнить данные паспорта страхователя
     *
     * @param fieldName
     * @param value
     */
    public void fillInsurerPassFields(String fieldName, String value) {
        switch (fieldName) {
            case "Серия":
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", insurerPassSeries);
                fillField(insurerPassSeries, value);
                break;
            case "Номер":
                fillField(insurerPassNo, value);
                break;
            case "Дата выдачи":
                fillField(insurerPassIssueDate, value);
                insurerPassIssueDate.sendKeys(Keys.ENTER);
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                break;
            case "Кем выдан":
                fillField(insurerPassWhoIssue, value);
                break;
            default:
                throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }
    }

    /**
     * отправить форму
     */
    public void clickSubmitButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", submitButton
        );
        submitButton.click();
    }

    /**
     * Проверка сообщения об ошибке - и кол-во уведомлений по незаполненным полям
     */
    public void checkIfAlertsPresent(int emptyFieldsWithAlerts) {
        Assert.assertEquals("При заполнении данных произошла ошибка", alertBlock.getText());
        assert errorFields.size() == emptyFieldsWithAlerts;
    }
}
