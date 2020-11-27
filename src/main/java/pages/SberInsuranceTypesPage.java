package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.SberBaseSteps;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class SberInsuranceTypesPage extends SberBasePage {
    @FindAll(@FindBy(xpath = "//div[contains(@class,'uc-full__item')]"))
    List<WebElement> insuranceTypesBlocks;

    @FindBy(xpath = "//h3[contains(text(),'Страхование для путешественников')]")
    WebElement TravellersInsuranceBlock;

    @FindBy(xpath = "//h3[contains(text(),\"Страхование для путешественников\")]/parent::*/parent::*/parent::*//a/b[contains(text(),'Оформить онлайн')]")
    WebElement insuranceOnlineButton;

    public SberInsuranceTypesPage() {
        PageFactory.initElements(SberBaseSteps.getDriver(), this);
        this.driver = SberBaseSteps.getDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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

    /**
     * Нажимаем на кнопку "Оформить онлайн" для выбранного типа страхования
     */
    public void clickGetOnlineButtonForTravelInsuranceType() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", insuranceOnlineButton
        );
        insuranceOnlineButton.click();
    }
}
