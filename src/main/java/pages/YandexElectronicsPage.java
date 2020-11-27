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

public class YandexElectronicsPage extends YandexBasePage {

    @FindBy(xpath = "//a[text()='Телевизоры']")
    WebElement tvCategory;

    @FindBy(xpath = "//div/a[text()='Наушники и Bluetooth-гарнитуры'][contains(@class,'SvBTI5gwNn')]")
    WebElement HeadphonesCategory;

    Wait<WebDriver> wait = new WebDriverWait(driver, 30, 5000);

    public YandexElectronicsPage() {
        PageFactory.initElements(YandexBaseSteps.getDriver(), this);
        YandexBaseSteps.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    public void chooseElectronicsCategory(String categoryName) {
        YandexBaseSteps.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        switch (categoryName) {
            case "Телевизоры":
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", tvCategory
                );
                wait.until(ExpectedConditions.visibilityOf(tvCategory)).click();
                break;
            case "Наушники":
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", HeadphonesCategory
                );
                wait.until(ExpectedConditions.visibilityOf(HeadphonesCategory)).click();
                break;
            default:
                throw new AssertionError("Категория не объявлена на странице");
        }
    }
}
