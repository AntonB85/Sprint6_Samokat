package yandex.praktikum.Samokat.PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
     // Адрес сайта
        private final String site = "https://qa-scooter.praktikum-services.ru/";
     // Яндекс логотип
        public static final By yandexLogo = By.className("Header_LogoYandex__3TSOI");
     // Заказать кнопка (верхн.)
        public static final By upOrderButton =  By.xpath("//button[@class='Button_Button__ra12g' and contains(text(), 'Заказать')]");
     // Статус заказа кнопка
        public static final By statusButton = By.className("Header_Link__1TAG7");
     // Заказать кнопка (нижн.)
        public static final By downOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Заказать')]");
     // Вопросы о важном гармошка
        public static final By accordionQuestion = By.className("accordion");

WebDriver driver;
public HomePage(WebDriver driver) {
    this.driver = driver;
}

// АДРЕС САЙТА:
    // Получаем адрес сайта:
    public String getSite() {
        return site;
    }


    // ОЖИДАНИЕ:
    // Ожидаем загрузки главной страницы
    public HomePage loadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(yandexLogo));
        return this;
    }

    // Ожидаем загрузки ответа на вопрос
    public HomePage waitLoadQuestion() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(accordionQuestion));
        return this;
    }


// НАЖАТИЕ:
    // Нажимаем на один из вопросов аккордиона
    public HomePage clickAccordionHeading(String headingId) {
        WebElement headingElement = driver.findElement(By.id(headingId));
        headingElement.click();
        return this;
    }

    // Нажимаем верхнюю кнопку "Заказать"
    public HomePage clickUpOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(upOrderButton));
        driver.findElement(upOrderButton).click();
        return this;
    }

    // Нажимаем нижнюю кнопку "Заказать"
    public HomePage clickDownOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(downOrderButton));
        driver.findElement(downOrderButton).click();
        return this;
    }

// Нажимаем кнопку "Статус заказа"
public HomePage clickOrderState() {
    driver.findElement(statusButton).click();
    return this;
}

    // Нажимаем логотип Яндекса
    public void clickYandexButton() {
    driver.findElement(yandexLogo).click();
}

// ПРОКРУТКА:
    // Переходим к блоку "Вопросы о важном"
    public HomePage scrollToQuestions(String headingId) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
            driver.findElement(By.id(headingId)));
        return this;
    }

    // Переходим к нижней кнопке "Заказать"
    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

// ПОЛУЧЕНИЕ ТЕКСТА:
    // Получаем текст ответа на вопрос
    public String getAccordionText(String cssSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        return textElement.getText();
    }
}