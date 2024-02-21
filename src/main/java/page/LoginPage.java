package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final By fieldEmail = By.xpath(".//div/input[@name = 'name']");
    private final By fieldPassword = By.xpath(".//div/input[@name = 'Пароль']");
    private final By buttonSignIn = By.xpath(".//button[text() = 'Войти']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Взаимодействие с e-mail")
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
    }
    @Step("Типы ввода пароля")
    public void setFieldPassword(String password) {
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
    }
    @Step("Клик по кнопке Войти")
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    @Step("Типы ожидания загрузки страницы")
    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn));
    }
    @Step("Взаимодействие с системой")
    public void login(String email, String password) {
        waitLoadPage();
        setFieldEmail(email);
        setFieldPassword(password);
        clickButtonSignIn();
    }
}