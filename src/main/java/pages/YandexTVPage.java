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

public class YandexTVPage extends YandexBasePage {

    @FindBy(xpath = "//input[@type='checkbox'][contains(@name,'Производитель LG')]/parent::*/div")
    WebElement LGCheckBox;

    @FindBy(xpath = "//input[@type='checkbox'][contains(@name,'Производитель Samsung')]/parent::*/div")
    WebElement samsungCheckBox;

    Wait<WebDriver> wait = new WebDriverWait(driver, 20, 1000);

    public YandexTVPage() {
        PageFactory.initElements(YandexBaseSteps.getDriver(), this);
        YandexBaseSteps.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }


    public void setTVManufacturer(String[] manufacturers) {
        String[] manufers = manufacturers;
        int allManufacturers = manufacturers.length;
        int i = 0;
        while (i < allManufacturers) {
            String manufacturer = manufers[i];
            switch (manufacturer) {
                case "LG":
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView(true);", LGCheckBox
                    );
                    i += 1;
                    wait.until(ExpectedConditions.visibilityOf(LGCheckBox)).click();
                    continue;
                case "Samsung":
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView(true);", samsungCheckBox
                    );
                    samsungCheckBox.click();
                    i += 1;
                    continue;
                    //wait.until(ExpectedConditions.visibilityOf(samsungCheckBox)).click();
                default:
                    throw new AssertionError("Призводитель не объявлен на странице");
            }
        }
    }
}
