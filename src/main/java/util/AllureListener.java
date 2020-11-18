//package util;
////
////import org.junit.runner.notification.Failure;
////import org.openqa.selenium.OutputType;
////import org.openqa.selenium.TakesScreenshot;
////import ru.yandex.qatools.allure.annotations.Attachment;
////import ru.yandex.qatools.allure.junit.AllureRunListener;
////import steps.SberBaseSteps;
////
////
////public class AllureListener extends AllureRunListener {
////
////    @Override
////    public void testFailure(Failure failure) {
////        takeScreenshot();
////        super.testFailure(failure);
////    }
////
////
////    @Attachment(type = "image/png", value = "скриншот при ошибке")
////    public static byte[] takeScreenshot() {
////        return ((TakesScreenshot) SberBaseSteps.getDriver()).getScreenshotAs(OutputType.BYTES);
////    }
////}