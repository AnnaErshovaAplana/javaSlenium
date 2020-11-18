package util;

import gherkin.formatter.model.Result;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;
import steps.SberBaseSteps;
import ru.yandex.qatools.allure.cucumberjvm.AllureReporter;


public class AllureReport extends AllureReporter {

    @Override
    public void result(Result result) {
        if ("failed".equals(result.getStatus())) {
            takeScreenshot();
        }
        super.result(result);
    }

    @Attachment(type = "image/png", value = "скриншот при ошибке")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) SberBaseSteps.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}