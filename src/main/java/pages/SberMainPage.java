package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.concurrent.TimeUnit;


public class SberMainPage extends SberBasePage {

    @FindBy(xpath = "//nav[@class='kitt-service-menu']//a[contains(text(),\"Страхование\")]")
    WebElement insuranceItem;

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    WebElement cookieWarningButton;


    @FindBy(xpath = "//div[contains(@class,'kitt-cookie-warning')][contains(@style,'display')]")
    WebElement cookieWarningBlock;

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
    public void selectInsuranceBottomMenu() {
        if(cookieWarningBlock.getAttribute("style").equals("display: block;")){
            cookieWarningButton.click();
        }
        ((JavascriptExecutor) this.driver).executeScript(
                "arguments[0].scrollIntoView(true);", insuranceItem
        );
        insuranceItem.click();
    }
}
