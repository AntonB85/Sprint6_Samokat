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
import yandex.praktikum.Samokat.PageObject.RenterPage;
import yandex.praktikum.Samokat.PageObject.ScooterPage;

import java.util.stream.Stream;

public class SamokatNewOrderTestDownButton {
    private WebDriver driver;
    private HomePage homePage;
    private RenterPage renterPage;
    private ScooterPage scooterPage;

    @BeforeEach
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(
                "--no-sandbox",
                "--headless",
                "--disable-dev-shm-usage"
        );
        driver = new FirefoxDriver(options);
        homePage = new HomePage(driver);
        renterPage = new RenterPage(driver);
        scooterPage = new ScooterPage(driver);
        driver.get("https://qa-scooter.education-services.ru/");
    }

    @ParameterizedTest
    @MethodSource("newOrder")
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
                .scrollToDownOrderButton()
                .clickDownOrderButton();
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
                .clickYesButton()
                .clickStatusButton();
    }

        static Stream<Arguments> newOrder() {
            return Stream.of(
                    Arguments.of("Аполлинарий", "Дырка", "ул.Кирпичные Выемки, д.14", 1, "81234567890", "31.12.2026", "black", "С перламутровыми пуговицами"),
                    Arguments.of("Иннокентий", "Забейворота", "Бабьегородский переулок, д.41", 2, "81234567890", "31.12.2026", "grey", "+5 к скорости"),
                    Arguments.of("Роман", "Бутылка", "ул.Трудовой Пчелы, д.41", 3, "81234567890", "31.12.2026", "both", "Не могу определиться с цветом"),
                    Arguments.of("Владилена", "Козюлькина", "Улица Жужа, д.22", 4, "81234567890", "31.12.2026", "none", "Лень писать комментарий")
            );
        }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}