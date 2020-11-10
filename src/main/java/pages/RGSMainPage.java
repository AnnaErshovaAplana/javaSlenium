package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RGSMainPage extends RGSBasePage {
    @FindBy(xpath = "//a[contains(@data-toggle,'dropdown')][contains(text(),'Меню')]")
    WebElement mainMenu;

    public RGSMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void selectInMenu(String menuItem) {
        mainMenu.click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li/a[contains(text(),\"" + menuItem + "\")]")))).click();
    }

}
