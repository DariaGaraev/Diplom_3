package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgetPage {
    private final WebDriver driver;
    private final By fieldEmail = By.xpath(".//label[text() = 'Email']");
    private final By buttonRestore = By.xpath(".//button[text() = 'Восстановить']");
    private final By buttonSignIn = By.xpath(".//div/p/a[text() = 'Войти']");
    public ForgetPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Отредактировать данные в поле Пароль")
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }
    @Step("Восстановить пароль")
    public void clickButtonRestore() {
        driver.findElement(buttonRestore).click();
    }
    @Step("Вход")
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    @Step("Ожидание загрузки страницы")
    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonRestore));
    }
}
