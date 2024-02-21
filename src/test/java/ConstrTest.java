import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.HomePage;

import static org.hamcrest.CoreMatchers.is;

public class ConstrTest {
    private WebDriver driver;
    private final WebDriverFactory driverFactory = new WebDriverFactory();
    @Before
    public void setUp() {
        driver = driverFactory.get();
    }
    @After
    @Step("Удаление тестовых данных")
    public void deleteUser() {
        driver.quit();
    }
    @Test
    @DisplayName("Клик по разделу Булки открывает список булочек")
    public void bunTest() {
        HomePage homePage = new HomePage(driver);
        String result = homePage.clickFieldBuns();
        MatcherAssert.assertThat(result, is("Флюоресцентная булка R2-D3"));
    }
    @Test
    @DisplayName("Клик по разделу Соусы открывает список соусов")
    public void sauceTest() {
        HomePage homePage = new HomePage(driver);
        String result = homePage.clickFieldSauce();
        MatcherAssert.assertThat(result, is("Соус Spicy-X"));
    }
    @Test
    @DisplayName("Клик по разделу Начинки открывает список начинок")
    public void fillingTest() {
        HomePage homePage = new HomePage(driver);
        String result = homePage.clickFieldFilling();
        MatcherAssert.assertThat(result, is("Мясо бессмертных моллюсков Protostomia"));
    }
 }