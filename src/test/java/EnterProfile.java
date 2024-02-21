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
public class EnterProfile {
    private WebDriver driver;
    private String token;
    private UserClient userClient = new UserClient();
    private final WebDriverFactory driverFactory = new WebDriverFactory();
    @Before
    public void setUp() {
        driver = driverFactory.get();
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
    @DisplayName("Клик по кнопке «Личный кабинет» открывает окно с возможностью выхода из кабинета")
    public void LoginButtonTest() {
        userClient = new UserClient();
        UserCreate userCreate = new UserCreate("drtrerterfgdf@yandex.ru", "asdasdasd", "Dasdax");
        ValidatableResponse responseCreate = userClient.newUser(userCreate);
        token = responseCreate.extract()
                            .jsonPath()
                            .get("accessToken");
        ProfilePage profilePage = new ProfilePage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();
        profilePage.sendTheLoginForm("drtrerterfgdf@yandex.ru", "asdasdasd");
        homePage.clickButtonPersonalAccount();
        String result = profilePage.findButtonExit();
        MatcherAssert.assertThat(result, is("Выход"));
    }
}
