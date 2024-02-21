package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegPage {
    private final WebDriver driver;
    private final By fieldName = By.xpath(".//fieldset[1]//div/input");
    private final By fieldEmail = By.xpath(".//fieldset[2]//div/input");
    private final By fieldPassword = By.xpath(".//div/input[@name = 'Пароль']");
    private final By buttonRegister = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By buttonSignIn = By.xpath(".//p/a[text() = 'Войти']");
    private final By incorrectPassword = By.xpath(".//p[text() = 'Некорректный пароль']");
    public RegPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Ввод данных в поле Имя")
    public void setFieldName(String name) {
        driver.findElement(fieldName).click();
        driver.findElement(fieldName).sendKeys(name);
    }
    @Step("Ввод данных в поле Почта")
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
    }
    @Step("Ввод данных в поле Пароль")
    public void setFieldPassword(String password) {
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
    }
    @Step("Клик по кнопке Зарегистрироваться")
    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }
    @Step("Клик по кнопке Вход")
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    @Step("Ожидание загрузки страницы регистации")
    public void waitForLoadingPage() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(buttonRegister));
    }
    @Step("Регистрация уникального пользователя")
    public void registerNewUser(String name, String email, String password) {
        waitForLoadingPage();
        setFieldName(name);
        setFieldEmail(email);
        setFieldPassword(password);
        clickButtonRegister();
    }
    @Step("Получить текст с кнопки Вход")
    public String findTextEnter() {
        return driver.findElement(buttonSignIn).getText();
    }
    @Step("Получить текст Некорректный пароль")
    public String findTextIncorrectPassword() {
        return driver.findElement(incorrectPassword).getText();
    }
}
