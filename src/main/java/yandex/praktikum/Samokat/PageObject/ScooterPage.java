package yandex.praktikum.Samokat.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScooterPage {
    // Дата доставки текстовое поле / календарь
    public static final By date = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // Срок аренды стрелка
    public static final By timeArrow = By.xpath(".//span[@class='Dropdown-arrow']");
    // Цвет черный чекбокс
    public static final By firstCheckbox = By.id("black");
    // Цвет серый чекбокс
    public static final By secondCheckbox = By.id("grey");
    // Комментарий текстовое поле
    public static final By comment = By.cssSelector("input[placeholder='Комментарий для курьера']");
    // Назад кнопка
    public static final By buttonBack = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
    // Заказать кнопка
    public static final By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    // Да кнопка
    public static final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    // Нет кнопка
    public static final By buttonNo = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Нет']");
    // Заказ оформлен уведомление
    public static final By successNotification = By.className("Order_ModalHeader__3FDaJ");

    WebDriver driver;
    public ScooterPage(WebDriver driver) {
        this.driver = driver;
    }

// ЗАПОЛНЕНИЕ:
    // Заполняем дату доставки
    public ScooterPage inputDate(String deliveryDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(date));
        driver.findElement(date).clear();
        driver.findElement(date).sendKeys(deliveryDate);
        return this; // Добавляем возврат текущего объекта
    }

    // Заполняем срок аренды
    public ScooterPage inputTime() {
        driver.findElement(timeArrow).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu")))
                .click();
        return this;
    }

    //Заполняем чек-боксы выбора цвета
    public ScooterPage inputColour(String colour) {
        if (colour.equals("black")) {
            driver.findElement(firstCheckbox).click();
        } else if (colour.equals("grey")) {
            driver.findElement(secondCheckbox).click();
        } else if (colour.equals("both")) {
            driver.findElement(firstCheckbox).click();
            driver.findElement(secondCheckbox).click();
        } else if (colour.equals("none")) {
            // Ничего не делаем — оставляем чекбоксы сброшенными
        }
        return this;
    }

    public ScooterPage inputComment(String newComment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(comment));
        driver.findElement(comment).clear();
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

// НАЖАТИЕ:
    // Нажимаем кнопку Назад
    public ScooterPage clickBackButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonBack));
        driver.findElement(buttonBack).click();
        return this;
    }

    // Нажимаем кнопку Заказать
    public ScooterPage clickOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonOrder));
        driver.findElement(buttonOrder).click();
        return this;
    }

// ДИАЛОГОВЫЕ ОКНА
    // Нажимаем кнопку Да
    public ScooterPage clickYesButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(buttonYes));
        driver.findElement(buttonYes).click();
        return this;
    }

    // Нажимаем кнопку Нет
    public ScooterPage clickNoButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonNo));
        driver.findElement(buttonNo).click();
        return this;
    }

// ПОЛУЧЕНИЕ ТЕКСТА:
    // Получаем текст уведомления об успешном оформлении заказа
    public String successNotificationText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification));
        return textElement.getText();
    }

}