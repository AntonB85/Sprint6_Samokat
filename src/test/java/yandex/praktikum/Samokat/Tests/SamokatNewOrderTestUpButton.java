package yandex.praktikum.Samokat.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import yandex.praktikum.Samokat.PageObject.RenterPage;
import yandex.praktikum.Samokat.PageObject.HomePage;
import yandex.praktikum.Samokat.PageObject.ScooterPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SamokatNewOrderTestUpButton {
    private WebDriver driver;
    private HomePage homePage;
    private RenterPage renterPage;
    private ScooterPage scooterPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions(); //При необходимости заменить на Firefox
        options.addArguments(
                "--no-sandbox",
                "--headless",
                "--disable-dev-shm-usage"
        );
        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
        renterPage = new RenterPage(driver);
        scooterPage = new ScooterPage(driver);
        String siteUrl = homePage.getSite(); // Получаем адрес сайта из HomePage
        driver.get(siteUrl); // Открываем страницу с помощью WebDriver
    }

    @ParameterizedTest
    @MethodSource("yandex.praktikum.Samokat.Tests.TestData#newOrder")
    public void testNewOrder(
            String userName,
            String userSurname,
            String userAddress,
            int stateNumber,
            String userPhone,
            String deliveryDate,
            String colour,
            String newComment
    ) {
        homePage
                .loadHomePage()
                .clickUpOrderButton();
        renterPage
                .inputName(userName)
                .inputSurname(userSurname)
                .inputAddress(userAddress)
                .inputMetro(stateNumber)
                .inputPhone(userPhone)
                .clickNextButton();
        scooterPage
                .inputDate(deliveryDate)
                .inputTime()
                .inputColour(colour)
                .inputComment(newComment)
                .clickOrderButton()
                .clickYesButton();
        String actualText = scooterPage.successNotificationText(); // Получаем текст ответа
        assertTrue(actualText.startsWith("Заказ оформлен"), "Текст уведомления не соответствует ожидаемому"); // Сверяем ожидаемый и полученный текст
        System.out.println("Заказ оформлен успешно"); // Выводим полученный текст ответа. Пусть пока будет
    }

        @AfterEach
        void tearDown() {
            driver.quit();
        }
    }