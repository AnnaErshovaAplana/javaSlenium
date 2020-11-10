package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import java.util.concurrent.TimeUnit;


public class SberMainPage extends SberBasePage {

    public SberMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void selectInBottomMenu(String menuItem) {
        WebElement bottomMenuItem = driver.findElement(By.xpath("//nav[@class='kitt-service-menu']//a[contains(text(),\"" + menuItem + "\")]"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", bottomMenuItem
        );
        bottomMenuItem.click();
    }
}
