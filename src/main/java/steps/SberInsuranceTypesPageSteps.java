package steps;

import org.junit.Assert;
import pages.SberInsuranceTypesPage;
import ru.yandex.qatools.allure.annotations.Step;

import static org.junit.Assert.assertTrue;

public class SberInsuranceTypesPageSteps extends SberBaseSteps{

    @Step("Получен заголовок блока с типом страхования {0}")
    public String getInsuranceTypeVisibleName(String blockName) {
        return new SberInsuranceTypesPage(driver).getInsuranceTypeVisibleName(blockName);
    }

    @Step("\"Оформить онлайн\" страхование типа: Страхование для путешественников")
    public void clickGetOnlineButtonForInsuranceType() {
        new SberInsuranceTypesPage(driver).clickGetOnlineButtonForTravelInsuranceType();
    }

    @Step("заголовок страницы - Отправить заявку равен {0}")
    public void checkPageTitle(String expectedTitle){
        String TravellersInsuranceBlockHeader = new SberInsuranceTypesPage(driver).getInsuranceTypeVisibleName("Страхование для путешественников");
        Assert.assertEquals("Заголовок равен [%s]. Ожидалось - [%s]", expectedTitle, TravellersInsuranceBlockHeader);
    }
}
