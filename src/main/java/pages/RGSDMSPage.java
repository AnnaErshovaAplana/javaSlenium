package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RGSDMSPage extends RGSBasePage {

    public RGSDMSPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickButton(String buttonName) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),\"" + buttonName + "\")]")))).click();
    }
}
