package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.YandexBaseSteps;

import java.util.concurrent.TimeUnit;

public class YandexMainPage extends YandexBasePage {

    @FindBy(xpath = "//input[@id='text']")
    WebElement searchField;

    @FindBy(xpath = "//a[@data-id='market']")
    WebElement marketButton;

    //Get the current window handle
    String mainWindow;

    public YandexMainPage() {
        PageFactory.initElements(YandexBaseSteps.getDriver(), this);
        YandexBaseSteps.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    public void goToService(String serviceName) {
        mainWindow = YandexBaseSteps.getDriver().getWindowHandle();
        switch (serviceName) {
            case "Маркет":
                marketButton.click();
                break;
            default:
                throw new AssertionError("Сервис не объявлен на странице");
        }
    }

}
