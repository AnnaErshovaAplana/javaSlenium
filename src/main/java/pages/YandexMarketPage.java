package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.YandexBaseSteps;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class YandexMarketPage extends YandexBasePage {

    @FindBy(xpath = "//input[@id='header-search']")
    WebElement searchElectronicsField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchElectronicsButton;

    @FindBy(xpath = "//input[@name='Цена от']")
    WebElement priceFromField;

    @FindBy(xpath = "//article[@data-autotest-id='product-snippet'][1]//h3//a")
    WebElement firstItemFound;

    @FindAll(@FindBy(xpath = "//article[@data-autotest-id='product-snippet']"))
    List<WebElement> foundArticles;

    @FindBy(xpath = "//span[text()='Электроника']")
    WebElement electronicsSection;

    Wait<WebDriver> wait = new WebDriverWait(driver, 20, 1000);

    public YandexMarketPage() {
        PageFactory.initElements(YandexBaseSteps.getDriver(), this);
        YandexBaseSteps.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    public void chooseMarketSection(String sectionName) {
        YandexBaseSteps.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        switch (sectionName) {
            case "Электроника":
                wait.until(ExpectedConditions.visibilityOf(electronicsSection)).click();
                break;
            default:
                throw new AssertionError("Раздел не объявлен на странице");
        }
    }

    public void setFromPrice(String price) {
        YandexBaseSteps.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", priceFromField
        );
        wait.until(ExpectedConditions.visibilityOf(priceFromField)).sendKeys(price);
    }

    public String getFirstArticleTitle() {
        YandexBaseSteps.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return firstItemFound.getAttribute("title");
    }

    public void searchProductByTitle(String title) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", searchElectronicsField
        );
        wait.until(ExpectedConditions.visibilityOf(searchElectronicsField)).sendKeys(title);
        searchElectronicsButton.click();
    }

    public void checkSearchedArticleTitle(String expected) {
        YandexBaseSteps.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String result = getFirstArticleTitle();
        assertEquals("Найденный товар ("+result+") не соответсвует первому товару из списка, полученного с использованием фильтра ("+expected+")", expected, result);
    }


    public void checkArticlesFilterResults(int expected) {
        YandexBaseSteps.getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(foundArticles)));
        int result = foundArticles.size();
        assertTrue("Количество найденнноых товаров ("+result+") не соответствует ожидаемому ("+expected+")",expected==result);
    }
}
