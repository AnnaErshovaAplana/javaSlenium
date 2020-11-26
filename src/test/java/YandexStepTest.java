import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.YandexBasePage;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Title;
import steps.*;

public class YandexStepTest extends YandexBaseSteps {

    static YandexBasePage yandexBasePage = new YandexBasePage();
    static YandexMainPageSteps yandexMainPageSteps = new YandexMainPageSteps();
    YandexMarketPageSteps yandexMarketPageSteps = new YandexMarketPageSteps();
    YandexElectronicsPageSteps yandexElectronicsPageSteps = new YandexElectronicsPageSteps();
    YandexTVPageSteps yandexTVPageSteps = new YandexTVPageSteps();
    YandexHeadphonesPageSteps yandexHeadphonesPageSteps = new YandexHeadphonesPageSteps();

    String[] makersListForTVsTest = {"Samsung", "LG"};
    String[] makersListForHeadphonesTest = {"Beats"};

    String FirstTvItem;
    String FirstHeadphonesItem;

    @Title("Яндекс. Сценарий №1. Телевизоры")
    @Test
    public void YandexTVsTest() {

//        Сценарий №1
// 1. Зайти на yandex.ru.
        driver.get(startPage);
//        2. Перейти в яндекс маркет
        yandexMainPageSteps.selectService("Маркет");
        yandexBasePage.switchToWindow(1);
//        3. Выбрать раздел Электроника
        yandexMarketPageSteps.selectSection("Электроника");
//        4. Выбрать раздел Наушники
        yandexElectronicsPageSteps.selectProductCategory("Телевизоры");
//        5. Зайти в расширенный поиск
//        6. Задать параметр поиска от 500000 рублей.
        yandexMarketPageSteps.enterFromPrice("500000");
//        7. Выбрать производителей Beats
        yandexTVPageSteps.enterTVManufacturer(makersListForTVsTest);
//        8. Проверить, что элементов на странице 12.
        yandexMarketPageSteps.checkItemsInResult(12);
//        9. Запомнить первый элемент в списке.
        FirstTvItem = yandexMarketPageSteps.getFirstItemFromResult();
//        10. В поисковую строку ввести запомненное значение.
        yandexMarketPageSteps.searchProduct(FirstTvItem);
//        11. Найти и проверить, что наименование товара соответствует запомненному значению.
        yandexMarketPageSteps.checkSearchProductResult(FirstTvItem);
    }

    @Ignore
    @Title("Яндекс. Сценарий №2. Наушники")
    @Test
    public void YandexHeadphonesTest() {

//        Сценарий №2
// 1. Зайти на yandex.ru.
        driver.get(startPage);
//        2. Перейти в яндекс маркет
        yandexMainPageSteps.selectService("Маркет");
        yandexBasePage.switchToWindow(1);
//        3. Выбрать раздел Электроника
        yandexMarketPageSteps.selectSection("Электроника");
//        4. Выбрать раздел Телевизоры
        yandexElectronicsPageSteps.selectProductCategory("Наушники");
//        5. Зайти в расширенный поиск
//        6. Задать параметр поиска от 10000 рублей.
        yandexMarketPageSteps.enterFromPrice("10000");
//        7. Выбрать производителей Beats
        yandexHeadphonesPageSteps.enterHeadphonesManufacturer(makersListForHeadphonesTest);
//        8. Проверить, что элементов на странице 11.
        yandexMarketPageSteps.checkItemsInResult(11);
//        9. Запомнить первый элемент в списке.
        FirstHeadphonesItem = yandexMarketPageSteps.getFirstItemFromResult();
//        10. В поисковую строку ввести запомненное значение.
        yandexMarketPageSteps.searchProduct(FirstHeadphonesItem);
//        11. Найти и проверить, что наименование товара соответствует запомненному значению.
        yandexMarketPageSteps.checkSearchProductResult(FirstHeadphonesItem);
    }
}
