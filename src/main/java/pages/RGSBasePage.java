package pages;

import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maria on 07.09.2017.
 */
public class RGSBasePage {
    WebDriver driver;

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //метод для заполнения полей
    public void fillField(WebElement locator, String value) {
        locator.clear();
        locator.sendKeys(value, Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void checkFillField(String value, WebElement element) {
        assertEquals(value, element.getAttribute("value"));
    }
}