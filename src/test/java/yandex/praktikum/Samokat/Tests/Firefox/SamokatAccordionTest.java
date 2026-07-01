package yandex.praktikum.Samokat.Tests.Firefox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import yandex.praktikum.Samokat.PageObject.HomePage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SamokatAccordionTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(
                "--no-sandbox",
                "--headless",
                "--disable-dev-shm-usage"
        );
        driver = new FirefoxDriver(options);
        homePage = new HomePage(driver); // Инициализируем Page Object
        driver.get("https://qa-scooter.education-services.ru/");
    }

    @ParameterizedTest
    @MethodSource("AccordionMenu")
    public void testAccordion(int accordionIndex, String headingId, String textSelector, String expectedText) {
        homePage.loadHomePage().scrollToQuestions(headingId); // Ждем загрузки страницы и крутим к вопросам
        homePage.waitLoadQuestion().clickAccordionHeading(headingId); // Ждем загрузки вопросов и кликаем
        String actualText = homePage.getAccordionText(textSelector); // Получаем текст ответа
        System.out.println("Текст вкладки " + accordionIndex + ": " + actualText); // Выводим полученный текст ответа. Пусть пока будет
        assertEquals(expectedText, actualText, "Текст ответа не соответствует ожидаемому"); // Сверяем ожидаемый и полученный текст
    }

    static Stream<Arguments> AccordionMenu() {
        return Stream.of(
                Arguments.of(0, "accordion__heading-0", "#accordion__panel-0 > p", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of(1, "accordion__heading-1", "#accordion__panel-1 > p", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of(2, "accordion__heading-2", "#accordion__panel-2 > p", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of(3, "accordion__heading-3", "#accordion__panel-3 > p", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of(4, "accordion__heading-4", "#accordion__panel-4 > p", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of(5, "accordion__heading-5", "#accordion__panel-5 > p", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of(6, "accordion__heading-6", "#accordion__panel-6 > p", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of(7, "accordion__heading-7", "#accordion__panel-7 > p", "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}