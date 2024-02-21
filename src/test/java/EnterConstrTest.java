import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.HomePage;
import page.ProfilePage;
import user.UserClient;
import user.UserCreate;
import static org.hamcrest.CoreMatchers.is;

public class EnterConstrTest {
    private WebDriver driver;
    private String token;
    private UserClient userClient = new UserClient();
    private final WebDriverFactory driverFactory = new WebDriverFactory();
    @Before
    public void setUp() {
        userClient = new UserClient();
        driver =  driverFactory.get();
        UserCreate userCreate = new UserCreate("drtrerterfgdf@yandex.ru", "asdasdasd", "Dasdax");
        ProfilePage profilePage = new ProfilePage(driver);
        HomePage homePage = new HomePage(driver);
        ValidatableResponse responseLogin = UserClient.loginUser(userCreate);
        token = responseLogin.extract()
                            .jsonPath()
                            .get("accessToken");
        homePage.clickButtonPersonalAccount();
        profilePage.sendTheLoginForm("drtrerterfgdf@yandex.ru", "asdasdasd");
        homePage.clickButtonPersonalAccount();
    }
    @After
    @Step("Удаление тестовых данных")
    public void tearDown() {
        if (token != null) {
            userClient.userDelete(token);
        }
        driver.quit();
    }
    @Test
    @DisplayName("Клик по кнопке «Конструктор» открывает Конструктор")
    public void enterConstructorFromProfileTest() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickButtonConstructor();
        HomePage homePage = new HomePage(driver);
        String result = homePage.findBunButtonText();
        MatcherAssert.assertThat(result, is("Булки"));
    }
    @Test
    @DisplayName("Клик по Stellar Burgers открывает Конструктор")
    public void enterConstructorFromLogoTest() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoStellarBurgers();
        HomePage homePage = new HomePage(driver);
        String result = homePage.findBunButtonText();
        MatcherAssert.assertThat(result, is("Булки"));
    }
}
