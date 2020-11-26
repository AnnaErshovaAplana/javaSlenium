package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.YandexBaseSteps;

import java.util.concurrent.TimeUnit;

public class YandexHeadphonesPage extends YandexBasePage {

    @FindBy(xpath = "//input[@type='checkbox'][contains(@name,'Производитель Beats')]/parent::*/div")
    WebElement BeatsCheckBox;

    Wait<WebDriver> wait = new WebDriverWait(driver, 20, 1000);

    public YandexHeadphonesPage() {
        PageFactory.initElements(YandexBaseSteps.getDriver(), this);
        YandexBaseSteps.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    public void setHeadphonesManufacturer(String[] manufacturers) {
        String[] manufers = manufacturers;
        int allManufacturers = manufacturers.length;
        int i = 0;
        while (i < allManufacturers) {
            String manufacturer = manufers[i];
            switch (manufacturer) {
                case "Beats":
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView(true);", BeatsCheckBox
                    );
                    i += 1;
                    wait.until(ExpectedConditions.visibilityOf(BeatsCheckBox)).click();
                    continue;
                default:
                    throw new AssertionError("Призводитель не объявлен на странице");
            }
        }
    }
}
