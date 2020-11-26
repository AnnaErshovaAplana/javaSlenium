package steps;

import pages.YandexMarketPage;
import ru.yandex.qatools.allure.annotations.Step;

public class YandexMarketPageSteps {

    @Step("Выбран раздел {0}")
    public void selectSection(String name) {
        new YandexMarketPage().chooseMarketSection(name);
    }

    @Step("При поиске товаров указана цена ОТ {0}")
    public void enterFromPrice(String amount) {
        new YandexMarketPage().setFromPrice(amount);
    }

    @Step("Количество товаров по результатам поиска: {0}")
    public void checkItemsInResult(int number) {
        new YandexMarketPage().checkArticlesFilterResults(number);
    }

    @Step("Получаем 1 товар из списка результатов поиска")
    public String getFirstItemFromResult() {
        return new YandexMarketPage().getFirstArticleTitle();
    }

    @Step("Ищем конкретный товар в строке поиска: {0}")
    public void searchProduct(String title) {
        new YandexMarketPage().searchProductByTitle(title);
    }

    @Step("Найденный товар соответсвует первому товару предыдущего результата поиска")
    public void checkSearchProductResult(String title) {
        new YandexMarketPage().checkSearchedArticleTitle(title);
    }

}
