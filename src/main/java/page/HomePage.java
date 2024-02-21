package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final By buttonSignIn = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By buttonPersonalAccount = By.xpath(".//a/p[text() = 'Личный Кабинет']");
    private final By fieldBuns = By.xpath(".//span[text()='Булки']");
    private final By fieldSauce = By.xpath(".//div/span[text() = 'Соусы']");
    private final By fieldFilling = By.xpath(".//div/span[text() = 'Начинки']");
    private final By selectedFieldBuns = By.xpath(".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']/p[text()='Флюоресцентная булка R2-D3']");
    private final By selectedFieldSauce = By.xpath(".//a/p[text()='Соус Spicy-X']");
    private final By selectedFieldFilling = By.xpath(".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']/p[text()='Мясо бессмертных моллюсков Protostomia']");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    @Step("Клик по кнопке Личный Кабинет")
    public void clickButtonPersonalAccount() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(buttonPersonalAccount));
        driver.findElement(buttonPersonalAccount).click();
    }
    @Step("Клик по разделу Булочки")
    public String clickFieldBuns() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fieldSauce));
        driver.findElement(fieldSauce).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fieldBuns));
        driver.findElement(fieldBuns).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectedFieldBuns));
        return driver.findElement(selectedFieldBuns).getText();
    }
    @Step("Клик по разделу Соусы")
    public String clickFieldSauce() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fieldSauce));
        driver.findElement(fieldSauce).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectedFieldSauce));
        return driver.findElement(selectedFieldSauce).getText();
    }
    @Step("Клик по разделу Начинка")
    public String clickFieldFilling() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(fieldFilling));
        driver.findElement(fieldFilling).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(selectedFieldFilling));
        return driver.findElement(selectedFieldFilling).getText();
    }
    @Step("Получить текст с кнопки Булочки")
    public String findBunButtonText() {
        return driver.findElement(fieldBuns).getText();
    }
}
