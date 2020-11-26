package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import steps.YandexBaseSteps;

import java.util.ArrayList;


public class YandexBasePage {

    WebDriver driver = YandexBaseSteps.getDriver();

    public YandexBasePage() {
        PageFactory.initElements(driver, this);
    }


    public void switchToWindow(int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println(tabs.size());
        //Use the list of window handles to switch between windows
        driver.switchTo().window(tabs.get(index));
    }

}
