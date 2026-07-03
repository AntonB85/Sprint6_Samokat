package yandex.praktikum.Samokat.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RenterPage {
    // Имя текстовое поле
    public static final By name = By.cssSelector("input[placeholder='* Имя']");
    // Фамилия текстовое поле
    public static final By surname = By.cssSelector("input[placeholder='* Фамилия']");
    // Адрес текстовое поле
    public static final By address = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    // Станция метро выпадающий список
    private final By stateMetro = By.className("select-search__input");
    // Выбор станции метро из списка
    private final String nameStateMetro = ".//button[@value='%s']";
    // Телефон текстовое поле
    public static final By phone = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    // Далее кнопка
    public static final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");

    WebDriver driver;

    public RenterPage(WebDriver driver) {
        this.driver = driver;
    }


// ЗАПОЛНЕНИЕ:
    // Заполняем имя заказчика
    public RenterPage inputName(String userName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(name));
        driver.findElement(name).clear();
        driver.findElement(name).sendKeys(userName);
        return this; // Добавляем возврат текущего объекта
    }

    // Заполняем фамилию заказчика
    public RenterPage inputSurname(String userSurname) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(surname));
        driver.findElement(surname).clear();
        driver.findElement(surname).sendKeys(userSurname);
        return this; // Добавляем возврат текущего объекта
    }

    // Заполняем адрес заказчика
    public RenterPage inputAddress(String userAddress) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(address));
        driver.findElement(address).clear();
        driver.findElement(address).sendKeys(userAddress);
        return this; // Добавляем возврат текущего объекта
    }

    // Заполняем станцию метро
    public RenterPage inputMetro(int stateNumber) {
        driver.findElement(stateMetro).click();
        By newStateMetro = By.xpath(String.format(nameStateMetro, stateNumber));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(newStateMetro));
        wait.until(ExpectedConditions.elementToBeClickable(newStateMetro));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(newStateMetro));
        driver.findElement(newStateMetro).click();
        return this;
    }

    // Заполняем телефон заказчика
    public RenterPage inputPhone(String userPhone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(phone));
        driver.findElement(phone).clear();
        driver.findElement(phone).sendKeys(userPhone);
        return this; // Добавляем возврат текущего объекта
    }

// НАЖАТИЕ:

    // Нажимаем кнопку Далее
    public RenterPage clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonNext));
        driver.findElement(buttonNext).click();
        return this;
    }

}