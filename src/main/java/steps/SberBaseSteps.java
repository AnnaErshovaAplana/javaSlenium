package steps;
import java.util.Properties;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import util.TestProperties;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SberBaseSteps {

    public static WebDriver getDriver(){
        return driver;
    }
    protected static WebDriver driver;
    protected static String baseUrl;
    protected static String TravelInsuranceUrl;
    // переменная для считывания properties
    public static Properties properties = TestProperties.getInstance().getProperties();


    @BeforeClass
    public static void SetUp() throws Exception {
        // из файла properties считываем переменную browser для уточнения, какой использовать браузер
        switch (properties.getProperty("browser")) {
            case "firefox":
                // устанавливаем путь к драйверу в системных свойствах и определяем переменную драйвера
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                // устанавливаем путь к драйверу в системных свойствах и определяем переменную драйвера
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            default:
                // устанавливаем путь к драйверу в системных свойствах и определяем переменную драйвера
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
        }

        // записываем значения в переменные ссылок
        baseUrl = properties.getProperty("baseUrl");
        TravelInsuranceUrl = properties.getProperty("TravelInsuranceUrl");
        System.out.println("Ссылка на вебстраницу для тестов РГС " + baseUrl);
        System.out.println("Ссылка на вебстраницу для тестов СберСтрах " + TravelInsuranceUrl);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

}
