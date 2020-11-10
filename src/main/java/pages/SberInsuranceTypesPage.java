package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SberInsuranceTypesPage extends SberBasePage {
    @FindAll(@FindBy(xpath = "//div[contains(@class,'uc-full__item')]"))
    List<WebElement> insuranceTypesBlocks;

    @FindBy(xpath = "//h3[contains(text(),'Страхование для путешественников')]")
    WebElement TravellersInsuranceBlock;

    public SberInsuranceTypesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        this.driver = driver;
    }

    /**
     * @param Type
     * @return Видимое название блока с уазанным типом страхования
     */
    public String getInsuranceTypeVisibleName(String Type) {
        switch (Type) {
            case "Страхование для путешественников":
                return TravellersInsuranceBlock.getText();
//            case  "Название других блоков типов страхования":
//                return blockName.getText();
        }
        throw new AssertionError("Поле не объявлено на странице");
    }

    /**
     * Нажимаем на кнопку "Оформить онлайн" для выбранного типа страхования
     *
     * @param type
     */
    public void clickGetOnlineButtonForInsuranceType(String type) {
        WebElement getOnlineButton = driver.findElement(By.xpath("//h3[contains(text(),\"" + type + "\")]/parent::*/parent::*/parent::*//a/b[contains(text(),'Оформить онлайн')]"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", getOnlineButton
        );
        getOnlineButton.click();
    }
}