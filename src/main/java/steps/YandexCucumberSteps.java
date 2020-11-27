package steps;

import cucumber.api.java.en.When;
import pages.YandexBasePage;

public class YandexCucumberSteps {

    YandexBasePage yandexBasePage = new YandexBasePage();
    YandexMainPageSteps yandexMainPageSteps = new YandexMainPageSteps();
    YandexMarketPageSteps yandexMarketPageSteps = new YandexMarketPageSteps();
    YandexElectronicsPageSteps yandexElectronicsPageSteps = new YandexElectronicsPageSteps();
    YandexTVPageSteps yandexTVPageSteps = new YandexTVPageSteps();
    YandexHeadphonesPageSteps yandexHeadphonesPageSteps = new YandexHeadphonesPageSteps();

    String FirstHeadphonesItem;
    String FirstTvItem;

    @When("^Перейти в Яндекс \"(.*)\"$")
    public void chooseService(String name) {
        yandexMainPageSteps.selectService(name);
        yandexBasePage.switchToWindow(1);
    }

    @When("^Выбрать раздел \"(.*)\"$")
    public void getSection(String name) {
        yandexMarketPageSteps.selectSection(name);
    }

    @When("^Выбрать категорию товара \"(.*)\"$")
    public void getProductCategory(String name) {
        yandexElectronicsPageSteps.selectProductCategory(name);
    }

    @When("^Задать параметр поиска 'Цена ОТ' \"(.*)\"$")
    public void setFromPrice(String price) {
        yandexMarketPageSteps.enterFromPrice(price);
    }

    @When("^Выбрать производителей телевизоров \"(.*)\"$")
    public void setTVManufacturers(String makersList){
        String[] makers = makersList.split(",");
        yandexTVPageSteps.enterTVManufacturer(makers);
    }

    @When("^Выбрать производителей наушников \"(.*)\"$")
    public void setHPhManufacturers(String makersList) {
        String[] makers = makersList.split(",");
        yandexHeadphonesPageSteps.enterHeadphonesManufacturer(makers);
    }

    @When("^Количество найденных товаров на странице равно \"(.*)\"$")
    public void checkResult(int exp) {
        yandexMarketPageSteps.checkItemsInResult(exp);
    }

    @When("Запомнили элемент в списке найденных наушников")
    public void getFirstHPh() {
        FirstHeadphonesItem = yandexMarketPageSteps.getFirstItemFromResult();
    }

    @When("Запомнили элемент в списке найденных телевизоров")
    public void getFirstTV() {
        FirstTvItem = yandexMarketPageSteps.getFirstItemFromResult();
    }

    @When("Ищем телевизор по сохраненному названию")
    public void searchTV() {
        yandexMarketPageSteps.searchProduct(FirstTvItem);
    }

    @When("Ищем наушники по сохраненному названию")
    public void searchHPh() {
        yandexMarketPageSteps.searchProduct(FirstHeadphonesItem);
    }

    @When("Найденные наушники соответствует запомненному значению первой позиции из результатов поиска")
    public void checkFoundHPh() {
        yandexMarketPageSteps.checkSearchProductResult(FirstHeadphonesItem);
    }

    @When("Найденный телевизор соответствует запомненному значению первой позиции из результатов поиска")
    public void checkFoundTV() {
        yandexMarketPageSteps.checkSearchProductResult(FirstTvItem);
    }

}

