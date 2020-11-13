package pages;

import org.openqa.selenium.*;

import static org.junit.Assert.assertEquals;


public class SberBasePage {
    WebDriver driver;

    //    public boolean isElementPresent(By by) {
//        try {
//            driver.findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//
    //метод для заполнения полей
    public void fillField(WebElement locator, String value) {
        locator.clear();
        locator.sendKeys(value, Keys.ENTER);
    }

    public void checkFillField(String value, WebElement element) {
        assertEquals(value, element.getAttribute("value"));
    }

}